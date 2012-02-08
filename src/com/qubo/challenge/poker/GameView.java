package com.qubo.challenge.poker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.qubo.challenge.poker.models.CardException;
import com.qubo.challenge.poker.models.Deck;
import com.qubo.challenge.poker.models.Hand;
import com.qubo.challenge.poker.models.TypeOfHand;
import com.qubo.views.AbstractView;
import com.qubo.views.View;

/**
 * �R���\�[���ł̃|�[�J�[��\������{@link View}
 * @author Qubo
 */
public class GameView extends AbstractView<int[]> {
	private Deck deck;
	private Hand hand;
	private int changeCount;
	private final View parent;

	/**
	 * �R���X�g���N�^
	 * @param parent �Ăяo������{@link View}�C���X�^���X
	 */
	GameView(View parent) {
		super("�|�[�J�[");
		this.parent = parent;
		try {
			changeCount = Configuration.changeCount;
			deck = new Deck(Configuration.jokerCount);
			hand = deck.deal();
		} catch (CardException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void renderBody() {
		TypeOfHand typeOfHand = TypeOfHand.getTypeOfHand(hand);

		if (changeCount == Configuration.changeCount)
			printLine("������D�͎��̂Ƃ���ł��B");

		printLine("������������������������������");
		printLine("��{0}����{1}����{2}����{3}����{4}��",
				hand.get(0).getSuitSymbol2Bytes(),
				hand.get(1).getSuitSymbol2Bytes(),
				hand.get(2).getSuitSymbol2Bytes(),
				hand.get(3).getSuitSymbol2Bytes(),
				hand.get(4).getSuitSymbol2Bytes());
		printLine("��{0}����{1}����{2}����{3}����{4}��",
				hand.get(0).getNumberSymbol2Bytes(),
				hand.get(1).getNumberSymbol2Bytes(),
				hand.get(2).getNumberSymbol2Bytes(),
				hand.get(3).getNumberSymbol2Bytes(),
				hand.get(4).getNumberSymbol2Bytes());
		printLine("������������������������������");
		printLine("  �P    �Q    �R    �S    �T  ");

		if (changeCount == 0) {
			printLine("����[{0}]�ł��BEnter�L�[�������ƃ��j���[�ɖ߂�܂�", typeOfHand.getName());
		} else if (deck.getRemainings() == 0) {
			changeCount = 0;
			printLine("�f�b�L�ɃJ�[�h���c���Ă��Ȃ����߁A����ȏ�����ł��܂���B");
			printLine("����[{0}]�ł��BEnter�L�[�������ƃ��j���[�ɖ߂�܂�", typeOfHand.getName());
		} else {
			printLine("���݂̖���[{0}]�ł��B����{1}��J�[�h�������ł��܂��B", typeOfHand.getName(), changeCount);
			printLine("�ύX�������J�[�h�̔ԍ�����͂��Ă�������(1�`5)�B");
			printLine("�������͂�����Enter�L�[�������ƁA���݂̖����m�肳���܂��B");
		}
	}

	@Override
	protected int[] requestUserInput() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			outer:
			try {
				String line = reader.readLine();
				if (changeCount > 0) {
					int available = Math.min(5, deck.getRemainings());
					if (line.length() <= available) {
						int[] change = new int[line.length()];
						for (int i = 0; i < line.length(); i++) {
							int value = Integer.parseInt("" + line.charAt(i));
							if (value < 1 || value > 5) {
								print("1����5�܂ł̐�����������͂��Ă��������I");
								break outer;
							}
							change[i] = value - 1;
						}
						return change;
					} else {
						if (available < 5)
							print("�f�b�L�ɃJ�[�h������܂���B");
						print("��x�Ɍ����ł���̂�{0}���܂łł��I", available);
					}
				} else {
					return new int[0];
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				print("���̓t�H�[�}�b�g�����������ł��I");
			}
		}
	}

	@Override
	protected View view(int[] input) {
		changeCount--;
		if (changeCount == -1) {
			return parent;
		} else {
			if (input.length > 0) {
				try {
					deck.change(hand, input);
					StringBuilder builder = new StringBuilder();
					for (int i : input) {
						if (builder.length() > 0)
							builder.append("�A");
						builder.append((i + 1) + "����");
					}
					builder.append("���������܂����B");
					print(builder.toString());
				} catch (CardException e) {
					e.printStackTrace();
				}
			} else {
				changeCount = 0;
			}

			return this;
		}
	}
}

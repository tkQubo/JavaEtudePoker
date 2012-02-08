package com.qubo.challenge.poker.models;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * �g�����v�̂P�Z�b�g�i���f�b�L�j��\������N���X�B
 * @author Qubo
 */
public class Deck {
	/** �f�b�L���Ɋ܂ނ��Ƃ̂ł���W���[�J�[�̍ő喇��: {@code 2} */
	public static final int JOKER_COUNT_MAX = 2;
	/** �f�b�L���Ɍ����ł���J�[�h������Ȃ��ꍇ�ɔ���:
	 * <code>
	 * "�f�b�L���̌����ł���J�[�h��{0}������܂���I"
	 * </code>
	 */
	public static final String ERROR_DECK_CARD_DEFICIT = "�f�b�L�ɃJ�[�h��{0}������܂���I";
	/** �W���[�J�[�̖����w�肪���������ꍇ�ɔ��������O�̃��b�Z�[�W:
	 * <code>
	 * "�W���[�J�[�̐���0���`2���̊ԂŐݒ肵�Ă��������I"
	 * </code>
	 */
	public static final String ERROR_JOKER_COUNT_RANGE = "�W���[�J�[�̐���0���`2���̊ԂŐݒ肵�Ă��������I";
	private static final Random random = new Random();
	private final List<Card> cards;
	private final List<Card> discardPile;
	private final int jokerCount;

	/**
	 * �f�b�L�̒��Ɋ܂܂��W���[�J�[�̐����擾����
	 * @return �W���[�J�[�̐�
	 */
	public int getJokerCount() { return jokerCount; }

	/**
	 * �R���X�g���N�^�B
	 * @param jokerCount �W���[�J�[�̖���
	 * @throws CardException �W���[�J�[�̖����w�肪���������ꍇ�ɕ\��
	 */
	public Deck(int jokerCount) throws CardException {
		if (jokerCount < 0 || jokerCount > JOKER_COUNT_MAX) throw new CardException(ERROR_JOKER_COUNT_RANGE);
		this.cards = new ArrayList<Card>();
		this.discardPile = new ArrayList<Card>();
		this.jokerCount = jokerCount;
		for (Suit suit : Suit.values()) {
			if (suit != Suit.Joker) {
				for (int i = 2; i <= 14; i++) {
					Card card = new Card(suit, i);
					cards.add(card);
				}
			}
		}
		for (int i = 0; i < jokerCount; i++) {
			cards.add(new Card(Suit.Joker, Card.RAW_VALUE_JOKER));
		}
	}
	/** �W���̃R���X�g���N�^�B�W���[�J�[���܂܂Ȃ��S52���ō\�������B
	 * @throws CardException */
	public Deck() throws CardException { this(0); }

	/**
	 * �f�b�L�̒����烉���_����5�������o���A������R���X�g���N�^�����Ƃ���
	 * {@link Hand}�C���X�^���X�𐶐����A�Ԃ��B<br />
	 * �f�b�L�̒��̃J�[�h������5���ȉ��̏ꍇ�́A{@code null}��Ԃ��B
	 * @return {@link Hand}�C���X�^���X
	 * @throws CardException �J�[�h������Ȃ��ꍇ�ɔ���
	 */
	public Hand deal() throws CardException {
		if (cards.size() < 5) throw new CardException(MessageFormat.format(ERROR_DECK_CARD_DEFICIT, 5 - cards.size()));

		Card[] dealt = new Card[5];

		for (int i = 0; i < 5; i++) {
			Card card = draw();
			dealt[i] = card;
		}

		return new Hand(dealt);
	}
	/**
	 * {@link Hand}�C���X�^���X�̎�̒�����A
	 * {@code indices}�Ŏw�肳�ꂽ�ʒu�ɂ���J�[�h���̂āA
	 * �̂Ă����������V���Ƀf�b�L����J�[�h���[����B<br />
	 * @param hand �����Ώۂ̎�D
	 * @param indices �����������J�[�h�̈ʒu��\�����z��
	 * @throws CardException �J�[�h������Ȃ��ꍇ�ɔ���
	 */
	public void change(Hand hand, int... indices) throws CardException {
		if (cards.size() < indices.length) throw new CardException(MessageFormat.format(ERROR_DECK_CARD_DEFICIT, indices.length - cards.size()));

		for (int index : indices) {
			Card discarded = hand.cards[index];
			discardPile.add(discarded);
			hand.cards[index] = draw();
		}
	}
	/**
	 * �f�b�L�̒��Ɏc�����J�[�h�̖������擾����
	 * @return �c�����J�[�h����
	 */
	public int getRemainings() { return cards.size(); }
	/**
	 * �f�b�L���烉���_����{@link Card}�C���X�^���X��I��ŕԂ��B
	 * �I�����ꂽ{@link Card}�C���X�^���X�́A�f�b�L�����菜�����B
	 * @return
	 */
	private Card draw() {
		return cards.remove(random.nextInt(cards.size()));
	}
}

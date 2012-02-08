package com.qubo.views;

import com.qubo.Utils;

/**
 * ���[�U�[�ɑ΂��ĒP����YES/NO�^�̎�����s���r���[�N���X
 * @author Qubo
 */
public abstract class AbstractYesNoView extends AbstractView<Boolean> {
	/** YES�ɑ������镶��: {@code 'y'} */
	private static final char YES = 'y';
	/** NO�ɑ������镶��: {@code 'n'} */
	private static final char NO = 'n';
	/** YES, NO �̔z�� */
	private static final char[] YESNO = new char[] { YES, NO };
	private final String question;
	private final boolean defaultAnswer;

	/**
	 * �W���̃R���X�g���N�^
	 * @param name �r���[��
	 * @param question ������e
	 * @param defaultAnswer ���[�U�[���������͂��s�킸��Enter�L�[���������ꍇ�ɓK�p����f�t�H���g�̕ԓ�
	 * �i{@code true}��YES�ɁA{@code false}��NO�ɑ�������j
	 */
	public AbstractYesNoView(String name, String question, boolean defaultAnswer) {
		super(name);
		this.question = question;
		this.defaultAnswer = defaultAnswer;
	}

	@Override
	protected final void renderBody() {
		printLine(question);
		printLine(" Y=�͂�/N=������");
		printLine("�������͂�����Enter�L�[�������ƁA[{0}]�ɂȂ�܂�", defaultAnswer  ? "�͂�" : "������");
	}

	@Override
	protected final Boolean requestUserInput() { return Utils.promptChar(YESNO, YES) == YES; }

}
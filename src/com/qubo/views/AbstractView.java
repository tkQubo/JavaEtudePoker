package com.qubo.views;

import java.text.MessageFormat;

import com.qubo.Utils;

/**
 * �قƂ�ǂ̃r���[�̊�b�ƂȂ钊�ۃN���X�B�\���I�Ɉȉ��̋@�\����������B
 * <ul>
 * <li>�g���ɂ�郌�C�A�E�g</li>
 * <li>���[�U�[���͂�v�����A�����{@code T}�^�ɕϊ����擾����</li>
 * <li>����ꂽ{@code T}�^�C���X�^���X�����ɁA���̉�ʂ��擾����</li>
 * </ul>
 * @author Qubo
 * @param <T> ���[�U�[���͌^
 */
public abstract class AbstractView<T> implements View {
	/** �g�����C�A�E�g�̃w�b�_���� */
	public static final String HEAD 			= "������������������������������������������������������������������������������";
	/** �g�����C�A�E�g�̃t�b�^���� */
	public static final String TAIL 			= "������������������������������������������������������������������������������";
	/** �g�����C�A�E�g�̃Z�p���[�^ */
	public static final String SEPARATOR 		= "������������������������������������������������������������������������������";
	/** �g�����C�A�E�g�̃Z�p���[�^�i�ׁj */
	public static final String THINE_SEPARATOR	= "������������������������������������������������������������������������������";
	/** �g�����C�A�E�g�̕� */
	public static final int LAYOUT_WIDTH = 72;

	/**
	 * �W���̃R���X�g���N�^
	 * @param name �r���[��
	 */
	public AbstractView(String name) {
		this.name = name;
	}

	private final String name;
	@Override
	public final String getName() { return name; }

	@Override
	public final View view() {
		// render header
		printHead();
		printLine("��" + name);
		printSeparator();

		renderBody();

		// render footer
		printTail();

		T input = requestUserInput();

		return view(input);
	}

	/** �r���[�̓��e�������_�����O����B */
	protected abstract void renderBody();
	/**
	 * ���[�U�[���͂����߁A{@code T}�^�ŕԂ�
	 * @return {@code T}�^�̃C���X�^���X
	 */
	protected abstract T requestUserInput();
	/**
	 * ���[�U�[���͂Ɋ�Â��Č��ʂ�\�����A���ɕ\������r���[���擾����
	 * @param input ���[�U�[����
	 * @return �r���[
	 */
	protected abstract View view(T input);


	/** �g�����C�A�E�g�̃w�b�_������\������ */
	protected void printHead() { print(HEAD); }
	/** �g�����C�A�E�g�̃t�b�^������\������ */
	protected void printTail() { print(TAIL); }
	/** �g�����C�A�E�g�̃Z�p���[�^��\������ */
	protected void printSeparator() { print(SEPARATOR); }
	/** �g�����C�A�E�g�̃Z�p���[�^�i�ׁj��\������ */
	protected void printThinSeparator() { print(THINE_SEPARATOR); }
	/**
	 * �g�����C�A�E�g���ɕ������\������
	 * @param pattern ������t�H�[�}�b�g
	 * @param objects �t�H�[�}�b�g�̈���
	 */
	protected void printLine(String pattern, Object...objects) {
		String message = MessageFormat.format(pattern, objects);
		message = Utils.rpad(message, LAYOUT_WIDTH);
		System.out.println("�� " + message + " ��");
	}
	/**
	 * �������\������
	 * @param pattern ������t�H�[�}�b�g
	 * @param objects �t�H�[�}�b�g�̈���
	 */
	protected void print(String pattern, Object...objects) {
		System.out.println(MessageFormat.format(pattern, objects));
	}
}

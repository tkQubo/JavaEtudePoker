package com.qubo.views;

import com.qubo.Utils;

/**
 * ���[�U�[�ɑ΂��Đ����l�̓��͂����߂ď������s���r���[�N���X
 * @author Qubo
 */
public abstract class IntegerInputView extends AbstractView<Integer> {
	private final String itemName;
	private final int min;
	private final int max;
	private final View forward;

	/**
	 * �W���̃R���X�g���N�^
	 * @param forward �J�ڐ�̉��
	 * @param name �r���[��
	 * @param min ���͒l�̉���
	 * @param max ���͒l�̏��
	 */
	public IntegerInputView(View forward, String name, int min, int max) {
		super(name + "�̕ύX");
		this.forward = forward;
		this.itemName = name;
		this.min = min;
		this.max = max;
	}

	@Override
	protected final void renderBody() {
		printLine("{0}�̒l����͂��ĉ�����({1}�`{2})�B", itemName, min, max);
		printLine("���̂܂�Enter�L�[�������΁A�ύX���s�킸��{0}�ɖ߂�܂��B", forward.getName());
		printLine("���݂̒l��{0}�ł��B", getValue());
	}

	@Override
	protected final Integer requestUserInput() {
		return Utils.promptInteger(min, max);
	}

	@Override
	protected final View view(Integer input) {
		if (input != null) {
			print("{0}��{1}�ɐݒ肵�܂����B", itemName, input);
			setValue(input);
		}
		return forward;
	}

	/**
	 * �l�̓��͂��������ꍇ�ɁA�������������
	 * @param value
	 */
	protected abstract void setValue(int value);
	/**
	 * �����O�̒l���擾����
	 * @return �ύX�O�̒l
	 */
	protected abstract int getValue();
}

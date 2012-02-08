package com.qubo.views;

import java.text.MessageFormat;

import com.qubo.Utils;

/**
 * ���j���[�r���[({@link MenuView}�N���X)�ŕ\�����郁�j���[���ڂ�\�����N���X
 * @author Qubo
 */
public class MenuItem {
	private char accessCharacter;
	private final String name;
	private final View view;

	/**
	 * �J�ڐ�̃r���[�����擾����
	 * @return �J�ڐ�̃r���[��
	 */
	public String getName() { return name; }
	/**
	 * �A�N�Z�X�������擾����
	 * @return �A�N�Z�X����
	 */
	public char getAccessCharacter() { return accessCharacter; }
	/**
	 * �J�ڐ�̃r���[���擾����
	 * @return �J�ڐ�̃r���[
	 */
	public View getView() { return view; }
	/**
	 * �A�N�Z�X������ݒ肷��B<br />
	 * �{���A�N�Z�X�����͌Œ肾���A���j���[���ڐ������j���[�r���[�ŕ\���ł��錏���𒴂����ꍇ�A
	 * ���j���[�r���[�������A�ԂŃA�N�Z�X���l������U���ăy�[�W���O�@�\��L���ɂ���B
	 * ���̃��\�b�h�͂��̂��߂̓����p���\�b�h�ł���B
	 * @param accessCharacter �A�N�Z�X����
	 */
	void setAccessCharacter(char accessCharacter) { this.accessCharacter = accessCharacter; }

	/**
	 * �R���X�g���N�^
	 * @param accessCharacter �A�N�Z�X����
	 * @param name ���ږ�
	 * @param view �J�ڐ�r���[
	 */
	public MenuItem(char accessCharacter, String name, View view) {
		this.name = name;
		this.accessCharacter = accessCharacter;
		this.view = view;
	}
	/**
	 * �R���X�g���N�^�B���ږ��ɂ́A�J�ڐ�̃r���[�������蓖�Ă���B
	 * @param accessCharacter �A�N�Z�X����
	 * @param view �J�ڐ�r���[
	 */
	public MenuItem(char accessCharacter, View view) {
		this.name = (view != null) ? view.getName() : null;
		this.accessCharacter = accessCharacter;
		this.view = view;
	}

	@Override
	public String toString() {
		return MessageFormat.format("{0} {1}", Utils.lpad(accessCharacter + ":", 4), name);
	}
}

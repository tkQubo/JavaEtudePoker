package com.qubo.views;

/**
 * �R���\�[����ł̉�ʑJ�ڂ�\�������N���X
 * @author Qubo
 */
public interface View {
	/**
	 * �r���[�̖��O���擾����
	 * @return �r���[��
	 */
	String getName();
	/**
	 * ���̃r���[���擾����
	 * @return ���̃r���[
	 */
	View view();
}

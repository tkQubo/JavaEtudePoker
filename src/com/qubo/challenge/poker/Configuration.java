package com.qubo.challenge.poker;

/**
 * �Q�[���̐ݒ��ۑ�����N���X
 * @author Qubo
 */
public abstract class Configuration {
	private Configuration() { }

	/** �J�[�h�̌����񐔂̃f�t�H���g�l */
	public static final int DEFAULT_CHANGECOUNT = 1;
	/** �W���[�J�[�����̃f�t�H���g�l */
	public static final int DEFAULT_JOKERCOUNT = 0;
	/** �W���[�J�[���� */
	public static int jokerCount = DEFAULT_JOKERCOUNT;
	/** �J�[�h�̌����� */
	public static int changeCount = DEFAULT_CHANGECOUNT;
}

package com.qubo.challenge.poker.models;

/**
 * �|�[�J�[�A�v���P�[�V�����ŗL�̗�O
 * @author Qubo
 */
public class CardException extends Exception {
	/** �V���A���o�[�W�����t�h�c */
	private static final long serialVersionUID = 3983999438471225539L;

	/** �W���̃R���X�g���N�^ */
	public CardException() { }
	/**
	 * �R���X�g���N�^
	 * @param message �G���[���b�Z�[�W
	 * @param throwable �����G���[
	 */
	public CardException(String message, Throwable throwable) { super(message, throwable); }
	/**
	 * �R���X�g���N�^
	 * @param message �G���[���b�Z�[�W
	 */
	public CardException(String message) { super(message); }
	/**
	 * �R���X�g���N�^
	 * @param throwable �����G���[
	 */
	public CardException(Throwable throwable) { super(throwable); }

}

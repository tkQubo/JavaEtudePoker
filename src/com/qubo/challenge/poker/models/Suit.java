package com.qubo.challenge.poker.models;

/**
 * �J�[�h�̃}�[�N��\�������N���X�B
 * �W���[�J�[����O�I�Ƀ}�[�N�̈��Ƃ��Ē�`����Ă���B
 * @author Qubo
 */
public enum Suit {
	/** �n�[�g */
	Heart { @Override public char getSymbol() { return SYMBOL_HEART; } },
	/** �X�y�[�h */
	Spade { @Override public char getSymbol() { return SYMBOL_SPADE; } },
	/** �_�C�������h */
	Diamond { @Override public char getSymbol() { return SYMBOL_DIAMOND; } },
	/** �N���u */
	Club { @Override public char getSymbol() { return SYMBOL_CLUB; } },
	/** �W���[�J�[ */
	Joker { @Override public char getSymbol() { return SYMBOL_JOKER; } },
	;
	/** �n�[�g�̕����\��: {@code 'H'} */
	public static final char SYMBOL_HEART = 'H';
	/** �X�y�[�h�̕����\��: {@code 'S'} */
	public static final char SYMBOL_SPADE = 'S';
	/** �n�[�g�̕����\��: {@code 'D'} */
	public static final char SYMBOL_DIAMOND = 'D';
	/** �N���u�̕����\��: {@code 'C'} */
	public static final char SYMBOL_CLUB = 'C';
	/** �W���[�J�[�̕����\��: {@code ' '} */
	public static final char SYMBOL_JOKER = ' ';

	/**
	 * �}�[�N�̕�����\�����擾����
	 * @return �}�[�N�̕�����\��
	 */
	public abstract char getSymbol();
}

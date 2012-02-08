package com.qubo.challenge.poker.models;


/**
 * �|�[�J�[�̖���\�����N���X�B<br />
 * ���̋����͋������Ɏ��̂Ƃ���ł���F<br />
 * <ol>
 * <li>�t�@�C�u�J�[�h</li>
 * <li>���C�����t���b�V��</li>
 * <li>�X�g���[�g�t���b�V��</li>
 * <li>�t�H�[�J�[�h</li>
 * <li>�t���n�E�X</li>
 * <li>�t���b�V��</li>
 * <li>�X�g���[�g</li>
 * <li>�X���[�J�[�h</li>
 * <li>�c�[�y�A</li>
 * <li>�����y�A</li>
 * <li>�𖳂�</li>
 * </ol>
 * �W���[�J�[���g��Ȃ����[���ł́A���R�̂��ƂȂ���t�@�C�u�J�[�h�͑��݂��Ȃ��B
 * @author Qubo
 */
public enum TypeOfHand {
	/** �t�@�C�u�J�[�h */
	FiveOfAKind {
		@Override public String getName() { return NAME_FIVE_OF_A_KIND; }
		@Override public boolean isValid(Hand hand) {
			return hand.isOfAKind(5 - hand.getJoker()) == 1;
		}
	},
	/** ���C�����t���b�V�� */
	RoyalFlush {
		@Override public String getName() { return NAME_ROYAL_FLUSH; }
		@Override public boolean isValid(Hand hand) {
			return hand.isSameSuit() && hand.isSequentialFrom(10);
		}
	},
	/** �X�g���[�g�t���b�V�� */
	StraightFlush {
		@Override public String getName() { return NAME_STRAIGHT_FLUSH; }
		@Override public boolean isValid(Hand hand) {
			return hand.isSameSuit() && hand.isSequential() && !hand.isSequentialFrom(10);
		}
	},
	/** �t�H�[�J�[�h */
	FourOfAKind {
		@Override public String getName() { return NAME_FOUR_OF_A_KIND; }
		@Override public boolean isValid(Hand hand) {
			return hand.isOfAKind(4 - hand.getJoker()) == 1;
		}
	},
	/** �t���n�E�X */
	FullHouse {
		@Override public String getName() { return NAME_FULL_HOUSE; }
		@Override public boolean isValid(Hand hand) {
			return (hand.isOfAKind(3) == 1 && hand.isOfAKind(2) == 1)
					|| (hand.isOfAKind(2) == 2 && hand.getJoker() == 1);
		}
	},
	/** �t���b�V�� */
	Flush {
		@Override public String getName() { return NAME_FLUSH; }
		@Override public boolean isValid(Hand hand) {
			return hand.isSameSuit() && !hand.isSequential();
		}
	},
	/** �X�g���[�g */
	Straight {
		@Override public String getName() { return NAME_STRAIGHT; }
		@Override public boolean isValid(Hand hand) {
			return hand.isSequential() && !hand.isSameSuit();
		}
	},
	/** �X���[�J�[�h */
	ThreeOfAKind {
		@Override public String getName() { return NAME_THREE_OF_A_KIND; }
		@Override public boolean isValid(Hand hand) {
			return (hand.isOfAKind(3) == 1 && hand.isOfAKind(1) == 2)
					|| (hand.getJoker() == 1 && hand.isOfAKind(1) == 2 && hand.isOfAKind(2) == 1)
					|| (hand.getJoker() == 2 && hand.isOfAKind(1) == 3 && !hand.isSameSuit() && !hand.isSequential());
		}
	},
	/** �c�[�y�A */
	TwoPair {
		@Override public String getName() { return NAME_TWO_PAIR; }
		@Override public boolean isValid(Hand hand) {
			return hand.isOfAKind(2) == 2 && hand.isOfAKind(1) == 1;
		}
	},
	/** �����y�A */
	OnePair {
		@Override public String getName() { return NAME_ONE_PAIR; }
		@Override public boolean isValid(Hand hand) {
			return (hand.isOfAKind(2) == 1 && hand.isOfAKind(1) == 3)
					|| (hand.getJoker() == 1 && hand.isOfAKind(1) == 4 && !hand.isSameSuit() && !hand.isSequential());
		}
	},
	/** �𖳂� */
	HighCards {
		@Override public String getName() { return NAME_HIGH_CARDS; }
		@Override public boolean isValid(Hand hand) {
			return !hand.isSameSuit() && !hand.isSequential() && hand.isOfAKind(1) == 5;
		}
	};

	/** �� */
	public static final String NAME_FIVE_OF_A_KIND = "�t�@�C�u�J�[�h";
	/** �� */
	public static final String NAME_ROYAL_FLUSH = "���C�����t���b�V��";
	/** �� */
	public static final String NAME_STRAIGHT_FLUSH = "�X�g���[�g�t���b�V��";
	/** �� */
	public static final String NAME_FOUR_OF_A_KIND = "�t�H�[�J�[�h";
	/** �𖼃X */
	public static final String NAME_FULL_HOUSE = "�t���n�E�X";
	/** �� */
	public static final String NAME_FLUSH = "�t���b�V��";
	/** �� */
	public static final String NAME_STRAIGHT = "�X�g���[�g";
	/** �� */
	public static final String NAME_THREE_OF_A_KIND = "�X���[�J�[�h";
	/** �� */
	public static final String NAME_TWO_PAIR = "�c�[�y�A";
	/** �� */
	public static final String NAME_ONE_PAIR = "�����y�A";
	/** �� */
	public static final String NAME_HIGH_CARDS = "�𖳂�";

	/**
	 * ���̖��O���擾����
	 * @return ���̖��O
	 */
	public abstract String getName();
	/**
	 * ��D�����̏����𖞂����Ă��邩�ǂ������擾����B
	 * ���̃��\�b�h��
	 * <strong>
	 * {@code true}���擾���邱�Ƃ́A��荂�ʂ̖������݂���\����r�����Ȃ��B
	 * </strong>
	 * �ō��ʂ̖���m�肽���ꍇ�́A{@link #getTypeOfHand(Hand)}���g�����ƁB
	 * @param hand ��D�C���X�^���X
	 * @return ��D�����̏����𖞂����Ă��邩�ǂ���
	 */
	public abstract boolean isValid(Hand hand);

	/** �|�[�J�[�̑S�Ă̖����A�������̂��珇�ɕ��ׂ��z�� */
	public static final TypeOfHand[] ALL = {
		FiveOfAKind, RoyalFlush, StraightFlush, FourOfAKind, FullHouse, Flush, Straight, ThreeOfAKind, TwoPair, OnePair, HighCards
	};

	/**
	 * ��D���\����������̒��ŁA�ł����ʂ̂��̂�Ԃ��B
	 * @param hand ��D
	 * @return ��
	 */
	public static TypeOfHand getTypeOfHand(Hand hand) {
		for (TypeOfHand typeOfHand : ALL) {
			if (typeOfHand.isValid(hand)) return typeOfHand;
		}
		return null;
	}
	/*
	 * (�� Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() { return "{" + getName() + "}"; }
}

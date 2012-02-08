package com.qubo.challenge.poker.models;

import java.text.MessageFormat;

import com.qubo.Utils;

/**
 * �g�����v�̃J�[�h��\�����N���X�B
 * @author Qubo
 */
public class Card {
	/**
	 * ������̃t�H�[�}�b�g�����������ꍇ�ɔ���:
	 * <code>
	 * "������[{0}]����[{1}]�������ł��܂���ł����I"
	 * </code>
	 */
	public static final String ERROR_PARSE = "������[{0}]����[{1}]�������ł��܂���ł����I";
	/**
	 * �R���X�g���N�^�ɗ^�������l���A�͈͊O�̏ꍇ�ɔ���:
	 * <code>
	 * "�W���[�J�[�ȊO�̃J�[�h�𐶐�����ꍇ�́A���l��2�`14�͈͓̔��Ŏw�肵�Ă��������I"
	 * </code>
	 */
	public static final String ERROR_NUMBER_OUT_OF_RANGE = "�W���[�J�[�ȊO�̃J�[�h�𐶐�����ꍇ�́A���l��2�`14�͈͓̔��Ŏw�肵�Ă��������I";
	/**
	 * �R���X�g���N�^�ɗ^�����}�[�N��{@code null}�������ꍇ�ɔ���:
	 * <code>
	 * "�}�[�N�ɂ�null���w��ł��܂���I"
	 * </code>
	 */
	public static final String ERROR_SUIT_NULL = "�}�[�N�ɂ�null���w��ł��܂���I";

	/** �W���b�N�̕����\��: {@code 'J'} */
	public static final char SYMBOL_JACK = 'J';
	/** �N�C�[���̕����\��: {@code 'Q'} */
	public static final char SYMBOL_QUEEN = 'Q';
	/** �L���O�̕����\��: {@code 'K'} */
	public static final char SYMBOL_KING = 'K';
	/** �G�[�X�̕����\��: {@code 'A'} */
	public static final char SYMBOL_ACE = 'A';
	/** �W���[�J�[�̕����\��: {@code ' '} */
	public static final char SYMBOL_JOKER = ' ';
	/** �W���[�J�[�̐���: {@code -1} */
	public static final int RAW_VALUE_JOKER = -1;

	private final Suit suit;
	private final int number;

	/**
	 * �W���̃R���X�g���N�^�B
	 * �W���[�J�[�𐶐�����ꍇ�́A{@code number}�̒l�͖�������A�����{@link #RAW_VALUE_JOKER}���K�p�����B
	 * @param suit �}�[�N
	 * @param number �����i�G�[�X��14�Ƃ��āA2�`14�܂łőS������\������j
	 * @throws CardException �R���X�g���N�^�̈������Ԉ���Ă���ꍇ�ɔ�������
	 */
	public Card(Suit suit, int number) throws CardException {
		if (suit == null) throw new CardException(ERROR_SUIT_NULL);
		if (suit != Suit.Joker && (number < 2 || number > 14)) throw new CardException(ERROR_NUMBER_OUT_OF_RANGE);

		this.suit = suit;
		this.number = (suit != Suit.Joker) ? number : RAW_VALUE_JOKER;
	}
	/**
	 * ���������͂���{@link Card}�C���X�^���X�𐶐�����B<br />
	 * ������̓}�[�N�{�������琬�藧���Ă���A
	 * �}�[�N�̓N���u�A�_�C�������h�A�n�[�g�A�X�y�[�h���ꂼ�ꂪC, D, H, S�̈ꕶ���ɑΉ�����B<br />
	 * �����̓G�[�X��14�Ƃ��āA2�`14�܂łőS������\������B<br />
	 * ��O�Ƃ��āA�W���[�J�[�͋�2����{@code "  "}�Ő�������B
	 * @param format ��͂��镶����
	 * @return �������ꂽ{@link Card}�C���X�^���X
	 * @throws CardException ������̃t�H�[�}�b�g�����������ꍇ�ɔ���
	 */
	public static Card parse(String format) throws CardException {
		Suit suit;
		int number;

		char chSuit = format.charAt(0);
		switch (chSuit) {
		case Suit.SYMBOL_HEART:
			suit = Suit.Heart;
			break;
		case Suit.SYMBOL_SPADE:
			suit = Suit.Spade;
			break;
		case Suit.SYMBOL_DIAMOND:
			suit = Suit.Diamond;
			break;
		case Suit.SYMBOL_CLUB:
			suit = Suit.Club;
			break;
		case Suit.SYMBOL_JOKER:
			suit = Suit.Joker;
			break;
		default:
			throw new CardException(MessageFormat.format(ERROR_PARSE, format, chSuit));
		}
		if (format.length() == 2) {
			char chNumber = format.charAt(1);
			switch (chNumber) {
			case SYMBOL_JACK:
				number = 11;
				break;
			case SYMBOL_QUEEN:
				number = 12;
				break;
			case SYMBOL_KING:
				number = 13;
				break;
			case SYMBOL_ACE:
				number = 14;
				break;
			case SYMBOL_JOKER:
				number = RAW_VALUE_JOKER;
				break;
			default:
				try {
					number = Integer.parseInt("" + chNumber);
				} catch (NumberFormatException e) {
					throw new CardException(MessageFormat.format(ERROR_PARSE, format, chNumber));
				}
				break;
			}
		} else if (format.substring(1).equals("10")) {
			number = 10;
		} else {
			throw new CardException(MessageFormat.format(ERROR_PARSE, format, format.substring(1)));
		}

		return new Card(suit, number);
	}

	/**
	 * �J�[�h�̃}�[�N���擾����
	 * @return �J�[�h�̃}�[�N
	 */
	public Suit getSuit() { return suit; }
	/**
	 * �}�[�N�̕�����\�����擾����B<br />
	 * <ul>
	 * <li>{@link Suit#Heart}�̃J�[�h��{@link Suit#SYMBOL_HEART}</li>
	 * <li>{@link Suit#Spade}�̃J�[�h��{@link Suit#SYMBOL_SPADE}</li>
	 * <li>{@link Suit#Diamond}�̃J�[�h��{@link Suit#SYMBOL_DIAMOND}</li>
	 * <li>{@link Suit#Club}�̃J�[�h��{@link Suit#SYMBOL_CLUB}</li>
	 * </ul>
	 * �𕶎���Ƃ��ĕԂ��B
	 * @return �}�[�N�̕�����\��
	 */
	public String getSuitSymbol() { return "" + suit.getSymbol(); }
	/**
	 * �}�[�N�̕�����\����S�p�Ŏ擾����B<br />
	 * <ul>
	 * <li>{@link Suit#Heart}�̃J�[�h��{@link Suit#SYMBOL_HEART}�̑S�p</li>
	 * <li>{@link Suit#Spade}�̃J�[�h��{@link Suit#SYMBOL_SPADE}�̑S�p</li>
	 * <li>{@link Suit#Diamond}�̃J�[�h��{@link Suit#SYMBOL_DIAMOND}�̑S�p</li>
	 * <li>{@link Suit#Club}�̃J�[�h��{@link Suit#SYMBOL_CLUB}�̑S�p</li>
	 * </ul>
	 * �𕶎���Ƃ��ĕԂ��B
	 * @return �}�[�N��2�o�C�g������\��
	 */
	public String getSuitSymbol2Bytes() { return "" + Utils.toFullWidth(suit.getSymbol()); }
	/**
	 * �J�[�h�̐������擾����B<br />
	 * <b>���G�[�X��14�ŕ\������</b>�B
	 * @return �J�[�h�̐���
	 */
	public int getRawNumber() { return number; }
	/**
	 * ���l�̕�����\�����擾����B<br />
	 * <ul>
	 * <li>11�̏ꍇ��{@link Card#SYMBOL_JACK}</li>
	 * <li>12�̏ꍇ��{@link Card#SYMBOL_QUEEN}</li>
	 * <li>13�̏ꍇ��{@link Card#SYMBOL_KING}</li>
	 * <li>14�̏ꍇ��{@link Card#SYMBOL_ACE}</li>
	 * <li>{@link #RAW_VALUE_JOKER}�̏ꍇ��{@link Card#SYMBOL_JOKER}</li>
	 * </ul>
	 * ����ȊO�̏ꍇ�͐��������̂܂ܕ�����ɂ������̂�Ԃ��B
	 * @return ���l�̕�����\��
	 */
	public String getNumberSymbol() {
		switch (number) {
		case 11:	return "" + SYMBOL_JACK;
		case 12:	return "" + SYMBOL_QUEEN;
		case 13:	return "" + SYMBOL_KING;
		case 14:	return "" + SYMBOL_ACE;
		case RAW_VALUE_JOKER: return "" + SYMBOL_JOKER;
		default:	return "" + number;
		}
	}
	/**
	 * ���l�̕�����\����2�o�C�g�Ŏ擾����B<br />
	 * <ul>
	 * <li>10�̏ꍇ�͂��̂܂�{@code "10"}</li>
	 * <li>11�̏ꍇ�͑S�p��{@link Card#SYMBOL_JACK}</li>
	 * <li>12�̏ꍇ�͑S�p��{@link Card#SYMBOL_QUEEN}</li>
	 * <li>13�̏ꍇ�͑S�p��{@link Card#SYMBOL_KING}</li>
	 * <li>14�̏ꍇ�͑S�p��{@link Card#SYMBOL_ACE}</li>
	 * <li>{@link #RAW_VALUE_JOKER}�̏ꍇ��{@link Card#SYMBOL_JOKER}</li>
	 * </ul>
	 * ����ȊO�̏ꍇ�͐�����S�p�����ɂ��ĕԂ��B
	 * @return ���l��2�o�C�g������\��
	 */
	public String getNumberSymbol2Bytes() {
		switch (number) {
		case 10: return "10";
		case 11: return "" + Utils.toFullWidth(SYMBOL_JACK);
		case 12: return "" + Utils.toFullWidth(SYMBOL_QUEEN);
		case 13: return "" + Utils.toFullWidth(SYMBOL_KING);
		case 14: return "" + Utils.toFullWidth(SYMBOL_ACE);
		case RAW_VALUE_JOKER: return "" + Utils.toFullWidth(SYMBOL_JOKER);
		default: return "" + Utils.toFullWidth(("" + number).charAt(0));
		}
	}

	/*
	 * (�� Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() { return getSuitSymbol() + getNumberSymbol(); }
	/*
	 * (�� Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			Card other = (Card) obj;
			return this.number == other.number && this.suit == other.suit;
		}
		return false;
	}

}

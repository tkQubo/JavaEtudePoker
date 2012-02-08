package com.qubo.challenge.poker.models;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * �|�[�J�[�̎�D��\�������N���X
 * @author Qubo
 */
public class Hand implements Iterable<Card> {
	Card[] cards;

	/**
	 * �W���̃R���X�g���N�^
	 * @param cards �z��ꂽ5���̃J�[�h���A�z��Ƃ��ĕ\����������
	 */
	Hand(Card... cards) { this.cards = cards; }
	/**
	 * �J�[�h�̕�����𗘗p�����R���X�g���N�^
	 * @param c1 1���ڂ̃J�[�h�̕�����\��
	 * @param c2 2���ڂ̃J�[�h�̕�����\��
	 * @param c3 3���ڂ̃J�[�h�̕�����\��
	 * @param c4 4���ڂ̃J�[�h�̕�����\��
	 * @param c5 5���ڂ̃J�[�h�̕�����\��
	 * @throws CardException ������̃t�H�[�}�b�g�����������ꍇ�ɔ���
	 */
	public Hand(String c1, String c2, String c3, String c4, String c5) throws CardException {
		this.cards = new Card[] { Card.parse(c1), Card.parse(c2), Card.parse(c3), Card.parse(c4), Card.parse(c5) };
	}

	/**
	 * {@code index}�̈ʒu�ɂ���J�[�h���擾����B
	 * @param index �J�[�h�̈ʒu
	 * @return {@link Card}�C���X�^���X
	 */
	public Card get(int index) { return cards[index]; }

	/*
	 * (�� Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return MessageFormat.format("[{0}][{1}][{2}][{3}][{4}]", cards[0], cards[1], cards[2], cards[3], cards[4]);
	}
	/*
	 * (�� Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Card> iterator() {
		return Collections.unmodifiableList(Arrays.asList(cards)).iterator();
	}
	/**
	 * ��D�̒��̃W���[�J�[�̐��𐔂���
	 * @return �W���[�J�[�̐�
	 */
	public int getJoker() {
		int total = 0;
		for (Card card : cards)
			if (card.getSuit() == Suit.Joker)
				total++;
		return total;
	}
	/**
	 * ��D�̒�����A�ŏ��̐����擾����B
	 * �W���[�J�[�͖��������B
	 * @return �ŏ��̐�
	 */
	public int getLowestNumber() {
		int min = Integer.MAX_VALUE;
		for (Card card : cards)
			if (card.getSuit() != Suit.Joker)
				min = Math.min(min, card.getRawNumber());
		return min;
	}
	/**
	 * ��D�̒�����A�ő�̐����擾����B
	 * �W���[�J�[�͖��������B
	 * @return �ő�̐�
	 */
	public int getHighestNumber() {
		int max = Integer.MIN_VALUE;
		for (Card card : cards)
			if (card.getSuit() != Suit.Joker)
				max = Math.max(max, card.getRawNumber());
		return max;
	}
	/**
	 * ��D�̒��ɁA���������̃J�[�h��{@code count}�������Ă���悤�ȑg���A
	 * ���Z�b�g���邩��Ԃ��B<br />
	 * �Ⴆ��{@code (isOfAkind(hand, 2) == 2)}�ƂȂ����ꍇ�̓c�[�y�A�ƂȂ�A
	 * {@code (isOfAkind(hand, 3) == 1 && isOfAkind(hand, 2) == 1)}�ƂȂ����ꍇ�̓t���n�E�X�ƂȂ�B
	 * �W���[�J�[�̓J�E���g�ɉ����Ȃ��B
	 * @param count �����Ă���ׂ�����
	 * @return �Z�b�g��
	 */
	public int isOfAKind(int count) {
		int found = 0;
		Map<Integer, Integer> kinds = new HashMap<Integer, Integer>();
		for (Card card : cards) {
			int number = card.getRawNumber();
			if (number != Card.RAW_VALUE_JOKER) {
				if (!kinds.containsKey(number)) {
					kinds.put(number, 0);
				}
				kinds.put(number, kinds.get(number) + 1);
			}
		}
		for (Integer value : kinds.values()) {
			if (value == count) found++;
		}
		return found;
	}
	public boolean hasNumber(int number) {
		for (Card card : cards) {
			if (card.getRawNumber() == number) {
				return true;
			}
		}
		return false;
	}
	/**
	 * ��D���A�w�肳�ꂽ��������A�����������ō\������Ă��邩�ǂ������擾����
	 * @param start �V�[�P���X�̊J�n����
	 * @return ��D���w�肳�ꂽ��������A�����������ō\������Ă��邩�ǂ���
	 */
	public boolean isSequentialFrom(int start) {
		int jokerRemain = getJoker();
		for (int num = start; num < start + 5; num++) {
			if (!hasNumber(num))
				jokerRemain--;
			if (jokerRemain < 0)
				return false;
		}
		return true;
	}
	/**
	 * ��D���A�����������ō\������Ă��邩�ǂ������擾����
	 * @return ��D���A�����������ō\������Ă��邩�ǂ���
	 */
	public boolean isSequential() {
		return isSequentialFrom(Math.min(getLowestNumber(), 10));
	}
	/**
	 * ��D���S�ē����}�[�N�ł��邩�ǂ������擾����
	 * �W���[�J�[�͖��������B�B
	 * @return ��D���S�ē����}�[�N�ł��邩�ǂ���
	 */
	public boolean isSameSuit() {
		Suit suit = null;
		for (Card card : cards) {
			if (card.getSuit() != Suit.Joker) {
				if (suit != null && suit != card.getSuit())
					return false;
				suit = card.getSuit();
			}
		}
		return true;
	}
}

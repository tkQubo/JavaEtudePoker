package test.com.qubo.challenge.poker.models;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.text.MessageFormat;

import org.junit.Test;

import com.qubo.challenge.poker.models.Card;
import com.qubo.challenge.poker.models.CardException;
import com.qubo.challenge.poker.models.Hand;

/**
 * {@link Hand}�p�̃e�X�g���`�����N���X
 * @author Qubo
 */
public class HandTest {
	/** {@link Hand#Hand(String, String, String, String, String)}�̃e�X�g */
	@Test
	public void testHandStringStringStringStringString() {
		try {
			String s1 = "H2";
			String s2 = "D9";
			String s3 = "S10";
			String s4 = "SJ";
			String s5 = "CA";
			new Hand(s1, s2, s3, s4, s5);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		doFailTestHandStringStringStringStringString("ZT", "Z");
		doFailTestHandStringStringStringStringString("HT", "T");
		doFailTestHandStringStringStringStringString("H123456", "123456");
	}
	/**
	 * {@link #doFailTestHandStringStringStringStringString(String, boolean)}�p�̓������\�b�h
	 * @param failedFormat �Ԉ�����t�H�[�}�b�g
	 * @param failedString �Ԉ�����ӏ�
	 */
	private void doFailTestHandStringStringStringStringString(String failedFormat, String failedString) {
		try {
			new Hand("H2", "D9", "S10", "SJ", failedFormat);
			fail();
		} catch (CardException e) {
			String message = MessageFormat.format(Card.ERROR_PARSE, failedFormat, failedString);
			assertThat(e.getMessage(), is(message));
		}
	}
	/** {@link Hand#get(int)}�̃e�X�g */
	@Test
	public void testGet() {
		try {
			String s1 = "H2";
			String s2 = "D9";
			String s3 = "S10";
			String s4 = "SJ";
			String s5 = "CA";
			Card card1 = Card.parse(s1);
			Card card2 = Card.parse(s2);
			Card card3 = Card.parse(s3);
			Card card4 = Card.parse(s4);
			Card card5 = Card.parse(s5);
			Hand hand = new Hand(s1, s2, s3, s4, s5);
			assertThat(hand.get(0), is(card1));
			assertThat(hand.get(1), is(card2));
			assertThat(hand.get(2), is(card3));
			assertThat(hand.get(3), is(card4));
			assertThat(hand.get(4), is(card5));
		} catch (CardException e) {
			fail(e.getMessage());
		}
	}

	/** {@link Hand#iterator()}�̃e�X�g */
	@Test
	public void testIterator() {
		try {
			String s1 = "H2";
			String s2 = "D9";
			String s3 = "S10";
			String s4 = "SJ";
			String s5 = "CA";
			Hand hand = new Hand(s1, s2, s3, s4, s5);
			int i = 0;
			int numTotalExpected = 46;
			int numTotal = 0;
			for (Card card : hand) {
				i++;
				numTotal += card.getRawNumber();
			}

			assertThat(i, is(5));
			assertThat(numTotal, is(numTotalExpected));
		} catch (CardException e) {
			fail(e.getMessage());
		}
	}

	/** {@link Hand#isSameSuit()}�̃e�X�g */
	@Test
	public void testIsSameSuit() {
		doTestIsSameSuit(Hands.fiveOfAKind1, false);
		doTestIsSameSuit(Hands.fiveOfAKind2, false);
		doTestIsSameSuit(Hands.royalFlush1, true);
		doTestIsSameSuit(Hands.royalFlush2, true);
		doTestIsSameSuit(Hands.royalFlush3, true);
		doTestIsSameSuit(Hands.royalFlush4, true);
		doTestIsSameSuit(Hands.straightFlush1, true);
		doTestIsSameSuit(Hands.straightFlush2, true);
		doTestIsSameSuit(Hands.straightFlush3, true);
		doTestIsSameSuit(Hands.straightFlush4, true);
		doTestIsSameSuit(Hands.fourOfAKind1, false);
		doTestIsSameSuit(Hands.fourOfAKind2, false);
		doTestIsSameSuit(Hands.fourOfAKind3, false);
		doTestIsSameSuit(Hands.fourOfAKind4, false);
		doTestIsSameSuit(Hands.fullHouse1, false);
		doTestIsSameSuit(Hands.fullHouse2, false);
		doTestIsSameSuit(Hands.fullHouse3, false);
		doTestIsSameSuit(Hands.fullHouse4, false);
		doTestIsSameSuit(Hands.flush1, true);
		doTestIsSameSuit(Hands.flush2, true);
		doTestIsSameSuit(Hands.flush3, true);
		doTestIsSameSuit(Hands.flush4, true);
		doTestIsSameSuit(Hands.straight1, false);
		doTestIsSameSuit(Hands.straight2, false);
		doTestIsSameSuit(Hands.straight3, false);
		doTestIsSameSuit(Hands.straight4, false);
		doTestIsSameSuit(Hands.threeOfAKind1, false);
		doTestIsSameSuit(Hands.threeOfAKind2, false);
		doTestIsSameSuit(Hands.threeOfAKind3, false);
		doTestIsSameSuit(Hands.threeOfAKind4, false);
		doTestIsSameSuit(Hands.twoPair1, false);
		doTestIsSameSuit(Hands.twoPair2, false);
		doTestIsSameSuit(Hands.onePair1, false);
		doTestIsSameSuit(Hands.onePair2, false);
		doTestIsSameSuit(Hands.onePair3, false);
		doTestIsSameSuit(Hands.onePair4, false);
		doTestIsSameSuit(Hands.highCards1, false);
		doTestIsSameSuit(Hands.highCards2, false);
		doTestIsSameSuit(Hands.highCards3, false);
		doTestIsSameSuit(Hands.highCards4, false);
	}
	/**
	 * {@link #testIsSameSuit()}�p�̓������\�b�h
	 * @param hand ��D
	 * @param expected �\�z�l
	 */
	private void doTestIsSameSuit(Hand hand, boolean expected) {
		assertThat(hand.isSameSuit(), is(expected));
	}

	/** {@link Hand#isSequential()}�̃e�X�g */
	@Test
	public void testIsSequential() {
		doTestIsSequential(Hands.fiveOfAKind1, false);
		doTestIsSequential(Hands.fiveOfAKind2, false);
		doTestIsSequential(Hands.royalFlush1, true);
		doTestIsSequential(Hands.royalFlush2, true);
		doTestIsSequential(Hands.royalFlush3, true);
		doTestIsSequential(Hands.royalFlush4, true);
		doTestIsSequential(Hands.straightFlush1, true);
		doTestIsSequential(Hands.straightFlush2, true);
		doTestIsSequential(Hands.straightFlush3, true);
		doTestIsSequential(Hands.straightFlush4, true);
		doTestIsSequential(Hands.fourOfAKind1, false);
		doTestIsSequential(Hands.fourOfAKind2, false);
		doTestIsSequential(Hands.fourOfAKind3, false);
		doTestIsSequential(Hands.fourOfAKind4, false);
		doTestIsSequential(Hands.fullHouse1, false);
		doTestIsSequential(Hands.fullHouse2, false);
		doTestIsSequential(Hands.fullHouse3, false);
		doTestIsSequential(Hands.fullHouse4, false);
		doTestIsSequential(Hands.flush1, false);
		doTestIsSequential(Hands.flush2, false);
		doTestIsSequential(Hands.flush3, false);
		doTestIsSequential(Hands.flush4, false);
		doTestIsSequential(Hands.straight1, true);
		doTestIsSequential(Hands.straight2, true);
		doTestIsSequential(Hands.straight3, true);
		doTestIsSequential(Hands.straight4, true);
		doTestIsSequential(Hands.threeOfAKind1, false);
		doTestIsSequential(Hands.threeOfAKind2, false);
		doTestIsSequential(Hands.threeOfAKind3, false);
		doTestIsSequential(Hands.threeOfAKind4, false);
		doTestIsSequential(Hands.twoPair1, false);
		doTestIsSequential(Hands.twoPair2, false);
		doTestIsSequential(Hands.onePair1, false);
		doTestIsSequential(Hands.onePair2, false);
		doTestIsSequential(Hands.onePair3, false);
		doTestIsSequential(Hands.onePair4, false);
		doTestIsSequential(Hands.highCards1, false);
		doTestIsSequential(Hands.highCards2, false);
		doTestIsSequential(Hands.highCards3, false);
		doTestIsSequential(Hands.highCards4, false);
	}
	/**
	 * {@link #isSequential()}�p�̓������\�b�h
	 * @param hand ��D
	 * @param expected �\�z�l
	 */
	private void doTestIsSequential(Hand hand, boolean expected) {
		assertThat(hand.isSequential(), is(expected));
	}

	/** {@link Hand#isOfAKind(int)}�̃e�X�g */
	@Test
	public void testIsOfAKind() {
		doTestIsOfAKind(Hands.fiveOfAKind1, 0, 0, 0, 1, 0);
		doTestIsOfAKind(Hands.fiveOfAKind2, 0, 0, 1, 0, 0);
		doTestIsOfAKind(Hands.royalFlush1, 5, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.royalFlush2, 5, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.royalFlush3, 4, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.royalFlush4, 3, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.straightFlush1, 5, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.straightFlush2, 5, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.straightFlush3, 4, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.straightFlush4, 3, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.fourOfAKind1, 1, 0, 0, 1, 0);
		doTestIsOfAKind(Hands.fourOfAKind2, 1, 0, 0, 1, 0);
		doTestIsOfAKind(Hands.fourOfAKind3, 1, 0, 1, 0, 0);
		doTestIsOfAKind(Hands.fourOfAKind4, 1, 1, 0, 0, 0);
		doTestIsOfAKind(Hands.fullHouse1, 0, 1, 1, 0, 0);
		doTestIsOfAKind(Hands.fullHouse2, 0, 1, 1, 0, 0);
		doTestIsOfAKind(Hands.fullHouse3, 0, 2, 0, 0, 0);
		doTestIsOfAKind(Hands.fullHouse4, 0, 2, 0, 0, 0);
		doTestIsOfAKind(Hands.flush1, 5, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.flush2, 5, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.flush3, 4, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.flush4, 3, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.straight1, 5, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.straight2, 5, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.straight3, 4, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.straight4, 3, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.threeOfAKind1, 2, 0, 1, 0, 0);
		doTestIsOfAKind(Hands.threeOfAKind2, 2, 0, 1, 0, 0);
		doTestIsOfAKind(Hands.threeOfAKind3, 2, 1, 0, 0, 0);
		doTestIsOfAKind(Hands.threeOfAKind4, 3, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.twoPair1, 1, 2, 0, 0, 0);
		doTestIsOfAKind(Hands.twoPair2, 1, 2, 0, 0, 0);
		doTestIsOfAKind(Hands.onePair1, 3, 1, 0, 0, 0);
		doTestIsOfAKind(Hands.onePair2, 3, 1, 0, 0, 0);
		doTestIsOfAKind(Hands.onePair3, 4, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.onePair4, 4, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.highCards1, 5, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.highCards2, 5, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.highCards3, 5, 0, 0, 0, 0);
		doTestIsOfAKind(Hands.highCards4, 5, 0, 0, 0, 0);
	}
	/**
	 * {@link #testIsOfAKind()}�p�̓������\�b�h
	 * @param hand ��D
	 * @param results �\�z�l(n�Ԗڂ̒l���A�u����������n���̑g�̐��v���Ӗ�����)
	 */
	private void doTestIsOfAKind(Hand hand, int... results) {
		for (int i = 0; i < results.length; i++) {
			int occurrence = hand.isOfAKind(i + 1);
			if (results[i] != -1) {
				assertThat(occurrence, is(results[i]));
			}
		}
	}

	/** {@link Hand#isSequentialFrom(int)}�̃e�X�g */
	@Test
	public void testIsSequentialFrom() {
		doTestIsSequentialFrom(Hands.fiveOfAKind1, -1);
		doTestIsSequentialFrom(Hands.fiveOfAKind2, -1);
		doTestIsSequentialFrom(Hands.royalFlush1, 10);
		doTestIsSequentialFrom(Hands.royalFlush2, 10);
		doTestIsSequentialFrom(Hands.royalFlush3, 10);
		doTestIsSequentialFrom(Hands.royalFlush4, 10);
		doTestIsSequentialFrom(Hands.straightFlush1, 3);
		doTestIsSequentialFrom(Hands.straightFlush2, 9);
		doTestIsSequentialFrom(Hands.straightFlush3, 3, 4);
		doTestIsSequentialFrom(Hands.straightFlush4, 8);
		doTestIsSequentialFrom(Hands.fourOfAKind1, -1);
		doTestIsSequentialFrom(Hands.fourOfAKind2, -1);
		doTestIsSequentialFrom(Hands.fourOfAKind3, -1);
		doTestIsSequentialFrom(Hands.fourOfAKind4, -1);
		doTestIsSequentialFrom(Hands.fullHouse1, -1);
		doTestIsSequentialFrom(Hands.fullHouse2, -1);
		doTestIsSequentialFrom(Hands.fullHouse3, -1);
		doTestIsSequentialFrom(Hands.fullHouse4, -1);
		doTestIsSequentialFrom(Hands.flush1, -1);
		doTestIsSequentialFrom(Hands.flush2, -1);
		doTestIsSequentialFrom(Hands.flush3, -1);
		doTestIsSequentialFrom(Hands.flush4, -1);
		doTestIsSequentialFrom(Hands.straight1, 2);
		doTestIsSequentialFrom(Hands.straight2, 8);
		doTestIsSequentialFrom(Hands.straight3, 2);
		doTestIsSequentialFrom(Hands.straight4, 7, 8);
		doTestIsSequentialFrom(Hands.threeOfAKind1, -1);
		doTestIsSequentialFrom(Hands.threeOfAKind2, -1);
		doTestIsSequentialFrom(Hands.threeOfAKind3, -1);
		doTestIsSequentialFrom(Hands.threeOfAKind4, -1);
		doTestIsSequentialFrom(Hands.twoPair1, -1);
		doTestIsSequentialFrom(Hands.twoPair2, -1);
		doTestIsSequentialFrom(Hands.onePair1, -1);
		doTestIsSequentialFrom(Hands.onePair2, -1);
		doTestIsSequentialFrom(Hands.onePair3, -1);
		doTestIsSequentialFrom(Hands.onePair4, -1);
		doTestIsSequentialFrom(Hands.highCards1, -1);
		doTestIsSequentialFrom(Hands.highCards2, -1);
		doTestIsSequentialFrom(Hands.highCards3, -1);
		doTestIsSequentialFrom(Hands.highCards4, -1);
	}
	/**
	 * {@link #testIsSequentialFrom()}�p�̓������\�b�h
	 * @param hand ��D
	 * @param froms �A�����鐔�l�̊J�n�l�i�ŏ��l�j
	 */
	private void doTestIsSequentialFrom(Hand hand, int... froms) {
		for (int i = 2; i <= 10; i++) {
			if (hand.isSequential()) {
				if (contains(froms, i))
					assertTrue(MessageFormat.format("{0} should be sequential from {1}", hand, i), hand.isSequentialFrom(i));
				else
					assertFalse(MessageFormat.format("{0} should not be sequential from {1}", hand, i), hand.isSequentialFrom(i));
			}
		}
	}

	/** {@link Hand#hasNumber(int)}�̃e�X�g */
	@Test
	public void testHasNumber() {
		doTestHasNumber(Hands.fiveOfAKind1, 6);
		doTestHasNumber(Hands.fiveOfAKind2, 8);
		doTestHasNumber(Hands.royalFlush1, 10, 11, 12, 13, 14);
		doTestHasNumber(Hands.royalFlush2, 10, 11, 12, 13, 14);
		doTestHasNumber(Hands.royalFlush3, 11, 12, 13, 14);
		doTestHasNumber(Hands.royalFlush4, 10, 11, 14);
		doTestHasNumber(Hands.straightFlush1, 3, 4, 5, 6, 7);
		doTestHasNumber(Hands.straightFlush2, 9, 10, 11, 12, 13);
		doTestHasNumber(Hands.straightFlush3, 4, 5, 6, 7);
		doTestHasNumber(Hands.straightFlush4, 8, 10, 12);
		doTestHasNumber(Hands.fourOfAKind1, 10, 12);
		doTestHasNumber(Hands.fourOfAKind2, 8, 12);
		doTestHasNumber(Hands.fourOfAKind3, 10, 12);
		doTestHasNumber(Hands.fourOfAKind4, 8, 12);
		doTestHasNumber(Hands.fullHouse1, 4, 12);
		doTestHasNumber(Hands.fullHouse2, 3, 14);
		doTestHasNumber(Hands.fullHouse3, 4, 12);
		doTestHasNumber(Hands.fullHouse4, 3, 14);
		doTestHasNumber(Hands.flush1, 3, 5, 10, 13, 14);
		doTestHasNumber(Hands.flush2, 2, 4, 8, 9, 12);
		doTestHasNumber(Hands.flush3, 3, 5, 13, 14);
		doTestHasNumber(Hands.flush4, 4, 8, 9);
		doTestHasNumber(Hands.straight1, 2, 3, 4, 5, 6);
		doTestHasNumber(Hands.straight2, 8, 9, 10, 11, 12);
		doTestHasNumber(Hands.straight3, 2, 3, 5, 6);
		doTestHasNumber(Hands.straight4, 8, 9, 11);
		doTestHasNumber(Hands.threeOfAKind1, 8, 9, 12);
		doTestHasNumber(Hands.threeOfAKind2, 2, 13, 14);
		doTestHasNumber(Hands.threeOfAKind3, 8, 9, 12);
		doTestHasNumber(Hands.threeOfAKind4, 2, 13, 14);
		doTestHasNumber(Hands.twoPair1, 8, 9, 12);
		doTestHasNumber(Hands.twoPair2, 4, 8, 14);
		doTestHasNumber(Hands.onePair1, 4, 8, 9, 11);
		doTestHasNumber(Hands.onePair2, 2, 6, 7, 12);
		doTestHasNumber(Hands.onePair3, 2, 7, 12, 13);
		doTestHasNumber(Hands.onePair4, 8, 11, 13, 14);
		doTestHasNumber(Hands.highCards1, 2, 6, 7, 12, 13);
		doTestHasNumber(Hands.highCards2, 2, 3, 6, 12, 14);
		doTestHasNumber(Hands.highCards3, 2, 3, 4, 5, 7);
		doTestHasNumber(Hands.highCards4, 8, 11, 12, 13, 14);
	}
	/**
	 * {@link #testHasNumber()}�p�̓������\�b�h
	 * @param hand ��D
	 * @param numbers ��D�������Ă��邱�Ƃ����҂���鐔��
	 */
	private void doTestHasNumber(Hand hand, int... numbers) {
		for (int i = 2; i <= 14; i++) {
			if (contains(numbers, i))
				assertTrue(MessageFormat.format("{0} should have {1}", hand, i), hand.hasNumber(i));
			else
				assertFalse(MessageFormat.format("{0} should not have {1}", hand, i), hand.hasNumber(i));
		}
	}

	/** {@link Hand#getJoker()}�̃e�X�g */
	@Test
	public void testGetJoker() {
		doTestGetJoker(Hands.fiveOfAKind1, 1);
		doTestGetJoker(Hands.fiveOfAKind2, 2);
		doTestGetJoker(Hands.royalFlush1, 0);
		doTestGetJoker(Hands.royalFlush2, 0);
		doTestGetJoker(Hands.royalFlush3, 1);
		doTestGetJoker(Hands.royalFlush4, 2);
		doTestGetJoker(Hands.straightFlush1, 0);
		doTestGetJoker(Hands.straightFlush2, 0);
		doTestGetJoker(Hands.straightFlush3, 1);
		doTestGetJoker(Hands.straightFlush4, 2);
		doTestGetJoker(Hands.fourOfAKind1, 0);
		doTestGetJoker(Hands.fourOfAKind2, 0);
		doTestGetJoker(Hands.fourOfAKind3, 1);
		doTestGetJoker(Hands.fourOfAKind4, 2);
		doTestGetJoker(Hands.fullHouse1, 0);
		doTestGetJoker(Hands.fullHouse2, 0);
		doTestGetJoker(Hands.fullHouse3, 1);
		doTestGetJoker(Hands.fullHouse4, 1);
		doTestGetJoker(Hands.flush1, 0);
		doTestGetJoker(Hands.flush2, 0);
		doTestGetJoker(Hands.flush3, 1);
		doTestGetJoker(Hands.flush4, 2);
		doTestGetJoker(Hands.straight1, 0);
		doTestGetJoker(Hands.straight2, 0);
		doTestGetJoker(Hands.straight3, 1);
		doTestGetJoker(Hands.straight4, 2);
		doTestGetJoker(Hands.threeOfAKind1, 0);
		doTestGetJoker(Hands.threeOfAKind2, 0);
		doTestGetJoker(Hands.threeOfAKind3, 1);
		doTestGetJoker(Hands.threeOfAKind4, 2);
		doTestGetJoker(Hands.twoPair1, 0);
		doTestGetJoker(Hands.twoPair2, 0);
		doTestGetJoker(Hands.onePair1, 0);
		doTestGetJoker(Hands.onePair2, 0);
		doTestGetJoker(Hands.onePair3, 1);
		doTestGetJoker(Hands.onePair4, 1);
		doTestGetJoker(Hands.highCards1, 0);
		doTestGetJoker(Hands.highCards2, 0);
		doTestGetJoker(Hands.highCards3, 0);
		doTestGetJoker(Hands.highCards4, 0);
	}
	/**
	 * {@link #testGetJoker()}�p�̓������\�b�h
	 * @param hand ��D
	 * @param count �\�z�l
	 */
	private void doTestGetJoker(Hand hand, int count) {
		assertThat(hand.getJoker(), is(count));
	}

	/** {@link Hand#getHighestNumber()}�̃e�X�g */
	@Test
	public void testGetHighestNumber() {
		doTestGetHighestNumber(Hands.fiveOfAKind1, 6);
		doTestGetHighestNumber(Hands.fiveOfAKind2, 8);
		doTestGetHighestNumber(Hands.royalFlush1, 14);
		doTestGetHighestNumber(Hands.royalFlush2, 14);
		doTestGetHighestNumber(Hands.royalFlush3, 14);
		doTestGetHighestNumber(Hands.royalFlush4, 14);
		doTestGetHighestNumber(Hands.straightFlush1, 7);
		doTestGetHighestNumber(Hands.straightFlush2, 13);
		doTestGetHighestNumber(Hands.straightFlush3, 7);
		doTestGetHighestNumber(Hands.straightFlush4, 12);
		doTestGetHighestNumber(Hands.fourOfAKind1, 12);
		doTestGetHighestNumber(Hands.fourOfAKind2, 12);
		doTestGetHighestNumber(Hands.fourOfAKind3, 12);
		doTestGetHighestNumber(Hands.fourOfAKind4, 12);
		doTestGetHighestNumber(Hands.fullHouse1, 12);
		doTestGetHighestNumber(Hands.fullHouse2, 14);
		doTestGetHighestNumber(Hands.fullHouse3, 12);
		doTestGetHighestNumber(Hands.fullHouse4, 14);
		doTestGetHighestNumber(Hands.flush1, 14);
		doTestGetHighestNumber(Hands.flush2, 12);
		doTestGetHighestNumber(Hands.flush3, 14);
		doTestGetHighestNumber(Hands.flush4, 9);
		doTestGetHighestNumber(Hands.straight1, 6);
		doTestGetHighestNumber(Hands.straight2, 12);
		doTestGetHighestNumber(Hands.straight3, 6);
		doTestGetHighestNumber(Hands.straight4, 11);
		doTestGetHighestNumber(Hands.threeOfAKind1, 12);
		doTestGetHighestNumber(Hands.threeOfAKind2, 14);
		doTestGetHighestNumber(Hands.threeOfAKind3, 12);
		doTestGetHighestNumber(Hands.threeOfAKind4, 14);
		doTestGetHighestNumber(Hands.twoPair1, 12);
		doTestGetHighestNumber(Hands.twoPair2, 14);
		doTestGetHighestNumber(Hands.onePair1, 11);
		doTestGetHighestNumber(Hands.onePair2, 12);
		doTestGetHighestNumber(Hands.onePair3, 13);
		doTestGetHighestNumber(Hands.onePair4, 14);
		doTestGetHighestNumber(Hands.highCards1, 13);
		doTestGetHighestNumber(Hands.highCards2, 14);
		doTestGetHighestNumber(Hands.highCards3, 7);
		doTestGetHighestNumber(Hands.highCards4, 14);
	}
	/**
	 * {@link #testGetHighestNumber()}�p�̓������\�b�h
	 * @param hand ��D
	 * @param highest �\�z�l
	 */
	private void doTestGetHighestNumber(Hand hand, int highest) {
		assertThat(hand.getHighestNumber(), is(highest));
	}

	/** {@link Hand#getLowestNumber()}�̃e�X�g */
	@Test
	public void testGetLowestNumber() {
		doTestGetLowestNumber(Hands.fiveOfAKind1, 6);
		doTestGetLowestNumber(Hands.fiveOfAKind2, 8);
		doTestGetLowestNumber(Hands.royalFlush1, 10);
		doTestGetLowestNumber(Hands.royalFlush2, 10);
		doTestGetLowestNumber(Hands.royalFlush3, 11);
		doTestGetLowestNumber(Hands.royalFlush4, 10);
		doTestGetLowestNumber(Hands.straightFlush1, 3);
		doTestGetLowestNumber(Hands.straightFlush2, 9);
		doTestGetLowestNumber(Hands.straightFlush3, 4);
		doTestGetLowestNumber(Hands.straightFlush4, 8);
		doTestGetLowestNumber(Hands.fourOfAKind1, 10);
		doTestGetLowestNumber(Hands.fourOfAKind2, 8);
		doTestGetLowestNumber(Hands.fourOfAKind3, 10);
		doTestGetLowestNumber(Hands.fourOfAKind4, 8);
		doTestGetLowestNumber(Hands.fullHouse1, 4);
		doTestGetLowestNumber(Hands.fullHouse2, 3);
		doTestGetLowestNumber(Hands.fullHouse3, 4);
		doTestGetLowestNumber(Hands.fullHouse4, 3);
		doTestGetLowestNumber(Hands.flush1, 3);
		doTestGetLowestNumber(Hands.flush2, 2);
		doTestGetLowestNumber(Hands.flush3, 3);
		doTestGetLowestNumber(Hands.flush4, 4);
		doTestGetLowestNumber(Hands.straight1, 2);
		doTestGetLowestNumber(Hands.straight2, 8);
		doTestGetLowestNumber(Hands.straight3, 2);
		doTestGetLowestNumber(Hands.straight4, 8);
		doTestGetLowestNumber(Hands.threeOfAKind1, 8);
		doTestGetLowestNumber(Hands.threeOfAKind2, 2);
		doTestGetLowestNumber(Hands.threeOfAKind3, 8);
		doTestGetLowestNumber(Hands.threeOfAKind4, 2);
		doTestGetLowestNumber(Hands.twoPair1, 8);
		doTestGetLowestNumber(Hands.twoPair2, 4);
		doTestGetLowestNumber(Hands.onePair1, 4);
		doTestGetLowestNumber(Hands.onePair2, 2);
		doTestGetLowestNumber(Hands.onePair3, 2);
		doTestGetLowestNumber(Hands.onePair4, 8);
		doTestGetLowestNumber(Hands.highCards1, 2);
		doTestGetLowestNumber(Hands.highCards2, 2);
		doTestGetLowestNumber(Hands.highCards3, 2);
		doTestGetLowestNumber(Hands.highCards4, 8);
	}
	/**
	 * {@link #testGetLowestNumber()}�p�̓������\�b�h
	 * @param hand ��D
	 * @param highest �\�z�l
	 */
	private void doTestGetLowestNumber(Hand hand, int highest) {
		assertThat(hand.getLowestNumber(), is(highest));
	}


	/**
	 * �z��̒��Ɏw�肵���v�f�����݂��邩�ǂ������擾����
	 * @param target �Ώۂ̔z��
	 * @param toFind �T�������v�f
	 * @return
	 */
	private boolean contains(int[] target, int toFind) {
		for (int i : target) {
			if (i == toFind) return true;
		}
		return false;
	}
}

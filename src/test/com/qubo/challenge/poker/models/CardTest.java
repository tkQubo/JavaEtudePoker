package test.com.qubo.challenge.poker.models;

import static com.qubo.challenge.poker.models.Suit.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.text.MessageFormat;

import org.junit.Test;

import com.qubo.challenge.poker.models.Card;
import com.qubo.challenge.poker.models.CardException;
import com.qubo.challenge.poker.models.Suit;

/**
 * {@link Card}�p�̃e�X�g���`�����N���X
 * @author Qubo
 */
public class CardTest {
	/** {@link Card#Card(Suit, int)}�̃e�X�g */
	@Test
	public void testCard() {
		doTestCard(Heart, 2, null);
		doTestCard(Spade, 8, null);
		doTestCard(Club, 13, null);
		doTestCard(Diamond, 14, null);
		doTestCard(Joker, Card.RAW_VALUE_JOKER, null);
		doTestCard(Joker, -8, null);
		doTestCard(null, 5, Card.ERROR_SUIT_NULL);
		doTestCard(Heart, 1, Card.ERROR_NUMBER_OUT_OF_RANGE);
		doTestCard(Diamond, 15, Card.ERROR_NUMBER_OUT_OF_RANGE);
	}
	/**
	 * {@link #testCard()}�p�̓������\�b�h
	 * @param suit �}�[�N
	 * @param number ���l
	 * @param errorMessage �G���[���b�Z�[�W�i�G���[�̔��������҂��Ă���ꍇ�̂݁j
	 */
	private void doTestCard(Suit suit, int number, String errorMessage) {
		try {
			new Card(suit, number);
		} catch (CardException e) {
			if (errorMessage != null && errorMessage.length() > 0) {
				assertThat(e.getMessage(), is(errorMessage));
			} else {
				fail(e.getMessage());
			}
		}
	}

	/** {@link Card#parse(String)}�̃e�X�g */
	@Test
	public void testParse() {
		doTestParse("H2", 2, Heart);
		doTestParse("S5", 5, Spade);
		doTestParse("D10", 10, Diamond);
		doTestParse("CJ", 11, Club);
		doTestParse("HQ", 12, Heart);
		doTestParse("SK", 13, Spade);
		doTestParse("DA", 14, Diamond);
		doTestParse(" 8", Card.RAW_VALUE_JOKER, Joker);
		doTestParse("  ", Card.RAW_VALUE_JOKER, Joker);
		doFailTestParse("ZT", "Z");
		doFailTestParse("CT", "T");
		doFailTestParse("S123456", "123456");
	}
	/**
	 * {@link #testParse()}�p�̓������\�b�h
	 * @param failedFormat �Ԉ�����t�H�[�}�b�g
	 * @param failedString �Ԉ�����ӏ�
	 */
	private void doFailTestParse(String failedFormat, String failedString) {
		try {
			Card.parse(failedFormat);
		} catch (CardException e) {
			String message = MessageFormat.format(Card.ERROR_PARSE, failedFormat, failedString);
			assertThat(e.getMessage(), is(message));
		}
	}
	/**
	 * {@link #testParse()}�p�̓������\�b�h
	 * @param format �t�H�[�}�b�g
	 * @param num �\�z����鐔�l
	 * @param suit �\�z�����}�[�N
	 */
	private void doTestParse(String format, int num, Suit suit) {
		try {
			Card card = Card.parse(format);
			assertThat(card.getRawNumber(), is(num));
			assertThat(card.getSuit(), is(suit));
		} catch (CardException e) {
			fail(e.getMessage());
		}
	}


	/** {@link Card#getSuit()}�̃e�X�g */
	@Test
	public void testGetSuit() {
		try {
			Card heart03 = new Card(Heart, 3);
			Card dia03 = new Card(Diamond, 3);
			Card spade13 = new Card(Spade, 13);
			Card club14 = new Card(Club, 14);
			Card joker1 = new Card(Joker, Card.RAW_VALUE_JOKER);
			Card joker2 = new Card(Joker, 10);

			assertThat(heart03.getSuit(), is(Heart));
			assertThat(dia03.getSuit(), is(Diamond));
			assertThat(spade13.getSuit(), is(Spade));
			assertThat(club14.getSuit(), is(Club));
			assertThat(joker1.getSuit(), is(Joker));
			assertThat(joker2.getSuit(), is(Joker));
		} catch (CardException e) {
			fail(e.getMessage());
		}
	}
	/** {@link Card#getSuitSymbol()}�̃e�X�g */
	@Test
	public void testGetSuitSymbol() {
		try {
			Card heart03 = new Card(Heart, 3);
			Card dia03 = new Card(Diamond, 3);
			Card spade13 = new Card(Spade, 13);
			Card club14 = new Card(Club, 14);
			Card joker1 = new Card(Joker, Card.RAW_VALUE_JOKER);
			Card joker2 = new Card(Joker, 10);

			assertThat(heart03.getSuitSymbol(), is("" + Suit.SYMBOL_HEART));
			assertThat(dia03.getSuitSymbol(), is("" + Suit.SYMBOL_DIAMOND));
			assertThat(spade13.getSuitSymbol(), is("" + Suit.SYMBOL_SPADE));
			assertThat(club14.getSuitSymbol(), is("" + Suit.SYMBOL_CLUB));
			assertThat(joker1.getSuitSymbol(), is("" + Suit.SYMBOL_JOKER));
			assertThat(joker2.getSuitSymbol(), is("" + Suit.SYMBOL_JOKER));
		} catch (CardException e) {
			fail(e.getMessage());
		}
	}
	/** {@link Card#getSuitSymbol2Bytes()}�̃e�X�g */
	@Test
	public void testGetSuitSymbol2Bytes() {
		try {
			Card heart03 = new Card(Heart, 3);
			Card dia03 = new Card(Diamond, 3);
			Card spade13 = new Card(Spade, 13);
			Card club14 = new Card(Club, 14);
			Card joker1 = new Card(Joker, Card.RAW_VALUE_JOKER);
			Card joker2 = new Card(Joker, 10);

			assertThat(heart03.getSuitSymbol2Bytes(), is("�g"));
			assertThat(dia03.getSuitSymbol2Bytes(), is("�c"));
			assertThat(spade13.getSuitSymbol2Bytes(), is("�r"));
			assertThat(club14.getSuitSymbol2Bytes(), is("�b"));
			assertThat(joker1.getSuitSymbol2Bytes(), is("�@"));
			assertThat(joker2.getSuitSymbol2Bytes(), is("�@"));
		} catch (CardException e) {
			fail(e.getMessage());
		}
	}
	/** {@link Card#getRawNumber()}�̃e�X�g */
	@Test
	public void testGetRawNumber() {
		doTestGetRawNumber("H2", 2);
		doTestGetRawNumber("D4", 4);
		doTestGetRawNumber("S8", 8);
		doTestGetRawNumber("C10", 10);
		doTestGetRawNumber("SJ", 11);
		doTestGetRawNumber("CQ", 12);
		doTestGetRawNumber("DK", 13);
		doTestGetRawNumber("HA", 14);
		doTestGetRawNumber("  ", Card.RAW_VALUE_JOKER);
	}
	/** {@link #testParse()}�p�̓������\�b�h
	 * @param format �t�H�[�}�b�g
	 * @param rawNumber �\�z�l
	 */
	private void doTestGetRawNumber(String format, int rawNumber) {
		try {
			Card card = Card.parse(format);
			assertThat(card.getRawNumber(), is(rawNumber));
		} catch (CardException e) {
			fail(e.getMessage());
		}
	}
	/** {@link Card#getNumberSymbol()}�̃e�X�g */
	@Test
	public void testGetNumberSymbol() {
		doTestGetNumberSymbol("H2", "2");
		doTestGetNumberSymbol("D5", "5");
		doTestGetNumberSymbol("S8", "8");
		doTestGetNumberSymbol("D10", "10");
		doTestGetNumberSymbol("HJ", "" + Card.SYMBOL_JACK);
		doTestGetNumberSymbol("SQ", "" + Card.SYMBOL_QUEEN);
		doTestGetNumberSymbol("HK", "" + Card.SYMBOL_KING);
		doTestGetNumberSymbol("CA", "" + Card.SYMBOL_ACE);
		doTestGetNumberSymbol("  ", "" + Card.SYMBOL_JOKER);
	}
	/**
	 * {@link #testGetNumberSymbol()}�p�̓������\�b�h
	 * @param format �t�H�[�}�b�g
	 * @param numberSymbol �\�z�l
	 */
	private void doTestGetNumberSymbol(String format, String numberSymbol) {
		try {
			Card card = Card.parse(format);
			assertThat(card.getNumberSymbol(), is(numberSymbol));
		} catch (CardException e) {
			fail(e.getMessage());
		}
	}
	/** {@link Card#getNumberSymbol2Bytes()}�̃e�X�g */
	@Test
	public void testGetNumberSymbol2Bytes() {
		doTestGetNumberSymbol2Bytes("H2", "�Q");
		doTestGetNumberSymbol2Bytes("S7", "�V");
		doTestGetNumberSymbol2Bytes("D9", "�X");
		doTestGetNumberSymbol2Bytes("C10", "10");
		doTestGetNumberSymbol2Bytes("HJ", "�i");
		doTestGetNumberSymbol2Bytes("SQ", "�p");
		doTestGetNumberSymbol2Bytes("CK", "�j");
		doTestGetNumberSymbol2Bytes("HA", "�`");
		doTestGetNumberSymbol2Bytes("  ", "�@");
	}
	/**
	 * {@link #testGetNumberSymbol2Bytes()}�p�̓������\�b�h
	 * @param format �t�H�[�}�b�g
	 * @param numberSymbol �\�z�l
	 */
	private void doTestGetNumberSymbol2Bytes(String format, String numberSymbol) {
		try {
			Card card = Card.parse(format);
			assertThat(card.getNumberSymbol2Bytes(), is(numberSymbol));
		} catch (CardException e) {
			fail(e.getMessage());
		}
	}
	/** {@link Card#toString()}�̃e�X�g */
	@Test
	public void testToString() {
		doTestToString("H2");
		doTestToString("D6");
		doTestToString("S9");
		doTestToString("C10");
		doTestToString("HJ");
		doTestToString("CQ");
		doTestToString("SK");
		doTestToString("CA");
		doTestToString("  ");
	}
	/**
	 * {@link #testToString()}�p�̓������\�b�h
	 * @param string �\�z�l
	 */
	private void doTestToString(String string) {
		try {
			Card card = Card.parse(string);
			assertThat(card.toString(), is(string));
		} catch (CardException e) {
			fail(e.getMessage());
		}
	}

}

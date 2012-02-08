package test.com.qubo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.qubo.Utils;

/**
 * {@link Utils}�p�̃e�X�g���`�����N���X
 * @author Qubo
 */
public class UtilsTest {

	/** {@link Utils#toFullWidth(char)}�̃e�X�g */
	@Test
	public void testToFullWidth() {
		doTestToFullWidth('0', '�O');
		doTestToFullWidth('1', '�P');
		doTestToFullWidth('2', '�Q');
		doTestToFullWidth('3', '�R');
		doTestToFullWidth('4', '�S');
		doTestToFullWidth('5', '�T');
		doTestToFullWidth('6', '�U');
		doTestToFullWidth('7', '�V');
		doTestToFullWidth('8', '�W');
		doTestToFullWidth('9', '�X');
		doTestToFullWidth('a', '��');
		doTestToFullWidth('b', '��');
		doTestToFullWidth('c', '��');
		doTestToFullWidth('j', '��');
		doTestToFullWidth('q', '��');
		doTestToFullWidth('k', '��');
		doTestToFullWidth('A', '�`');
		doTestToFullWidth('B', '�a');
		doTestToFullWidth('C', '�b');
		doTestToFullWidth('D', '�c');
		doTestToFullWidth('J', '�i');
		doTestToFullWidth('Q', '�p');
		doTestToFullWidth('K', '�j');
		doTestToFullWidth('+', '�{');
		doTestToFullWidth('*', '��');
		doTestToFullWidth('^', '�O');
		doTestToFullWidth(' ', '�@');
	}
	/**
	 * {@link #testToFullWidth()}�p�̓������\�b�h
	 * @param input ���͒l
	 * @param exp �\�z�l
	 */
	private void doTestToFullWidth(char input, char exp) {
		char result = Utils.toFullWidth(input);
		assertThat(result, is(exp));
	}

	/** {@link Utils#rpad(String, int)}�̃e�X�g */
	@Test
	public void testRpad() {
		doTestPad("", 4, true, "    ");
		doTestPad("test", 10, true, "test      ");
		doTestPad("�S�p", 6, true, "�S�p  ");
		doTestPad("ThisIsATest", 8, true, "ThisI...");
		doTestPad("�S�p�e�X�g", 6, true, "�S... ");
	}
	/** {@link Utils#lpad(String, int)}�̃e�X�g */
	@Test
	public void testLpad() {
		doTestPad("", 4, false, "    ");
		doTestPad("test", 10, false, "      test");
		doTestPad("�S�p", 6, false, "  �S�p");
		doTestPad("ThisIsATest", 8, false, "ThisI...");
		doTestPad("�S�p�e�X�g", 6, false, " �S...");
	}
	/**
	 * {@link #testRpad()}�p�̓������\�b�h
	 * @param text ������
	 * @param length ������
	 * @param rpad {@code true}�̏ꍇ��{@link Utils#rpad(String, int)}���A{@code false}�̏ꍇ��{@link Utils#lpad(String, int)}���Ăяo��
	 * @param expected �\�z������
	 */
	private void doTestPad(String text, int length, boolean rpad, String expected) {
		assertThat(rpad ? Utils.rpad(text, length) : Utils.lpad(text, length), is(expected));
	}
	/** {@link Utils#isBlank(String)}�̃e�X�g */
	@Test
	public void testIsBlank() {
		assertTrue(Utils.isBlank(null));
		assertTrue(Utils.isBlank(""));
		assertTrue(Utils.isBlank("        "));
		assertFalse(Utils.isBlank("a"));
		assertFalse(Utils.isBlank("test test"));
		assertFalse(Utils.isBlank("�@�@�@�@")); // �S�p��Ή��I
	}

}

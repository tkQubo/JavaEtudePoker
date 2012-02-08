package com.qubo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;

/**
 * �֗��֐����W�߂��N���X
 * @author Qubo
 */
public class Utils {
	/** �S�p�������o�̂��߂̃G���R�[�f�B���O: {@code "Windows-31J"} */
	private static final String WINDOWS31J = "Windows-31J";
	/** {@link #pad(String, int, boolean)}�œ����I�Ɏg���ȗ��L��: {@code "..."} */
	private static final String ELLIPSIS = "...";
	/** {@link #pad(String, int, boolean)}�œ����I�Ɏg���p�f�B���O���� */
	private static final String PADDING = "                                                                                ";

	/** ���p�L���z��(���p�X�y�[�X������) */
	private static final char[] SIGNS = {
		'!', '#', '$', '%', '&', '(',
		')', '*', '+', ',', '-', '.',
		'/', ':', ';', '<', '=', '>',
		'?', '@', '[', ']', '^', '_',
		'{', '|', '}'
	};
	/** ���p�����́A�����R�[�h��͈̔͊J�n�ʒu: {@code 0x39} */
	private static final int NUM_HALF_END = 0x39;
	/** ���p�����́A�����R�[�h��͈̔͏I���ʒu: {@code 0x30} */
	private static final int NUM_HALF_START = 0x30;
	/** ���p�啶���A���t�@�x�b�g�́A�����R�[�h��͈̔͊J�n�ʒu: {@code 0x41} */
	private static final int CAPITAL_LETTER_START = 0x41;
	/** ���p�啶���A���t�@�x�b�g�́A�����R�[�h��͈̔͏I���ʒu: {@code 0x5A} */
	private static final int CAPITAL_LETTER_END = 0x5A;
	/**���p�������A���t�@�x�b�g�́A�����R�[�h��͈̔͊J�n�ʒu: {@code 0x61} */
	private static final int SMALL_LETTER_START = 0x61;
	/** ���p�������A���t�@�x�b�g�́A�����R�[�h��͈̔͏I���ʒu: {@code 0x7A} */
	private static final int SMALL_LETTER_END = 0x7A;
	/** �����R�[�h��ł́A�C�ӂ̔��p�p�����Ƃ���ɑΉ�����S�p�p�����܂ł̍�: {@code 0xFEE0} */
	private static final int OFFSET_TO_FULL_WIDTH = '�`' - 'A';

	/**
	 * ���p�̉p���L����S�p�ɕϊ�����B���p�p���L���ȊO�̂��̂��^����ꂽ�ꍇ�A���̂܂܈����̒l��Ԃ��B
	 * @param value ���p�p���L��
	 * @return {@code value}�ɑΉ�����S�p�p���L��
	 */
	public static char toFullWidth(char value) {
		if (isNumber((int) value) || isCapitalLetter((int) value) || isSmallLetter((int) value) || isSign(value)) {
			return (char) ((int) value + OFFSET_TO_FULL_WIDTH);
		} else if (value == ' ') { // ���p�X�y�[�X�̏ꍇ
			return '�@';
		} else {
			return value;
		}
	}
	/**
	 * �^����ꂽ�������A���p�������A���t�@�x�b�g���ǂ������擾����
	 * @param c
	 * @return {@code c}�����p�������A���t�@�x�b�g���ǂ���
	 */
	private static boolean isSmallLetter(int c) {
		return c >= SMALL_LETTER_START && c <= SMALL_LETTER_END;
	}
	/**
	 * �^����ꂽ�������A���p�啶���A���t�@�x�b�g���ǂ������擾����
	 * @param c
	 * @return {@code c}�����p�啶���A���t�@�x�b�g���ǂ���
	 */
	private static boolean isCapitalLetter(int c) {
		return c >= CAPITAL_LETTER_START && c <= CAPITAL_LETTER_END;
	}
	/**
	 * �^����ꂽ�������A���p�������ǂ������擾����
	 * @param c
	 * @return {@code c}�����p�������ǂ���
	 */
	private static boolean isNumber(int c) {
		return c >= NUM_HALF_START && c <= NUM_HALF_END;
	}
	/**
	 * �^����ꂽ�������A���p�L�����ǂ������擾����
	 * @param c
	 * @return {@code c}�����p�L�����ǂ���
	 */
	private static boolean isSign(char c) {
		for (char sign : SIGNS) {
			if (sign == c) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �w�肳�ꂽ�����񒷂ɂȂ�܂ŁA�^����ꂽ������̉E���ɋ󔒂�ǉ����ĕԂ��B
	 * @param text ������
	 * @param totalLength ������
	 * @return �p�f�B���O���ꂽ������
	 */
	public static String rpad(String text, int totalLength) { return pad(text, totalLength, false); }
	/**
	 * �w�肳�ꂽ�����񒷂ɂȂ�܂ŁA�^����ꂽ������̍����ɋ󔒂�ǉ����ĕԂ��B
	 * @param text ������
	 * @param totalLength ������
	 * @return �p�f�B���O���ꂽ������
	 */
	public static String lpad(String text, int totalLength) { return pad(text, totalLength, true); }
	/**
	 * {@link #rpad(String, int)}�����{@link #lpad(String, int)}�p�̓����֐�
	 * @param text ������
	 * @param totalLength ������
	 * @param doLeftPad �E�l�߂����l�߂����w��
	 * @return �p�f�B���O���ꂽ������
	 */
	private static String pad(String text, int totalLength, boolean doLeftPad) {
		String result = text;
		try {
			int byteLength = text.getBytes(WINDOWS31J).length;
			if (byteLength <= totalLength) {
				String padding = PADDING.substring(0, totalLength - byteLength);
				result = doLeftPad ? padding + text : text + padding;
			} else {
				String substring = null;
				for (int endIndex = text.length(); byteLength > totalLength; endIndex--) {
					substring = text.substring(0, endIndex) + ELLIPSIS;
					byteLength = substring.getBytes(WINDOWS31J).length;
				}
				String padding = PADDING.substring(0, totalLength - byteLength);
				result = doLeftPad ? padding + substring : substring + padding;
			}
		} catch (UnsupportedEncodingException e) {
		}
		return result;
	}
	/**
	 * �R���\�[���Ń��[�U�[�ɑ΂��ĕ����̓��͂����߂�B
	 * ���͕�����{@code candidates}�Ɏw�肳�ꂽ�����z��̒��ɑ��݂��Ȃ��ꍇ�A�ēx���͂����߂�B
	 * �܂��A���[�U�[���������͂�����Enter�L�[���������ꍇ�A{@code defaultChar}�����͂Ƃ��ēK�p�����B
	 * @param candidates �������z��
	 * @param defaultChar �f�t�H���g�̕���
	 * @return ���[�U�[�̓��͕���
	 */
	public static char promptChar(char[] candidates, char defaultChar) {
		String candidatesStr = String.copyValueOf(candidates).toLowerCase();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (true) {
				String line = reader.readLine().toLowerCase();
				if (line.length() == 1) {
					if (candidatesStr.contains(line.substring(0, 1))) {
						return line.charAt(0);
					}
				} else if (line.length() == 0) {
					return defaultChar;
				}
				System.out.println("�������l����͂��Ă��������I");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("���B�s�\�ȃR�[�h�ɒB���܂����I");
	}
	/**
	 * �R���\�[���Ń��[�U�[�ɑ΂��Đ����l�̓��͂����߂�B
	 * ���l��{@code min}�`{@code max}�͈̔͊O�������ꍇ�A����ѐ����l�ȊO�����͂��ꂽ�ꍇ�A�ēx���͂����߂�B
	 * �܂��A���[�U�[���������͂�����Enter�L�[���������ꍇ�A{@code null}��Ԃ��B
	 * @param min ���߂鐮���̍ŏ��l
	 * @param max ���߂鐮���̍ő�l
	 * @return ���[�U�[�̓��͐��l
	 */
	public static Integer promptInteger(int min, int max) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (true) {
				try {
					String line = reader.readLine();
					if (Utils.isBlank(line)) {
						return null;
					}

					int value = Integer.parseInt(line);
					if (value >= min && value <= max) {
						return value;
					} else {
						System.out.println(MessageFormat.format("{0}~{1}�͈̔͂Ő��l����͂��Ă��������I", min, max));
					}

				} catch (NumberFormatException e) {
					System.out.println("���������l����͂��Ă��������I");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("���B�s�\�ȃR�[�h�ɒB���܂����I");
	}
	/**
	 * �����񂪋󂩂ǂ������擾����
	 * @param text ���ׂ镶����
	 * @return �����񂪋󂩂ǂ���
	 */
	public static boolean isBlank(String text) { return text == null || text.trim().length() == 0; }
}

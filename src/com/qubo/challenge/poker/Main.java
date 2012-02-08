package com.qubo.challenge.poker;

import com.qubo.challenge.poker.models.CardException;

/**
 * �R���\�[���v���O�����̃G���g���|�C���g����`���ꂽ�N���X
 * @author Qubo
 */
public class Main {
	/**
	 * �G���g���|�C���g�B
	 * @param args
	 */
	public static void main(String[] args) {
		ConsoleGame game = new ConsoleGame();
		try {
			game.play();
		} catch (CardException e) {
			System.err.println(e.getMessage());
		}
	}
}

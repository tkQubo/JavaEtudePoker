package com.qubo.challenge.poker;

import com.qubo.challenge.poker.models.CardException;
import com.qubo.views.AbstractYesNoView;
import com.qubo.views.IntegerInputView;
import com.qubo.views.MenuView;
import com.qubo.views.View;
import com.qubo.views.ViewApplication;

/**
 * �R���\�[���v���O�����Ƃ��ă|�[�J�[���v���C���邽�߂̃V�X�e��
 * @author Qubo
 */
public class ConsoleGame {
	/**
	 * �|�[�J�[���v���C����
	 * @throws CardException �Q�[�����ŃG���[���������ꍇ�ɔ���
	 */
	public void play() throws CardException {
		ViewApplication.start(buildMenu());
	}

	/**
	 * �|�[�J�[�Q�[���̂��߂̃��C�����j���[�𐶐�����
	 * @return ���C�����j���[�I�u�W�F�N�g
	 */
	public View buildMenu() {
		final MenuView menu = new MenuView("���C�����j���[");

		View gameStart = new View() {
			@Override public View view() { return new GameView(menu); }
			@Override public String getName() { return "�Q�[���J�n"; }
		};
		View changeJokerCount = new IntegerInputView(menu, "�W���[�J�[�̖���", 0, 2) {
			@Override protected void setValue(int value) { Configuration.jokerCount = value; }
			@Override protected int getValue() { return Configuration.jokerCount; }
		};
		View changeChangeCount = new IntegerInputView(menu, "������", 0, 20) {
			@Override protected void setValue(int value) { Configuration.changeCount = value; }
			@Override protected int getValue() { return Configuration.changeCount; }
		};
		View resetSettings = new AbstractYesNoView("�ݒ�̃��Z�b�g", "�{���ɐݒ�����Z�b�g���܂����H", true) {
			@Override protected View view(Boolean input) {
				if (input) {
					Configuration.jokerCount = Configuration.DEFAULT_JOKERCOUNT;
					Configuration.changeCount = Configuration.DEFAULT_CHANGECOUNT;
					print("�W���[�J�[�̖�����{0}���A�����񐔂�{1}��ɖ߂��܂����B", Configuration.jokerCount, Configuration.changeCount);
				}
				return menu;
			}
		};
		View quit = new AbstractYesNoView("�I��", "�{���ɏI�����܂����H", true) {
			@Override protected View view(Boolean input) { return input ? null : menu; }
		};

		menu.addMenuItem('g', gameStart);
		menu.addMenuItem('j', changeJokerCount);
		menu.addMenuItem('c', changeChangeCount);
		menu.addMenuItem('r', resetSettings);
		menu.setDefaultMenuItem('q', quit);

		return menu;
	}
}















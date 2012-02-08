package com.qubo.views;

import java.util.ArrayList;
import java.util.List;

import com.qubo.Utils;

/**
 * ��ʑJ�ڗp�̃r���[�B
 * ���[�U�[��1�������͂����߂āA���̕����ɑΉ��������j���[���ڂ̃r���[�ɑJ�ڂ���B
 * @author Qubo
 */
public class MenuView extends AbstractView<MenuItem> {
	/** ���ʂɕ\�����郁�j���[���ڂ̍ő吔 */
	public static int MAX_RECORDS = 10;
	/** �����y�[�W�ɂȂ����ꍇ�ɁA�O�̃y�[�W�ɑJ�ڂ��邽�߂̃��j���[���� */
	private final MenuItem previousPage = new MenuItem('<', "�O�̃y�[�W", this);
	/** �����y�[�W�ɂȂ����ꍇ�ɁA���̃y�[�W�ɑJ�ڂ��邽�߂̃��j���[���� */
	private final MenuItem nextPage = new MenuItem('>', "���̃y�[�W", this);

	private final List<MenuItem> menuItems;
	private int page;
	private MenuItem defaultMenuItem;

	/**
	 * �W���̃R���X�g���N�^
	 * @param name �r���[��
	 */
	public MenuView(String name) {
		super(name);
		this.menuItems = new ArrayList<MenuItem>();
		this.page = 0;
	}
	/**
	 * ���j���[���ڂ�ǉ�����
	 * @param menuItem �ǉ����郁�j���[����
	 */
	public void addMenuItem(MenuItem menuItem) {
		menuItems.add(menuItem);
	}
	/**
	 * �^����ꂽ�������烁�j���[���ڂ𐶐����Ēǉ�����
	 * @param accessCharacter �A�N�Z�X����
	 * @param view �r���[
	 */
	public void addMenuItem(char accessCharacter, View view) {
		addMenuItem(new MenuItem(accessCharacter, view));
	}
	/**
	 * �f�t�H���g�̃��j���[���ڂ��擾����B
	 * �f�t�H���g�̃��j���[���ڂ̓r���[�̈�ԉ��ɕ\������A���[�U�[���������͂�����Enter�L�[���������ꍇ�ɓK�p�����B
	 * @return �f�t�H���g�̃��j���[����
	 */
	public MenuItem getDefaultMenuItem() {
		return defaultMenuItem;
	}
	/**
	 * �f�t�H���g�̃��j���[���ڂ�ݒ肷��B
	 * �f�t�H���g�̃��j���[���ڂ̓r���[�̈�ԉ��ɕ\������A���[�U�[���������͂�����Enter�L�[���������ꍇ�ɓK�p�����B
	 * @param defaultMenuItem �f�t�H���g�̃��j���[����
	 */
	public void setDefaultMenuItem(MenuItem defaultMenuItem) {
		this.defaultMenuItem = defaultMenuItem;
	}
	/**
	 * �^����ꂽ�������烁�j���[���ڂ𐶐����āA�f�t�H���g�̃��j���[���ڂƂ��Đݒ肷��B
	 * @param accessCharacter �A�N�Z�X����
	 * @param view �r���[
	 */
	public void setDefaultMenuItem(char accessCharacter, View view) {
		setDefaultMenuItem(new MenuItem(accessCharacter, view));
	}

	@Override
	protected final void renderBody() {
		printLine("���j���[��I�����Ă��������B");
		printLine("�������͂���Enter�L�[�������ƁA[{0}]�ɂȂ�܂��B", defaultMenuItem.getView().getName());
		printThinSeparator();

		for (int i = page * MAX_RECORDS; i < Math.min(menuItems.size(), (page + 1) * MAX_RECORDS); i++) {
			MenuItem menuItem = menuItems.get(i);
			if (menuItems.size() > MAX_RECORDS) {
				char accessCharacter = (char) ((i - page * MAX_RECORDS) + '0');
				menuItem.setAccessCharacter(accessCharacter);
			}
			printLine(menuItem.toString());
		}

		if (menuItems.size() > MAX_RECORDS) {
			printThinSeparator();
			if (hasPreviousPage()) {
				printLine(previousPage.toString());
			}
			if (hasNextPage()) {
				printLine(nextPage.toString());
			}
			printLine("    ����{0}/{1}�y�[�W�ڂ�\�����Ă��܂�", page + 1, getPageTotal());
		}
		printThinSeparator();
		printLine(defaultMenuItem.toString());
	}

	@Override
	protected final MenuItem requestUserInput() {
		char[] accessCharacters = getAccessCharacters();
		char selected = Utils.promptChar(accessCharacters, defaultMenuItem.getAccessCharacter());
		for (int i = page * MAX_RECORDS; i < Math.min(menuItems.size(), (page + 1) * MAX_RECORDS); i++) {
			MenuItem menuItem = menuItems.get(i);
			if (selected == menuItem.getAccessCharacter()) {
				return menuItem;
			}
		}

		if (selected == '<') {
			page--;
			return previousPage;
		} else if (selected == '>') {
			page++;
			return nextPage;
		} else if (selected == defaultMenuItem.getAccessCharacter()) {
			return defaultMenuItem;
		}

		return null;
	}

	/**
	 * �L���ȃA�N�Z�X������z��Ƃ��Ď擾����
	 * @return �L���ȃA�N�Z�X����
	 */
	private char[] getAccessCharacters() {
		List<Character> list = new ArrayList<Character>();
		for (int i = page * MAX_RECORDS; i < Math.min(menuItems.size(), (page + 1) * MAX_RECORDS); i++) {
			MenuItem menuItem = menuItems.get(i);
			list.add(menuItem.getAccessCharacter());
		}
		if (hasNextPage()) {
			list.add(nextPage.getAccessCharacter());
		}
		if (hasPreviousPage()) {
			list.add(previousPage.getAccessCharacter());
		}
		list.add(defaultMenuItem.getAccessCharacter());

		char[] accessCharacters = new char[list.size()];
		for (int i = 0; i < list.size(); i++) {
			accessCharacters[i] = list.get(i);
		}

		return accessCharacters;
	}
	/**
	 * ���j���[�r���[���O�̃y�[�W�������ǂ������擾����
	 * @return ���j���[�r���[���O�̃y�[�W�������ǂ���
	 */
	private boolean hasPreviousPage() { return menuItems.size() > MAX_RECORDS && page >= 1; }
	/**
	 * ���j���[�r���[�����̃y�[�W�������ǂ������擾����
	 * @return ���j���[�r���[�����̃y�[�W�������ǂ���
	 */
	private boolean hasNextPage() { return menuItems.size() > MAX_RECORDS && page < getPageTotal() - 1; }
	/**
	 * ���j���[�r���[�̑��y�[�W�����擾����
	 * @return ���j���[�r���[�̑��y�[�W��
	 */
	private double getPageTotal() { return Math.ceil((float)menuItems.size() / MAX_RECORDS); }

	@Override
	protected View view(MenuItem input) {
		print("[{0}]��I�����܂����B", input.getName());
		return input.getView();
	}
}

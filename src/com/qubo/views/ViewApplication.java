package com.qubo.views;

/**
 * {@link View}�N���X�𗘗p�����R���\�[���A�v���P�[�V���������s���邽�߂̃N���X
 * @author Qubo
 */
public abstract class ViewApplication {
	/**
	 * �w�肵��{@link View}�C���X�^���X���N���r���[�Ƃ��āA
	 * �R���\�[���A�v���P�[�V���������s����B<br />
	 * {@link View}�C���X�^���X��{@link View#view()}���Ăяo�����Ƃ�
	 * ���̃r���[���擾���A�[���I�ɉ�ʑJ�ڂ��s���B<br />
	 * {@link View#view()}��{@code null}��Ԃ������_�ŏI������B
	 * @param entryView �N���r���[
	 */
	public static void start(View entryView) {
		View view = entryView;
		while (view != null) {
			view = view.view();
			if (view != null)
				System.out.println();
		}
	}
}

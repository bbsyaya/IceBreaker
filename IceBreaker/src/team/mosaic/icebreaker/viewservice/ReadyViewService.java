package team.mosaic.icebreaker.viewservice;

import java.util.ArrayList;

import team.mosaic.icebreaker.view.main.MainFrame;

public interface ReadyViewService {

//	public void showRole();
	
	/**
	 * ��ʾ���ߺ����б�
	 * @param friends
	 */
	public void showFriends(ArrayList<String> friends);

	public void findCoopPair(String id);
	
	public void findCoopFour(String[] id);
	
	public void findPKFour(String[] id);

	/**
	 * ��ʼ˫��Э��
	 */
	public void startTwosome();
	/**
	 * ��ʼ����Э��
	 */
	public void startFoursome();
	/**
	 * ��ʼpk
	 */	
	public void startPk(final String id);
	/**
	 * ȡ������ѡ��״̬
	 */
	public void cancelSelect(int i);
	public MainFrame getFrame();


	
}

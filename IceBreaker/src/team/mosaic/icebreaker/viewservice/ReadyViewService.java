package team.mosaic.icebreaker.viewservice;

import java.util.ArrayList;

import team.mosaic.icebreaker.view.main.MainFrame;

public interface ReadyViewService {

//	public void showRole();
	
	/**
	 * 显示在线好友列表
	 * @param friends
	 */
	public void showFriends(ArrayList<String> friends);

	public void findCoopPair(String id);
	
	public void findCoopFour(String[] id);
	
	public void findPKFour(String[] id);

	/**
	 * 开始双人协作
	 */
	public void startTwosome();
	/**
	 * 开始四人协作
	 */
	public void startFoursome();
	/**
	 * 开始pk
	 */	
	public void startPk(final String id);
	/**
	 * 取消道具选中状态
	 */
	public void cancelSelect(int i);
	public MainFrame getFrame();


	
}

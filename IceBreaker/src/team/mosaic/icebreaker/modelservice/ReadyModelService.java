package team.mosaic.icebreaker.modelservice;

import java.util.ArrayList;
public interface ReadyModelService {
	/**
	 * 向服务器请求配对
	 * @param
	 */
	public void askCouple(String id);
	/**
	 * 向服务器请求随机配对
	 * @param
	 */
	public void askCoupleRandom();
	/**
	 * 随机四人协作匹配
	 */
	public void randomCoopFour();
	/**
	 * 向服务器请求配对
	 * @param
	 */
	public void PKWith(String id);
	/**
	 * 向服务器请求随机配对
	 * @param
	 */
	public void PKRandom();
	/**
	 * 随机四人PK匹配
	 */
	public void randomPkFour();
	/**
	 * 向服务器获取好友信息
	 * @param resultVO
	 */
	public void getFriends();
	/**
	 * 显示好友
	 * @param resultVO
	 */
	public void showFriends(ArrayList<String> friends);
	 /**
	  * 根据是否拥有该道具判断能否选择
	  * @param i
	  * @param b
	  */
	public void SelectTool(int i);
	
	/**
	 * 减少选择使用道具的数量
	 * @param tool
	 */
	public void setTool(boolean[] tool );
	
	/**************for net below*********************/
	
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
	
}

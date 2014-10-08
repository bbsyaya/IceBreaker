package team.mosaic.icebreaker.viewservice;

import java.util.ArrayList;

/**
 * 主界面接口
 */

public interface FirstViewService {

	/**
	 * 刷新好友排行榜
	 * 
	 * @param friends
	 */
	public void refreshFriendsRank(ArrayList<String> friends);

	/**
	 * 刷新全球排行榜
	 * 
	 * @param allplayers
	 */
	public void refreshAllRank(ArrayList<String> allplayers);
	/**
	 * 好友申请提示框
	 * 
	 * @param allplayers
	 */
	public void friendApply(String id);

}

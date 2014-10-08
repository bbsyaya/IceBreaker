package team.mosaic.icebreaker.modelservice;

import java.util.ArrayList;

import team.mosaic.icebreaker.vo.ScoreVO;

public interface MenuModelService {
	/**
	 * 好友排行
	 * @param
	 */
	 public void friendRank();
	 /**
	  * 世界排行
	  * @param
	  */
	 public void worldRank();
	 /**
	  * 展示朋友排行榜
	  * @param
	  */
	 public void showFriendRank(ArrayList<ScoreVO> rank);
	 /**
	  * 显示世界排行
	  * @param
	  */
	 public void showWorldRank(ArrayList<ScoreVO> rank);
	 /**
	  * 添加好友
	  * @param String 好友id
	  */
	 public void addFriend(String id);
	 /**
	  * 好友申请
	  * @param String 申请者id
	  */
	 public void friendApply(String id);
	 /**
	  * 申请好友的结果
	  * @param
	  */
	 public void resultOfFriendApply(String id,boolean b);
	 
	 public void notFoundPlayer();
	
}

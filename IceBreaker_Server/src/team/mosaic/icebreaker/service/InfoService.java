package team.mosaic.icebreaker.service;

import java.util.ArrayList;

import team.mosaic.icebreaker.vo.InfoVO;
import team.mosaic.icebreaker.vo.RecordVO;
import team.mosaic.icebreaker.vo.ScoreVO;
import team.mosaic.icebreaker.vo.ToolVO;

public interface InfoService {
	/**
	 * 得到玩家个人信息
	 * @param id 玩家id
	 * @return 玩家个人信息
	 */
	public InfoVO getInfo(String id);
	/**
	 * 得到玩家的好友列表
	 * @param id 玩家id
	 * @return 好友列表
	 */
	public ArrayList<String> getMyFriends(String id);
	/**
	 * 得到玩家的好友排行榜
	 * @param id 玩家id
	 * @return 排行榜（id和对应成绩）
	 */
	public ArrayList<ScoreVO> getFriendRank(String id);
	/**
	 * 得到世界排行榜
	 * @return 排行榜（id和对应成绩）
	 */
	public ArrayList<ScoreVO> getWorldRank();
	/**
	 * 得到我的道具
	 * @param 玩家id
	 * @return 我的道具
	 */
	public ArrayList<ToolVO> getMyTools(String id);
	/**
	 * 添加好友
	 * @param id 好友id
	 */
	public void addFriend(String id1,String id2);
	/**
	 * 查询游戏记录
	 * @param id 好友id
	 */
	public ArrayList<RecordVO> queryRecord(String id);
	/**
	 * 提交游戏记录
	 * @param id 好友id
	 */
	public void updateRecord(String id,RecordVO rvo);
	
}

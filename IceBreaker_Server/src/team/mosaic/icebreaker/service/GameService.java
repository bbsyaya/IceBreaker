package team.mosaic.icebreaker.service;

import team.mosaic.icebreaker.vo.GainVO;
import team.mosaic.icebreaker.vo.ResultVO;

public interface GameService {
	/**
	 * 提交单人游戏分数
	 * @param id 玩家id
	 * @param gvo 游戏获得（包括得分，经验，金币）
	 * @param mode 游戏模式
	 * @return 是否刷新记录
	 */
	public ResultVO submitScore(String id,GainVO gvo,int mode);
	/**
	 * 购买道具
	 * @param id 玩家id
	 * @param tool_id 道具id
	 * @return 是否购买成功
	 */
	public boolean buyTool(String id,String tool_id);
	/**
	 * 使用道具
	 * @param id 玩家id
	 * @param tool_id 道具id
	 */
	public void useTool(String id,String tool_id);
	/**
	 * 购买人物
	 * @param id 玩家id
	 * @param char_id 人物id
	 * @return 是否购买成功
	 */
	public boolean buyCharacter(String id,String char_id);
	/**
	 * 得到人物解锁情况
	 * @param id 玩家id
	 * @return 人物解锁
	 */
	public boolean[] getCharacter(String id);
	
}

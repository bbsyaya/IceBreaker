package team.mosaic.icebreaker.modelservice;

import team.mosaic.icebreaker.viewservice.GameViewService;
import team.mosaic.icebreaker.viewservice.ReadyViewService;
import team.mosaic.icebreaker.vo.GainVO;
import team.mosaic.icebreaker.vo.ReplyVO;
import team.mosaic.icebreaker.vo.ResultVO;

public interface GameModelService {
	/**
	 * 开始协作游戏
	 */
	public void startCooperation();
	/**
	 * 开始pk游戏
	 * @param id 对方id
	 */
	public void startPK(String id);
	/**
	 * 开始单人游戏
	 * @param
	 */
	public void startSingle();
	/**
	 * 被邀请协作游戏 id 对方id
	 * @param
	 */
	public void invitedCoop(String id);
	/**
	 * 被邀请PK
	 * @param id 对方id
	 */
	public void invitedPK(String id);
	/**
	 * 邀请结果
	 * @param boolean
	 */
	public void inviteResult(ReplyVO rvo);
	/**
	 * 中途退出游戏
	 */
	public void quitGame();
	/**
	 * 设置为主机
	 */
	public void setHost();
	
	public void setReadyView(ReadyViewService rvs);
	
	public void setGameView(GameViewService gvs);
	
	public void showResult(ResultVO rvo);
	
	public void setGainVO(GainVO gvo);
	
	public void someoneQuit(String id);
	
}

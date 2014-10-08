package team.mosaic.icebreaker.modelservice;

import team.mosaic.icebreaker.viewservice.GameViewService;
import team.mosaic.icebreaker.viewservice.ReadyViewService;
import team.mosaic.icebreaker.vo.GainVO;
import team.mosaic.icebreaker.vo.ReplyVO;
import team.mosaic.icebreaker.vo.ResultVO;

public interface GameModelService {
	/**
	 * ��ʼЭ����Ϸ
	 */
	public void startCooperation();
	/**
	 * ��ʼpk��Ϸ
	 * @param id �Է�id
	 */
	public void startPK(String id);
	/**
	 * ��ʼ������Ϸ
	 * @param
	 */
	public void startSingle();
	/**
	 * ������Э����Ϸ id �Է�id
	 * @param
	 */
	public void invitedCoop(String id);
	/**
	 * ������PK
	 * @param id �Է�id
	 */
	public void invitedPK(String id);
	/**
	 * ������
	 * @param boolean
	 */
	public void inviteResult(ReplyVO rvo);
	/**
	 * ��;�˳���Ϸ
	 */
	public void quitGame();
	/**
	 * ����Ϊ����
	 */
	public void setHost();
	
	public void setReadyView(ReadyViewService rvs);
	
	public void setGameView(GameViewService gvs);
	
	public void showResult(ResultVO rvo);
	
	public void setGainVO(GainVO gvo);
	
	public void someoneQuit(String id);
	
}

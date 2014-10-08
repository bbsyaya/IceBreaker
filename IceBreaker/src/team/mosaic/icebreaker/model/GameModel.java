package team.mosaic.icebreaker.model;


import team.mosaic.icebreaker.modelservice.GameModelService;
import team.mosaic.icebreaker.modelservice.InfoModelService;
import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.viewservice.GameViewService;
import team.mosaic.icebreaker.viewservice.ReadyViewService;
import team.mosaic.icebreaker.vo.GainVO;
import team.mosaic.icebreaker.vo.RecordVO;
import team.mosaic.icebreaker.vo.ReplyVO;
import team.mosaic.icebreaker.vo.ResultVO;

/**
 * 游戏进行中的模型类
 * @author acer
 *
 */
public class GameModel implements GameModelService {
	private GameViewService gvs;
	private ReadyViewService rvs;
	private String player;
	private boolean isHost;
	private GainVO gvo;
	
	public GameModel(){};
	
	public GameModel(GameViewService gvs){
		this.gvs = gvs;
		ClientModel.setGameModel(this);
	}
	
	
	public void setReadyView(ReadyViewService rvs){
		this.rvs = rvs;
	}
	
	public void setGameView(GameViewService gvs){
		this.gvs = gvs;
	}
	
	/**
	 * 更改视图，开始协作游戏
	 */
	@Override
	public void startCooperation() {
		// TODO Auto-generated method stub
		rvs.startTwosome();
	}

	/**
	 * 更改视图，开始对战游戏
	 */
	@Override
	public void startPK(String id) {
		// TODO Auto-generated method stub
		rvs.startPk(id);
	}

	@Override
	public void startSingle() {
		// TODO Auto-generated method stub
//		gvs.ToGame(null);
	}
	
	/**
	 * 邀请玩家进行协作游戏
	 */
	@Override
	public void invitedCoop(String id) {
		// TODO Auto-generated method stub
		Prompt.showCoopInvited(rvs.getFrame(), id);
	}
	
	/**
	 * 邀请玩家进行对战游戏
	 */
	@Override
	public void invitedPK(String id) {
		Prompt.showPkInvited(rvs.getFrame(), id);
	}
	
	/**
	 * 更改视图，显示邀请结果
	 */
	@Override
	public void inviteResult(ReplyVO rvo) {
		// TODO Auto-generated method stub
		boolean result = rvo.getAgreement();
		String id = rvo.getPalID();
		if(!result)
			Prompt.showWarning(rvs.getFrame(), id+" 拒绝了你的邀请");
		else{
			Prompt.showWarning(rvs.getFrame(), id+" 接收了你的邀请");
//			rvs.CoupleResult(id);
		}
	}
	@Override
	public void setHost() {
		// TODO Auto-generated method stub
		this.isHost = true;
	}
	
	/**
	 * 更改视图，显示游戏结果
	 */
	@Override
	public void showResult(ResultVO rvo) {
		// TODO Auto-generated method stub
		boolean isPK = rvo.getScore() != -2?true:false;
		System.out.println(rvo.getRecordBroken()+ " "+rvo.getLevelUp());
		Prompt.showGainDialog(rvs.getFrame(), isPK, gvo.getScore(), gvo.getCoin(), gvo.getExp(), rvo.getRecordBroken(), rvo.getLevelUp(), gvo.getScore()-rvo.getScore());
//		RecordVO r = new RecordVO(gvo.getScore(), 0);
//		InfoModelService ims = new InfoModel();
//		ims.saveRecord(r);
	}

	@Override
	public void setGainVO(GainVO gvo) {
		// TODO Auto-generated method stub
		this.gvo = gvo;
	}

	/**
	 * 退出游戏
	 */
	@Override
	public void quitGame() {
		// TODO Auto-generated method stub
		NetService.quitGame();
	}

	/**
	 * 一方退出游戏
	 */
	@Override
	public void someoneQuit(String id) {
		// TODO Auto-generated method stub
		gvs.someoneQuit(id);
	}

}

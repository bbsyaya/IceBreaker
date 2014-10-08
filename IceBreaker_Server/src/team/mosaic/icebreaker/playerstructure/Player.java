package team.mosaic.icebreaker.playerstructure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import team.mosaic.icebreaker.net.MyServerSocket;
import team.mosaic.icebreaker.service.AccountService;
import team.mosaic.icebreaker.service.GameService;
import team.mosaic.icebreaker.service.InfoService;
import team.mosaic.icebreaker.serviceimp.AccountServiceImp;
import team.mosaic.icebreaker.serviceimp.GameServiceImp;
import team.mosaic.icebreaker.serviceimp.InfoServiceImp;
import team.mosaic.icebreaker.vo.AccountVO;
import team.mosaic.icebreaker.vo.GainVO;
import team.mosaic.icebreaker.vo.MessageVO;
import team.mosaic.icebreaker.vo.RecordVO;
import team.mosaic.icebreaker.vo.ReplyVO;
import team.mosaic.icebreaker.vo.ResultVO;
import team.mosaic.icebreaker.vo.SwapActionVO;
/**
 * 玩家类，负责与客户端玩家的交互
 * @author HJW
 *
 */
public class Player extends Thread {
	private String id;//玩家id
	private int head;//头像
	private boolean isFree;//空闲状态
	private int score;
	private boolean isHost;
	private ResultVO rvo;
	
	private MyServerSocket mss;
	private Socket s;
	private ObjectInputStream ois;//输入
	private ObjectOutputStream oos;//输出
	private MessageVO mvo;//消息包
	private AccountService accountService;//帐户业务逻辑对象
	private GameService gameService;//游戏业务逻辑对象
	private InfoService infoService;//个人信息业务逻辑对象
	//玩家交互
	private CooperationPair coop;//协作双方对象
	private CompetitionPair comp;//pk双方对象
	private CooperationFoursome foursome;//四人协作对象
	private CompetitionFoursome compfour;
	private CooperationPair subCoop;

	public Player(MyServerSocket mss, Socket s) {
		isFree = true;
		this.mss = mss;
		this.s = s;
		accountService = new AccountServiceImp();
		gameService = new GameServiceImp();
		infoService = new InfoServiceImp();
	}

	public void run() {
		try {
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
			while (true) {
				mvo = (MessageVO) ois.readObject();
				dealWithMessage(mvo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			quitAllQueue();
			shut();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 得到玩家id
	 * @return 玩家id
	 */
	public String getID() {
		return id;
	}
	/**
	 * 得到玩家空闲状态
	 * @return 状态
	 */
	public boolean getFree(){
		return isFree;
	}
	/**
	 * 设置玩家空闲状态
	 * @param isFree 状态
	 */
	public void setFree(boolean isFree){
		this.isFree = isFree;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void setHost(boolean b){
		this.isHost = b;
	}
	
	public boolean getHost(){
		return this.isHost;
	}
	
	public ResultVO getResult(){
		return this.rvo;
	}
	
	public void setCooperationPair(CooperationPair cp){
		this.coop = cp;
	}
	
	public void setCompetitionPair(CompetitionPair cp){
		this.comp = cp;
	}
	
	public void setCooperationFoursome(CooperationFoursome cf){
		this.foursome = cf;
	}
	
	public void setCompetitionFoursome(CompetitionFoursome cf){
		this.compfour = cf;
	}
	
	public void setSubCooperationPair(CooperationPair scp){
		this.subCoop = scp;
	}
	
	/**
	 * 结束该玩家线程
	 */
	@SuppressWarnings("deprecation")
	private void shut(){
		if (id != null)
			mss.removeMember(this);
		System.out.println(id+" logoff");
		this.stop();
	}
	/**
	 * 发送消息给客户端
	 * @param mvo 消息包
	 * @throws IOException
	 */
	public void sendMessage(MessageVO mvo) throws IOException {
		oos.writeObject(mvo);
		oos.flush();
	}
	/**
	 * 处理接收到的客户端消息
	 * @param mvo 消息包
	 * @throws IOException
	 */
	private void dealWithMessage(MessageVO mvo) throws IOException {
		String cmd = mvo.getCommand();
		Object obj = mvo.getObject();
		String recmd = "re-" + cmd;
		switch (cmd) {
		case "register"://注册
			AccountVO avo2 = (AccountVO) obj;
			boolean isRegistered = accountService.register(avo2);
			sendMessage(new MessageVO(recmd, isRegistered));
			break;
		case "signin"://登陆
			AccountVO avo = (AccountVO) obj;
			boolean isVerified = accountService.verify(avo);
			if (isVerified) {// 登陆成功
				id = avo.getID();
				mss.addMember(this);
				System.out.println(id+" login");
			}
			sendMessage(new MessageVO(recmd, isVerified));
			break;
		case "signout"://注销
			mss.removeMember(this);
			id = null;
			break;
		case "info"://个人信息
			sendMessage(new MessageVO(recmd, infoService.getInfo(id)));
			break;
		case "applyfriend"://好友申请(p1)
			String applyID = (String)obj;
			Player applyPal = mss.getPlayer(applyID);
			if(applyPal != null)
				applyPal.sendMessage(new MessageVO(recmd, id));
			else 
				sendMessage(new MessageVO("notfound", null));
			break;
		case "replyfriend"://回复好友申请（p2）
			ReplyVO rvo1 = (ReplyVO)obj;
			String repliedID1 = rvo1.getPalID();
			boolean agree1 = rvo1.getAgreement();
			Player repliedPlayer1 = mss.getPlayer(repliedID1);
			repliedPlayer1.sendMessage(new MessageVO(recmd, new ReplyVO(agree1, id)));//告诉对方自己是否同意
			if(agree1)
				infoService.addFriend(repliedID1, id);
			break;
		case "friendrank"://好友排行榜
			sendMessage(new MessageVO(recmd, infoService.getFriendRank(id)));
			break;
		case "worldrank"://世界排行榜
			sendMessage(new MessageVO(recmd, infoService.getWorldRank()));
			break;
		case "mytool"://我的道具
			sendMessage(new MessageVO(recmd, infoService.getMyTools(id)));
			break;
		case "usetool"://使用道具
			gameService.useTool(id, (String)obj);
			break;
		case "buytool"://购买道具
			sendMessage(new MessageVO(recmd, gameService.buyTool(id, (String)obj)));
			break;
		case "buycharacter"://购买人物
			sendMessage(new MessageVO(recmd, gameService.buyCharacter(id, (String)obj)));
			break;
		case "getcharacter"://得到人物
			sendMessage(new MessageVO(recmd, gameService.getCharacter(id)));
			break;
		case "sendqueue"://协作开始发送循环队列
			if(coop != null)
				coop.init(obj);
			else if(foursome != null)
				foursome.init(obj);
			else if(compfour != null)
				compfour.getPair(this).init(obj);
			break;
		case "swap"://协作中交换操作
			if(coop != null)
				coop.synchronize((SwapActionVO)obj);
			else if(foursome != null)
				foursome.synchronize((SwapActionVO)obj);
			else if(compfour != null)
				compfour.getPair(this).synchronize((SwapActionVO)obj);
			break;
		case "onlinepal"://在线好友
			ArrayList<String> palStrings = infoService.getMyFriends(id);
			ArrayList<String> ret = new ArrayList<>();
			if(palStrings != null)
				for(String s:palStrings){
					Player p = mss.getPlayer(s);
					if(p != null && p.getFree())
						ret.add(s);
				}
			sendMessage(new MessageVO(recmd, ret));
			break;
		case "coopwithpal"://请求与好友协作游戏(p1)
			String coopID = (String)obj;
			Player coopPal = mss.getPlayer(coopID);
			if(coopPal.getFree())
				coopPal.sendMessage(new MessageVO(recmd, id));
			break;
		case "cooprandom"://请求随即匹配协作游戏
			quitAllQueue();
			mss.getCoopQueue().queueUp(this);//排队
			break;
		case "quitrandom"://取消排队
			quitAllQueue();
			break;
		case "replycoop"://答复协作游戏(p2)
			ReplyVO rvo = (ReplyVO)obj;
			String repliedID = rvo.getPalID();
			Player repliedPlayer = mss.getPlayer(repliedID);
			boolean agree = rvo.getAgreement();
			if(agree){//如果同意，建立连接
				coop = new CooperationPair(repliedPlayer, this);
			}
			else {//告诉对方不同意
				repliedPlayer.sendMessage(new MessageVO(recmd, new ReplyVO(agree,id)));
			}
			break;
		case "pkwithpal"://请求与好友pk(p1)
			String pkID = (String)obj;
			Player pkPal = mss.getPlayer(pkID);
			if(pkPal.getFree())
				pkPal.sendMessage(new MessageVO(recmd, id));
			break;
		case "pkrandom"://请求随即匹配pk游戏
			quitAllQueue();
			mss.getPkQueue().queueUp(this);
			break;
		case "replypk"://答复pk(p2)
			ReplyVO rvo2 = (ReplyVO)obj;
			String repliedID2 = rvo2.getPalID();
			Player repliedPlayer2 = mss.getPlayer(repliedID2);
			boolean agree2 = rvo2.getAgreement();
			if(agree2){//如果同意，建立连接
				comp = new CompetitionPair(repliedPlayer2, this);
			}
			else {//告诉对方不同意
				repliedPlayer2.sendMessage(new MessageVO(recmd, new ReplyVO(agree2,id)));
			}
			break;
		case "foursomecoop"://四人协作
			quitAllQueue();
			mss.getFoursomeQueue().queueUp(this);//排队
			break;
		case "foursomecomp"://四人对战
			quitAllQueue();
			mss.getCompfourQueue().queueUp(this);
			break;
		case "startsingle":
			isFree = false;
			break;
		case "endsingle"://结算个人游戏
			isFree = true;
			GainVO gvo = (GainVO)obj;
			ResultVO resultVO = gameService.submitScore(id, gvo,1);
			sendMessage(new MessageVO(recmd, resultVO));
			break;
		case "endcoop"://结算协作
			GainVO gvo2 = (GainVO)obj;
			ResultVO resultVO2 = gameService.submitScore(id, gvo2,2);
			sendMessage(new MessageVO(recmd, resultVO2));
			if(isHost)
				coop.dismiss();
			break;
		case "endpk"://结算PK
			GainVO gvo3 = (GainVO)obj;
			this.rvo = gameService.submitScore(id, gvo3,3);
			this.score = gvo3.getScore();
			comp.end();
			break;
		case "endcoopfour"://结算四人协作
			ResultVO resultVO3 = gameService.submitScore(id, (GainVO)obj, 4);
			sendMessage(new MessageVO(recmd, resultVO3));
			if(isHost)
				foursome.dismiss();
			break;
		case "endpkfour"://结算四人PK
			GainVO gvo4 = (GainVO)obj;
			this.rvo = gameService.submitScore(id, gvo4, 5);
			this.score = gvo4.getScore();
			if(isHost)
				compfour.end();
			break;
		case "deleteb":
			if(comp != null)
				comp.deleteB(this);
			else if(compfour != null && isHost)
				compfour.deleteB(this);
			break;
		case "supertime":
			if(comp != null)
				comp.superTime(this);
			else if(compfour != null && isHost)
				compfour.superTime(this);
			break;
		case "quit":
			isFree = true;
			if(comp!=null)
				comp.quit(this);
			else if(compfour != null)
				compfour.quit(this);
			else if(coop != null)
				coop.quit(this);
			else if(foursome != null)
				foursome.quit(this);
			break;
		case "queryrecord":
			ArrayList<RecordVO> records = infoService.queryRecord(id);
			sendMessage(new MessageVO(recmd, records));
			break;
		case "updaterecord":
			RecordVO rdvo = (RecordVO) obj;
			infoService.updateRecord(id, rdvo);
			break;
		default://没找到相应命令
			System.out.println("Undefined instruction.");
			break;
		}
	}
	
	private void quitAllQueue(){
		if(mss.getCoopQueue().has(this))
			mss.getCoopQueue().quitQueue(this);
		if(mss.getPkQueue().has(this))
			mss.getPkQueue().quitQueue(this);
		if(mss.getFoursomeQueue().has(this))
			mss.getFoursomeQueue().quitQueue(this);
	}
	
}

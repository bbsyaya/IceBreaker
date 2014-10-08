package team.mosaic.icebreaker.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import team.mosaic.icebreaker.modelservice.ClientService;
import team.mosaic.icebreaker.vo.ColorQueueVO;
import team.mosaic.icebreaker.vo.InfoVO;
import team.mosaic.icebreaker.vo.MessageVO;
import team.mosaic.icebreaker.vo.RecordVO;
import team.mosaic.icebreaker.vo.ReplyVO;
import team.mosaic.icebreaker.vo.ResultVO;
import team.mosaic.icebreaker.vo.ScoreVO;
import team.mosaic.icebreaker.vo.SwapActionVO;
import team.mosaic.icebreaker.vo.ToolVO;
/**
 * 负责接收服务器消息
 * @author HJW
 *
 */
public class ClientThread extends Thread {
	private Socket s;
	private ClientSocket cs;
	private ObjectInputStream ois;
	private MessageVO mvo;
	private ClientService service;

	public ClientThread(Socket s, ClientSocket cs) {
		this.s = s;
		this.cs = cs;
		try {
			ois = new ObjectInputStream(this.s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				mvo = (MessageVO) ois.readObject();
				dealWithMessage(mvo);
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "连接异常！");
				System.exit(0);
				e.printStackTrace();
			}
		}
	}
	
	void setClientModel(ClientService service){
		this.service = service;
	}
	
	/**
	 * 处理收到的消息
	 * @param mvo 消息包
	 */
	private void dealWithMessage(MessageVO mvo) {
		String cmd = mvo.getCommand();
		Object obj = mvo.getObject();
		switch (cmd) {
		case "re-register"://注册验证结果
			boolean isRegistered = (Boolean) obj;
			service.register(isRegistered);
			break;
		case "re-signin"://登陆验证结果
			boolean isVerified = (Boolean) obj;
			service.login(isVerified);
			break;
		case "re-endsingle"://结算单人游戏结果
			service.showResult((ResultVO)obj);
			break;
		case "re-endcoop"://结算协作
			service.showResult((ResultVO)obj);
			break;
		case "re-endcoopfour":
			service.showResult((ResultVO)obj);
			break;
		case "pkresult"://结算PK
			service.showResult((ResultVO)obj);
			break;
		case "re-info"://返回个人信息
			InfoVO ivo = (InfoVO)obj;
			service.myInfo(ivo);
			break;
		case "re-applyfriend"://收到好友申请（p2）
			service.friendsApply((String) obj);
			break;
		case "re-replyfriend"://收到好友申请回复（p1）
			ReplyVO rvo1 = (ReplyVO)obj;
			service.resultOffriendsApply(rvo1);
			break;
		case "re-friendrank"://返回好友排行榜
			@SuppressWarnings("unchecked")
			ArrayList<ScoreVO> friendRank = (ArrayList<ScoreVO>)obj;
			service.firendRank(friendRank);
			break;
		case "re-worldrank"://返回世界排行榜
			@SuppressWarnings("unchecked")
			ArrayList<ScoreVO> worldRank = (ArrayList<ScoreVO>)obj;
			service.worldRank(worldRank);
			break;
		case "re-mytool"://我的道具
			@SuppressWarnings("unchecked")
			ArrayList<ToolVO> mytools = (ArrayList<ToolVO>)obj;
			service.myTools(mytools);
			break;
		case "re-buytool"://回复购买道具	
			service.buyToolResult((Boolean)obj);
			break;
		case "re-buycharacter"://回复购买人物
			service.buyCharacterResult((Boolean)obj);
			break;
		case "re-getcharacter"://人物解锁情况
			service.myCharacters((boolean[])obj);
			break;
		case "re-onlinepal"://返回在线好友
			@SuppressWarnings("unchecked")
			ArrayList<String> onlinePal = (ArrayList<String>)obj;
			service.onlineFriends(onlinePal);
			break;
		case "re-coopwithpal"://接到协作邀请(p2)
			String inviteID = (String)obj;
			service.coopInvited(inviteID);
			break;
		case "re-pkwithpal"://接到pk邀请(p2)
			service.pkInvited((String)obj);
			break;
		case "re-replycoop"://接到对方是否同意协作的结果(p1)
			ReplyVO rvo = (ReplyVO)obj;
			service.invitationResult(rvo);
			break;
		case "findpair"://匹配到玩家
			service.findCoopPair((String)obj);
			break;
		case "findgroup"://匹配到四人
			service.findCoopFour((String[])obj);
			break;
		case "findpaircomp"://匹配到四人pk
			service.findPKFour((String[])obj);
			break;
		case "sethost"://设置主机
			service.setHost();
			break;
		case "sendqueue"://发送初始化的盘面
			service.sendQueue((ColorQueueVO)obj);
			break;
		case "startcoop"://开始协作游戏
			service.startTwosome();
			break;
		case "startcoopfour":
			service.startFoursome();
			break;
		case "startpk"://开始pk对战
			service.startPk((String)obj);
			break;
		case "act":
			SwapActionVO svo = (SwapActionVO)obj;
			service.act(svo);
			break;
		case "randomlock":
			service.randomLock();
			break;
		case "lockc":
			service.lockC();
			break;
		case "quit"://其他玩家中途退出游戏
			service.someoneQuit((String)obj);
			break;
		case "notfound":
			service.notFoundPlayer();
			break;
		case "re-queryrecord":
			service.sendRecords((ArrayList<RecordVO>)obj);
			break;
		default:
			System.out.println("Undefined instruction.");
			break;
		}
	}

}

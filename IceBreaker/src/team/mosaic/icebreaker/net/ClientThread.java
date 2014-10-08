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
 * ������շ�������Ϣ
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
				JOptionPane.showMessageDialog(null, "�����쳣��");
				System.exit(0);
				e.printStackTrace();
			}
		}
	}
	
	void setClientModel(ClientService service){
		this.service = service;
	}
	
	/**
	 * �����յ�����Ϣ
	 * @param mvo ��Ϣ��
	 */
	private void dealWithMessage(MessageVO mvo) {
		String cmd = mvo.getCommand();
		Object obj = mvo.getObject();
		switch (cmd) {
		case "re-register"://ע����֤���
			boolean isRegistered = (Boolean) obj;
			service.register(isRegistered);
			break;
		case "re-signin"://��½��֤���
			boolean isVerified = (Boolean) obj;
			service.login(isVerified);
			break;
		case "re-endsingle"://���㵥����Ϸ���
			service.showResult((ResultVO)obj);
			break;
		case "re-endcoop"://����Э��
			service.showResult((ResultVO)obj);
			break;
		case "re-endcoopfour":
			service.showResult((ResultVO)obj);
			break;
		case "pkresult"://����PK
			service.showResult((ResultVO)obj);
			break;
		case "re-info"://���ظ�����Ϣ
			InfoVO ivo = (InfoVO)obj;
			service.myInfo(ivo);
			break;
		case "re-applyfriend"://�յ��������루p2��
			service.friendsApply((String) obj);
			break;
		case "re-replyfriend"://�յ���������ظ���p1��
			ReplyVO rvo1 = (ReplyVO)obj;
			service.resultOffriendsApply(rvo1);
			break;
		case "re-friendrank"://���غ������а�
			@SuppressWarnings("unchecked")
			ArrayList<ScoreVO> friendRank = (ArrayList<ScoreVO>)obj;
			service.firendRank(friendRank);
			break;
		case "re-worldrank"://�����������а�
			@SuppressWarnings("unchecked")
			ArrayList<ScoreVO> worldRank = (ArrayList<ScoreVO>)obj;
			service.worldRank(worldRank);
			break;
		case "re-mytool"://�ҵĵ���
			@SuppressWarnings("unchecked")
			ArrayList<ToolVO> mytools = (ArrayList<ToolVO>)obj;
			service.myTools(mytools);
			break;
		case "re-buytool"://�ظ��������	
			service.buyToolResult((Boolean)obj);
			break;
		case "re-buycharacter"://�ظ���������
			service.buyCharacterResult((Boolean)obj);
			break;
		case "re-getcharacter"://����������
			service.myCharacters((boolean[])obj);
			break;
		case "re-onlinepal"://�������ߺ���
			@SuppressWarnings("unchecked")
			ArrayList<String> onlinePal = (ArrayList<String>)obj;
			service.onlineFriends(onlinePal);
			break;
		case "re-coopwithpal"://�ӵ�Э������(p2)
			String inviteID = (String)obj;
			service.coopInvited(inviteID);
			break;
		case "re-pkwithpal"://�ӵ�pk����(p2)
			service.pkInvited((String)obj);
			break;
		case "re-replycoop"://�ӵ��Է��Ƿ�ͬ��Э���Ľ��(p1)
			ReplyVO rvo = (ReplyVO)obj;
			service.invitationResult(rvo);
			break;
		case "findpair"://ƥ�䵽���
			service.findCoopPair((String)obj);
			break;
		case "findgroup"://ƥ�䵽����
			service.findCoopFour((String[])obj);
			break;
		case "findpaircomp"://ƥ�䵽����pk
			service.findPKFour((String[])obj);
			break;
		case "sethost"://��������
			service.setHost();
			break;
		case "sendqueue"://���ͳ�ʼ��������
			service.sendQueue((ColorQueueVO)obj);
			break;
		case "startcoop"://��ʼЭ����Ϸ
			service.startTwosome();
			break;
		case "startcoopfour":
			service.startFoursome();
			break;
		case "startpk"://��ʼpk��ս
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
		case "quit"://���������;�˳���Ϸ
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

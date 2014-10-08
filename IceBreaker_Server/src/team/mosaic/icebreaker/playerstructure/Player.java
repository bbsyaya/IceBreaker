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
 * ����࣬������ͻ�����ҵĽ���
 * @author HJW
 *
 */
public class Player extends Thread {
	private String id;//���id
	private int head;//ͷ��
	private boolean isFree;//����״̬
	private int score;
	private boolean isHost;
	private ResultVO rvo;
	
	private MyServerSocket mss;
	private Socket s;
	private ObjectInputStream ois;//����
	private ObjectOutputStream oos;//���
	private MessageVO mvo;//��Ϣ��
	private AccountService accountService;//�ʻ�ҵ���߼�����
	private GameService gameService;//��Ϸҵ���߼�����
	private InfoService infoService;//������Ϣҵ���߼�����
	//��ҽ���
	private CooperationPair coop;//Э��˫������
	private CompetitionPair comp;//pk˫������
	private CooperationFoursome foursome;//����Э������
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
	 * �õ����id
	 * @return ���id
	 */
	public String getID() {
		return id;
	}
	/**
	 * �õ���ҿ���״̬
	 * @return ״̬
	 */
	public boolean getFree(){
		return isFree;
	}
	/**
	 * ������ҿ���״̬
	 * @param isFree ״̬
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
	 * ����������߳�
	 */
	@SuppressWarnings("deprecation")
	private void shut(){
		if (id != null)
			mss.removeMember(this);
		System.out.println(id+" logoff");
		this.stop();
	}
	/**
	 * ������Ϣ���ͻ���
	 * @param mvo ��Ϣ��
	 * @throws IOException
	 */
	public void sendMessage(MessageVO mvo) throws IOException {
		oos.writeObject(mvo);
		oos.flush();
	}
	/**
	 * ������յ��Ŀͻ�����Ϣ
	 * @param mvo ��Ϣ��
	 * @throws IOException
	 */
	private void dealWithMessage(MessageVO mvo) throws IOException {
		String cmd = mvo.getCommand();
		Object obj = mvo.getObject();
		String recmd = "re-" + cmd;
		switch (cmd) {
		case "register"://ע��
			AccountVO avo2 = (AccountVO) obj;
			boolean isRegistered = accountService.register(avo2);
			sendMessage(new MessageVO(recmd, isRegistered));
			break;
		case "signin"://��½
			AccountVO avo = (AccountVO) obj;
			boolean isVerified = accountService.verify(avo);
			if (isVerified) {// ��½�ɹ�
				id = avo.getID();
				mss.addMember(this);
				System.out.println(id+" login");
			}
			sendMessage(new MessageVO(recmd, isVerified));
			break;
		case "signout"://ע��
			mss.removeMember(this);
			id = null;
			break;
		case "info"://������Ϣ
			sendMessage(new MessageVO(recmd, infoService.getInfo(id)));
			break;
		case "applyfriend"://��������(p1)
			String applyID = (String)obj;
			Player applyPal = mss.getPlayer(applyID);
			if(applyPal != null)
				applyPal.sendMessage(new MessageVO(recmd, id));
			else 
				sendMessage(new MessageVO("notfound", null));
			break;
		case "replyfriend"://�ظ��������루p2��
			ReplyVO rvo1 = (ReplyVO)obj;
			String repliedID1 = rvo1.getPalID();
			boolean agree1 = rvo1.getAgreement();
			Player repliedPlayer1 = mss.getPlayer(repliedID1);
			repliedPlayer1.sendMessage(new MessageVO(recmd, new ReplyVO(agree1, id)));//���߶Է��Լ��Ƿ�ͬ��
			if(agree1)
				infoService.addFriend(repliedID1, id);
			break;
		case "friendrank"://�������а�
			sendMessage(new MessageVO(recmd, infoService.getFriendRank(id)));
			break;
		case "worldrank"://�������а�
			sendMessage(new MessageVO(recmd, infoService.getWorldRank()));
			break;
		case "mytool"://�ҵĵ���
			sendMessage(new MessageVO(recmd, infoService.getMyTools(id)));
			break;
		case "usetool"://ʹ�õ���
			gameService.useTool(id, (String)obj);
			break;
		case "buytool"://�������
			sendMessage(new MessageVO(recmd, gameService.buyTool(id, (String)obj)));
			break;
		case "buycharacter"://��������
			sendMessage(new MessageVO(recmd, gameService.buyCharacter(id, (String)obj)));
			break;
		case "getcharacter"://�õ�����
			sendMessage(new MessageVO(recmd, gameService.getCharacter(id)));
			break;
		case "sendqueue"://Э����ʼ����ѭ������
			if(coop != null)
				coop.init(obj);
			else if(foursome != null)
				foursome.init(obj);
			else if(compfour != null)
				compfour.getPair(this).init(obj);
			break;
		case "swap"://Э���н�������
			if(coop != null)
				coop.synchronize((SwapActionVO)obj);
			else if(foursome != null)
				foursome.synchronize((SwapActionVO)obj);
			else if(compfour != null)
				compfour.getPair(this).synchronize((SwapActionVO)obj);
			break;
		case "onlinepal"://���ߺ���
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
		case "coopwithpal"://���������Э����Ϸ(p1)
			String coopID = (String)obj;
			Player coopPal = mss.getPlayer(coopID);
			if(coopPal.getFree())
				coopPal.sendMessage(new MessageVO(recmd, id));
			break;
		case "cooprandom"://�����漴ƥ��Э����Ϸ
			quitAllQueue();
			mss.getCoopQueue().queueUp(this);//�Ŷ�
			break;
		case "quitrandom"://ȡ���Ŷ�
			quitAllQueue();
			break;
		case "replycoop"://��Э����Ϸ(p2)
			ReplyVO rvo = (ReplyVO)obj;
			String repliedID = rvo.getPalID();
			Player repliedPlayer = mss.getPlayer(repliedID);
			boolean agree = rvo.getAgreement();
			if(agree){//���ͬ�⣬��������
				coop = new CooperationPair(repliedPlayer, this);
			}
			else {//���߶Է���ͬ��
				repliedPlayer.sendMessage(new MessageVO(recmd, new ReplyVO(agree,id)));
			}
			break;
		case "pkwithpal"://���������pk(p1)
			String pkID = (String)obj;
			Player pkPal = mss.getPlayer(pkID);
			if(pkPal.getFree())
				pkPal.sendMessage(new MessageVO(recmd, id));
			break;
		case "pkrandom"://�����漴ƥ��pk��Ϸ
			quitAllQueue();
			mss.getPkQueue().queueUp(this);
			break;
		case "replypk"://��pk(p2)
			ReplyVO rvo2 = (ReplyVO)obj;
			String repliedID2 = rvo2.getPalID();
			Player repliedPlayer2 = mss.getPlayer(repliedID2);
			boolean agree2 = rvo2.getAgreement();
			if(agree2){//���ͬ�⣬��������
				comp = new CompetitionPair(repliedPlayer2, this);
			}
			else {//���߶Է���ͬ��
				repliedPlayer2.sendMessage(new MessageVO(recmd, new ReplyVO(agree2,id)));
			}
			break;
		case "foursomecoop"://����Э��
			quitAllQueue();
			mss.getFoursomeQueue().queueUp(this);//�Ŷ�
			break;
		case "foursomecomp"://���˶�ս
			quitAllQueue();
			mss.getCompfourQueue().queueUp(this);
			break;
		case "startsingle":
			isFree = false;
			break;
		case "endsingle"://���������Ϸ
			isFree = true;
			GainVO gvo = (GainVO)obj;
			ResultVO resultVO = gameService.submitScore(id, gvo,1);
			sendMessage(new MessageVO(recmd, resultVO));
			break;
		case "endcoop"://����Э��
			GainVO gvo2 = (GainVO)obj;
			ResultVO resultVO2 = gameService.submitScore(id, gvo2,2);
			sendMessage(new MessageVO(recmd, resultVO2));
			if(isHost)
				coop.dismiss();
			break;
		case "endpk"://����PK
			GainVO gvo3 = (GainVO)obj;
			this.rvo = gameService.submitScore(id, gvo3,3);
			this.score = gvo3.getScore();
			comp.end();
			break;
		case "endcoopfour"://��������Э��
			ResultVO resultVO3 = gameService.submitScore(id, (GainVO)obj, 4);
			sendMessage(new MessageVO(recmd, resultVO3));
			if(isHost)
				foursome.dismiss();
			break;
		case "endpkfour"://��������PK
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
		default://û�ҵ���Ӧ����
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

package team.mosaic.icebreaker.netservice;

import team.mosaic.icebreaker.net.ClientSocket;
import team.mosaic.icebreaker.vo.AccountVO;
import team.mosaic.icebreaker.vo.ColorQueueVO;
import team.mosaic.icebreaker.vo.GainVO;
import team.mosaic.icebreaker.vo.MessageVO;
import team.mosaic.icebreaker.vo.RecordVO;
import team.mosaic.icebreaker.vo.ReplyVO;
import team.mosaic.icebreaker.vo.SwapActionVO;
/**
 * �ṩ��ģ�͵�����ͨ�Žӿ�
 * @author HJW
 *
 */
public class NetService {

	private static ClientSocket cs = ClientSocket.getInstance();
	
	/**
	 * ע��
	 * @param avo �ʻ���Ϣ���ʺ����룩
	 */
	public static void register(AccountVO avo){
		cs.sendMessage(new MessageVO("register", avo));
	}
	/**
	 * ��½
	 * @param avo �ʻ���Ϣ���ʺ����룩
	 */
	public static void signIn(AccountVO avo){
		cs.sendMessage(new MessageVO("signin", avo));
	}
	/**
	 * ע��
	 */
	public static void signOut(){
		cs.sendMessage(new MessageVO("signout", null));
	}
	/**
	 * �õ���Ҹ�����Ϣ
	 */
	public static void getInfo(){
		cs.sendMessage(new MessageVO("info", null));
	}
	/**
	 * ������Ӻ���
	 * @param id �Է�id
	 */
	public static void applyFriend(String id){
		cs.sendMessage(new MessageVO("applyfriend", id));
	}
	/**
	 * �ظ���������
	 * @param isAgree �Ƿ�ͬ��
	 */
	public static void replyFriend(ReplyVO rvo){
		cs.sendMessage(new MessageVO("replyfriend", rvo));
	}
	/**
	 * �õ��������а񣨼������б�������
	 */
	public static void getMyRank(){
		cs.sendMessage(new MessageVO("friendrank", null));
	}
	/**
	 * �õ��������а�������
	 */
	public static void getWorldRank(){
		cs.sendMessage(new MessageVO("worldrank",null));
	}
	/**
	 * �õ��ҵĵ���
	 */
	public static void getMyTools(){
		cs.sendMessage(new MessageVO("mytool", null));
	}
	/**
	 * ʹ�õ���
	 * @param toolID ����id
	 */
	public static void useTool(String toolID){
		cs.sendMessage(new MessageVO("usetool", toolID));
	}
	/**
	 * �������
	 * @param toolID ����id
	 */
	public static void buyTool(String toolID){
		cs.sendMessage(new MessageVO("buytool", toolID));
	}
	/**
	 * ��������
	 * @param charID ����id
	 */
	public static void buyCharacter(String charID){
		cs.sendMessage(new MessageVO("buycharacter", charID));
	}
	/**
	 * �õ������Ƿ����
	 */
	public static void getCharacter(){
		cs.sendMessage(new MessageVO("getcharacter", null));
	}
	/**
	 * �õ����ߺ���
	 */
	public static void getOnlinePal(){
		cs.sendMessage(new MessageVO("onlinepal", null));
	}
	/**
	 * ��������ѽ���Э��
	 * @param fid ����id
	 */
	public static void cooperateWithPal(String fid){
		cs.sendMessage(new MessageVO("coopwithpal", fid));
	}
	/**
	 * ���ƥ�����Э��
	 */
	public static void cooperateRandom(){
		cs.sendMessage(new MessageVO("cooprandom", null));
	}
	/**
	 * �ظ�Э������(p2)
	 * @param rvo �Ƿ�ͬ��,�Է�id
	 */
	public static void replyCooperation(ReplyVO rvo){
		cs.sendMessage(new MessageVO("replycoop", rvo));
	}
	/**
	 * ����ѽ���һ��һpk
	 * @param fid ����id
	 */
	public static void pkWithPal(String fid){
		cs.sendMessage(new MessageVO("pkwithpal", fid));
	}
	/**
	 * ���ƥ�����pk
	 */
	public static void pkRandom(){
		cs.sendMessage(new MessageVO("pkrandom", null));
	}
	/**
	 * �ظ�pk����
	 * @param rvo �Ƿ�ͬ��,�Է�id
	 */
	public static void replyPk(ReplyVO rvo){
		cs.sendMessage(new MessageVO("replypk", rvo));
	}
	/**
	 * ��ʼ������Ϸ�����߷�������æ��
	 */
	public static void startSingle(){
		cs.sendMessage(new MessageVO("startsingle", null));
	}
	/**
	 * ���㵥����Ϸ
	 * @param gvo ��Ϸ��ã������÷֣����飬��ң�
	 */
	public static void endSingle(GainVO gvo){
		cs.sendMessage(new MessageVO("endsingle", gvo));
	}
	/**
	 * ����˫��Э��
	 * @param gvo ��Ϸ��ã������÷֣����飬��ң�
	 */
	public static void endCooperation(GainVO gvo){
		cs.sendMessage(new MessageVO("endcoop", gvo));
	}
	/**
	 * ����һ��һpk
	 * @param gvo ��Ϸ��ã������÷֣����飬��ң�
	 */
	public static void endPK(GainVO gvo){
		cs.sendMessage(new MessageVO("endpk", gvo));
	}
	/**
	 * ��������Э��
	 * @param gvo ��Ϸ���
	 */
	public static void endCoopFour(GainVO gvo){
		cs.sendMessage(new MessageVO("endcoopfour", gvo));
	}
	/**
	 * ��������PK
	 * @param gvo ��Ϸ���
	 */
	public static void endPkFour(GainVO gvo){
		cs.sendMessage(new MessageVO("endpkfour", gvo));
	}
	/**
	 * ���������ɵ���ɫѭ�����и��߷�����
	 */
	public static void setColorQueue(ColorQueueVO qvo){
		cs.sendMessage(new MessageVO("sendqueue", qvo));
	}
	/**
	 * ����������ͽ�������
	 * @param savo
	 */
	public static void trySwap(SwapActionVO savo){
		cs.sendMessage(new MessageVO("swap", savo));
	}
	
	/**
	 * ����Э���������
	 */
	public static void foursomeCoop(){
		cs.sendMessage(new MessageVO("foursomecoop", null));
	}
	/**
	 * ���������ս�������
	 */
	public static void foursomeComp(){
		cs.sendMessage(new MessageVO("foursomecomp", null));
	}
	/**
	 * ȡ��ƥ��
	 */
	public static void quitRandom(){
		cs.sendMessage(new MessageVO("quitrandom", null));
	}
	/**
	 * pk������һ�������˵���B������һ�����һ����ɫ�޷��ƶ�2s
	 */
	public static void deleteB(){
		cs.sendMessage(new MessageVO("deleteb", null));
	}
	/**
	 * pk������һ������޵�״̬����������һ������C�Ĺ���5��
	 */
	public static void superTime(){
		cs.sendMessage(new MessageVO("supertime", null));
	}
	/**
	 * ��;�˳���Ϸ
	 */
	public static void quitGame(){
		cs.sendMessage(new MessageVO("quit", null));
	}
	/**
	 * ��ѯ������Ϸ��¼
	 */
	public static void queryRecord(){
		cs.sendMessage(new MessageVO("queryrecord", null));
	}
	/**
	 * ���¸�����Ϸ��¼
	 */
	public static void updateRecord(RecordVO rvo){
		cs.sendMessage(new MessageVO("updaterecord", rvo));
	}
	
}

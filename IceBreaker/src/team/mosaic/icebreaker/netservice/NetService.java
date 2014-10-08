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
 * 提供给模型的网络通信接口
 * @author HJW
 *
 */
public class NetService {

	private static ClientSocket cs = ClientSocket.getInstance();
	
	/**
	 * 注册
	 * @param avo 帐户信息（帐号密码）
	 */
	public static void register(AccountVO avo){
		cs.sendMessage(new MessageVO("register", avo));
	}
	/**
	 * 登陆
	 * @param avo 帐户信息（帐号密码）
	 */
	public static void signIn(AccountVO avo){
		cs.sendMessage(new MessageVO("signin", avo));
	}
	/**
	 * 注销
	 */
	public static void signOut(){
		cs.sendMessage(new MessageVO("signout", null));
	}
	/**
	 * 得到玩家个人信息
	 */
	public static void getInfo(){
		cs.sendMessage(new MessageVO("info", null));
	}
	/**
	 * 申请添加好友
	 * @param id 对方id
	 */
	public static void applyFriend(String id){
		cs.sendMessage(new MessageVO("applyfriend", id));
	}
	/**
	 * 回复好友申请
	 * @param isAgree 是否同意
	 */
	public static void replyFriend(ReplyVO rvo){
		cs.sendMessage(new MessageVO("replyfriend", rvo));
	}
	/**
	 * 得到好友排行榜（即好友列表，已排序）
	 */
	public static void getMyRank(){
		cs.sendMessage(new MessageVO("friendrank", null));
	}
	/**
	 * 得到世界排行榜（已排序）
	 */
	public static void getWorldRank(){
		cs.sendMessage(new MessageVO("worldrank",null));
	}
	/**
	 * 得到我的道具
	 */
	public static void getMyTools(){
		cs.sendMessage(new MessageVO("mytool", null));
	}
	/**
	 * 使用道具
	 * @param toolID 道具id
	 */
	public static void useTool(String toolID){
		cs.sendMessage(new MessageVO("usetool", toolID));
	}
	/**
	 * 购买道具
	 * @param toolID 道具id
	 */
	public static void buyTool(String toolID){
		cs.sendMessage(new MessageVO("buytool", toolID));
	}
	/**
	 * 购买人物
	 * @param charID 人物id
	 */
	public static void buyCharacter(String charID){
		cs.sendMessage(new MessageVO("buycharacter", charID));
	}
	/**
	 * 得到人物是否解锁
	 */
	public static void getCharacter(){
		cs.sendMessage(new MessageVO("getcharacter", null));
	}
	/**
	 * 得到在线好友
	 */
	public static void getOnlinePal(){
		cs.sendMessage(new MessageVO("onlinepal", null));
	}
	/**
	 * 申请与好友进行协作
	 * @param fid 好友id
	 */
	public static void cooperateWithPal(String fid){
		cs.sendMessage(new MessageVO("coopwithpal", fid));
	}
	/**
	 * 随机匹配进行协作
	 */
	public static void cooperateRandom(){
		cs.sendMessage(new MessageVO("cooprandom", null));
	}
	/**
	 * 回复协作请求(p2)
	 * @param rvo 是否同意,对方id
	 */
	public static void replyCooperation(ReplyVO rvo){
		cs.sendMessage(new MessageVO("replycoop", rvo));
	}
	/**
	 * 与好友进行一对一pk
	 * @param fid 好友id
	 */
	public static void pkWithPal(String fid){
		cs.sendMessage(new MessageVO("pkwithpal", fid));
	}
	/**
	 * 随机匹配进行pk
	 */
	public static void pkRandom(){
		cs.sendMessage(new MessageVO("pkrandom", null));
	}
	/**
	 * 回复pk请求
	 * @param rvo 是否同意,对方id
	 */
	public static void replyPk(ReplyVO rvo){
		cs.sendMessage(new MessageVO("replypk", rvo));
	}
	/**
	 * 开始单人游戏（告诉服务器正忙）
	 */
	public static void startSingle(){
		cs.sendMessage(new MessageVO("startsingle", null));
	}
	/**
	 * 结算单人游戏
	 * @param gvo 游戏获得（包括得分，经验，金币）
	 */
	public static void endSingle(GainVO gvo){
		cs.sendMessage(new MessageVO("endsingle", gvo));
	}
	/**
	 * 结算双人协作
	 * @param gvo 游戏获得（包括得分，经验，金币）
	 */
	public static void endCooperation(GainVO gvo){
		cs.sendMessage(new MessageVO("endcoop", gvo));
	}
	/**
	 * 结算一对一pk
	 * @param gvo 游戏获得（包括得分，经验，金币）
	 */
	public static void endPK(GainVO gvo){
		cs.sendMessage(new MessageVO("endpk", gvo));
	}
	/**
	 * 结算四人协作
	 * @param gvo 游戏获得
	 */
	public static void endCoopFour(GainVO gvo){
		cs.sendMessage(new MessageVO("endcoopfour", gvo));
	}
	/**
	 * 结算四人PK
	 * @param gvo 游戏获得
	 */
	public static void endPkFour(GainVO gvo){
		cs.sendMessage(new MessageVO("endpkfour", gvo));
	}
	/**
	 * 主机将生成的颜色循环队列告诉服务器
	 */
	public static void setColorQueue(ColorQueueVO qvo){
		cs.sendMessage(new MessageVO("sendqueue", qvo));
	}
	/**
	 * 向服务器发送交换请求
	 * @param savo
	 */
	public static void trySwap(SwapActionVO savo){
		cs.sendMessage(new MessageVO("swap", savo));
	}
	
	/**
	 * 四人协作（随机）
	 */
	public static void foursomeCoop(){
		cs.sendMessage(new MessageVO("foursomecoop", null));
	}
	/**
	 * 四人两组对战（随机）
	 */
	public static void foursomeComp(){
		cs.sendMessage(new MessageVO("foursomecomp", null));
	}
	/**
	 * 取消匹配
	 */
	public static void quitRandom(){
		cs.sendMessage(new MessageVO("quitrandom", null));
	}
	/**
	 * pk过程中一方消除了道具B，则另一方随机一种颜色无法移动2s
	 */
	public static void deleteB(){
		cs.sendMessage(new MessageVO("deleteb", null));
	}
	/**
	 * pk过程中一方变成无敌状态，则消除另一方道具C的功能5秒
	 */
	public static void superTime(){
		cs.sendMessage(new MessageVO("supertime", null));
	}
	/**
	 * 中途退出游戏
	 */
	public static void quitGame(){
		cs.sendMessage(new MessageVO("quit", null));
	}
	/**
	 * 查询个人游戏记录
	 */
	public static void queryRecord(){
		cs.sendMessage(new MessageVO("queryrecord", null));
	}
	/**
	 * 更新个人游戏记录
	 */
	public static void updateRecord(RecordVO rvo){
		cs.sendMessage(new MessageVO("updaterecord", rvo));
	}
	
}

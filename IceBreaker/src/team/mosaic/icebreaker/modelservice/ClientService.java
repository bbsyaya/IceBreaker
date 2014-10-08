package team.mosaic.icebreaker.modelservice;

import java.util.ArrayList;

import team.mosaic.icebreaker.vo.ColorQueueVO;
import team.mosaic.icebreaker.vo.InfoVO;
import team.mosaic.icebreaker.vo.RecordVO;
import team.mosaic.icebreaker.vo.ReplyVO;
import team.mosaic.icebreaker.vo.ResultVO;
import team.mosaic.icebreaker.vo.ScoreVO;
import team.mosaic.icebreaker.vo.SwapActionVO;
import team.mosaic.icebreaker.vo.ToolVO;

public interface ClientService {
	/**
	 * 返回登陆验证结果
	 * @param isVerified 验证结果
	 */
	public void login(boolean isVerified);
	/**
	 * 返回注册验证结果
	 * @param isVerified 验证结果
	 */
	public void register(boolean isVerified);
	/**
	 * 返回个人信息
	 * @param ivo 个人信息
	 */
	public void myInfo(InfoVO ivo);
	/**
	 * 返回我的道具
	 * @param tools 道具(id,数量)
	 */
	public void myTools(ArrayList<ToolVO> tools);
	/**
	 * 购买道具结果
	 * @param b 结果
	 */
	public void buyToolResult(boolean b);
	/**
	 * 返回好友排行榜
	 * @param rank 好友排行榜（已排序）
	 */
	public void firendRank(ArrayList<ScoreVO> rank);
	/**
	 * 返回世界排行榜
	 * @param rank 世界排行榜（已排序）
	 */
	public void worldRank(ArrayList<ScoreVO> rank);
	/**
	 * 返回在线好友
	 * @param friends 在线好友
	 */
	public void onlineFriends(ArrayList<String> friends);
	/**
	 * 收到协作邀请
	 * @param id 邀请者id
	 */
	public void coopInvited(String id);
	/**
	 * 收到pk邀请
	 * @param id 邀请者id
	 */
	public void pkInvited(String id);
	/**
	 * 对方回应邀请结果（不同意，同意的话会直接开始游戏）
	 * @param rvo 答复结果，答复者id
	 */
	public void invitationResult(ReplyVO rvo);
	/**
	 * 设置为主机
	 */
	public void setHost();
	/**
	 * 同步动作
	 * @param action 移动、消除、产生新方块
	 */
	public void act(SwapActionVO svo);
	/**
	 * 购买人物结果
	 * @param b 结果
	 */
	public void buyCharacterResult(boolean b);
	/**
	 * 角色信息
	 * @param CharacterVO[] 
	 */
	public void myCharacters(boolean[] c);
	/**
	 * 被添加好友
	 * @param String 申请者id 
	 */
	public void friendsApply(String id);
	/**
	 * 好友申请结果
	 * @param String 申请者id 
	 */
	public void resultOffriendsApply(ReplyVO rvo);

	/**
	 * 收到初始化的盘面
	 * @param qvo 初始化的队列
	 */
	public void sendQueue(ColorQueueVO qvo);
	/**
	 * 显示游戏结果
	 * @param rvo 游戏结果
	 */
	public void showResult(ResultVO rvo);
	/**
	 * pk模式下锁定某一种方块
	 */
	public void randomLock();
	/**
	 * pk模式下道具C失效
	 */
	public void lockC();
	/**
	 * 玩家提前退出了游戏
	 * @param id 玩家id
	 */
	public void someoneQuit(String id);
	
	public void findCoopPair(String id);
	
	public void findCoopFour(String[] id);
	
	public void findPKFour(String[] id);

	/**
	 * 开始双人协作
	 */
	public void startTwosome();
	/**
	 * 开始四人协作
	 */
	public void startFoursome();
	/**
	 * 开始pk
	 */
	public void startPk(final String id);
	/**
	 * 从服务器传来记录数据
	 */
	public void sendRecords(ArrayList<RecordVO> records);
	
	public void notFoundPlayer();
}

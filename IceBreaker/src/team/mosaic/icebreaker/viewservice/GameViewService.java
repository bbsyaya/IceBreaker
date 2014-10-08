package team.mosaic.icebreaker.viewservice;

import team.mosaic.icebreaker.vo.GainVO;

/**
 * 游戏界面接口
 */
public interface GameViewService {
	
	/**
	 * 显示人物
	 * @param character 1为Anna，2为Kristoff，3为Sven，4为Elsa
	 */
	public void showChar(int character);
	
	/**
	 * 改变计时器  
	 * @param time 参数为int，单位为秒
	 */
	public void refreshTime(int time);
	
	/**
	 * 改变连击计数器 面板中尚未添加连击区域
	 * @param count 参数为int
	 */
	public void refreshCombo(int count);
	
	/**
	 * 改变得分
	 * @param score
	 */
	public void refreshScore(int score);
	/**
	 * 结算游戏
	 * @param gvo 所获金币、经验、分数
	 */
	public void end(GainVO gvo);

	/**
	 * 初始化单人游戏界面
	 * @param character 所选人物
	 * @param tools 所选道具
	 */
	public void initSingle(int character,boolean[] tools);
	
	/**
	 * 初始化两人协作游戏界面
	 * @param character 所选人物
	 * @param player2 对方玩家id
	 * @param tools 所选道具
	 */
	public void initCoop(int character,String player2,boolean[] tools);
	
	/**
	 * 初始化两人对战游戏界面
	 * @param character 所选人物
	 * @param player2 对方玩家id
	 * @param tools 所选道具
	 */
	public void initPk(int character,String player2,boolean[] tools);
	
	/**
	 * 初始化四人协作游戏界面
	 * @param character 所选人物
	 * @param others 相关玩家
	 * @param tools 所选道具
	 */
	public void initCoopFour(int character,String[] others,boolean[] tools);
	
	/**
	 * 初始化四人对战游戏界面
	 * @param character 所选人物
	 * @param others 相关玩家
	 * @param tools 所选道具
	 */
	public void initPKFour(int character,String[] others,boolean[] tools);
	
	/**
	 * 其他玩家中途退出游戏
	 * @param id 玩家id
	 */
	public void someoneQuit(String id);
	
	/**
	 * 锁定道具C
	 */
	public void lockToolC();
	
	/**
	 * 解锁道具C
	 */
	public void unlockToolC();
	
}

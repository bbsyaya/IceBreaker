package team.mosaic.icebreaker.modelservice;

import team.mosaic.icebreaker.vo.ColorQueueVO;
import team.mosaic.icebreaker.vo.PositionVO;

/**
 * 色块界面模型接口
 * @author acer
 *
 */
public interface BoardModelService {

	public void stopGame();
	
	/**
	 * 判断两个位置是否可以进行交换，如果可以非协作模式就进行交换，协作模式会向服务器发送请求
	 * 协作模式下并不是真正改变盘面，改变盘面会等服务器回应后	
	 * @param p1
	 * @param p2
	 * @return
	 */
	
	public boolean trySwap(PositionVO p1,PositionVO p2);
	
	/**
	 * 对道具A，B的单独消除
	 * 如果该坐标不是道具返回false
	 */	
	public boolean useToolGrid(PositionVO p);

	/**
	 * 设置主玩家
	 */
	public void setMainPlayer();
	
	/**
	 * 设置副玩家
	 * @param queue
	 */
	public void setSubPlayer(ColorQueueVO queue);
	
	/**
	 * 多人游戏时直接交换
	 * @param p1
	 * @param p2
	 * @throws Exception
	 */
	public void swap(PositionVO p1,PositionVO p2) throws Exception;
	
	/**
	 * 多人游戏时直接消除道具	
	 * @param p
	 */
	public void useToolGridwithNet(PositionVO p);

	public void lockColor();
	
	public void lockToolC();
	
	public int[][] grids();

	public int direction();
	
	public void startTimer();
}

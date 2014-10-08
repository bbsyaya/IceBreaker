package team.mosaic.icebreaker.viewservice;

import java.util.ArrayList;


/**
 *更新道具界面接口
 */
public interface ToolViewService {
	
	/**
	 * 改变玩家拥有的道具数
	 * @param num
	 */
public void updateToolNumbers(ArrayList<Integer> num);
/**
 * 购买后改变玩家拥有的道具数
 * @param num,b
 */
public void updateToolNumbers(ArrayList<Integer> num,boolean b);

}

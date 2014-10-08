package team.mosaic.icebreaker.modelservice;

import java.util.ArrayList;

public interface ToolModelService {
	/**
	 * 购买道具
	 * @param int
	 */
	public void buyTool(int toolID);
	/**
	 * 返回道具的个数
	 * @param
	 */
	public void toolNumber();
	/**
	 * 展示道具
	 * @param
	 */
	public void showTools();
	/**
	 * 将道具数据保存到本地
	 * @param
	 */
	public void saveTools(ArrayList<Integer> num);
	/**
	 * 道具购买结果
	 * @param
	 */
	public void buyToolResult(boolean b);
	
}

package team.mosaic.icebreaker.viewservice;

import java.util.ArrayList;

import team.mosaic.icebreaker.vo.MoveandCreateActionVO;
import team.mosaic.icebreaker.vo.PositionVO;

public interface BoardViewService {

	/**
	 * 初始化/刷新界面
	 * @param  参数为int[9][9],数值1~6代表六种方块
	 */
	public void initBoard(int[][] dot);
	
	/**
	 * 交换游戏单元
	 * @param PositionVO p1,PositionVO p2 交换的两个单元的位置
	 */
	public void swapUnit(PositionVO p1,PositionVO p2);
	
	/**
	 * 交换游戏单元两次
	 * @param p1 位置1
	 * @param p2 位置2
	 */
	public void swapUnit2(PositionVO p1,PositionVO p2);
	
	/**
	 * 消除一系列单元
	 * @param int[][] dbChange
	 */
	public void deleteUnits(int[][] dbChange);
	
	/**
	 * 移动或者生成新的游戏单元
	 * @param ArrayList<MoveandCreateActionVO> avoList
	 * @throws Exception 
	 */
	public void dealWithMoveAction(ArrayList<MoveandCreateActionVO> avoList) throws Exception;
	
	/**
	 * 更新颜色数组
	 * @param dot 9*9数组
	 */
	public void setGrids(int[][] dot);

	/**
	 * 提示
	 * @param 可以交换的2个位置
	 */
	public void prompt(PositionVO[] pvo);
	/**
	 * 取消提示
	 */
	public void noprompt();
	/**
	 * 提示冰雪连击
	 */
	public void iceCombo();
	/**
	 * 锁定某种颜色不能移动
	 * @param color 颜色
	 */
	public void lock(int color);
	/**
	 * 解锁某种不能移动的颜色方块
	 * @param color 颜色
	 */
	public void unlock(int color);
	
}

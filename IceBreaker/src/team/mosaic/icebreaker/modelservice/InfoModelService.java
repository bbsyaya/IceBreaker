package team.mosaic.icebreaker.modelservice;

import java.util.ArrayList;
import team.mosaic.icebreaker.vo.InfoVO;
import team.mosaic.icebreaker.vo.RecordVO;


public interface InfoModelService {
	/**
	 * 得到个人信息
	 * @param
	 */
	public void fetchInfo();
	/**
	 * 显示个人信息
	 * @param
	 */
	public void showInfo(InfoVO ivo);
	/**
	 * 判断人物是否被解锁
	 * @param int
	 */
	public void ifBought(int i);
	
	/**
	 * 购买人物
	 * @param i
	 */
	public void buyChar(int i);
	
	/**
	 * 给net调用，得到人物是否购买列表
	 */
	public void setChar(ArrayList<Boolean> list);
	/**
	 * 给model调用，更新人物列表
	 */
	public void updateChar();
	/**
	 * 单人游戏结束后保存记录
	 * @param 
	 */
	public void saveRecord(RecordVO rvo);
	/**
	 * 展示总局数和平均分
	 * @param 
	 */
	public void showTotalRecord();
	/**
	 * 展示每日平均分
	 * @param 
	 */
	public void showAvg();
	/**
	 * 展示每日最好成绩
	 * @param 
	 */
	public void showBest();
	/**
	 * 展示每局得分
	 * @param 
	 */
	public void showEveryGame();
	/**
	 * 展示每日局数统计
	 * @param 
	 */
	public void showGameCount();
	/**
	 * 展示每日最高连击
	 * @param 
	 */
	public void showMaxCombo();
	/**
	 * 购买人物结果
	 * @param b 结果
	 */
	public void buyCharacterResult(boolean b);
	/**
	 * 把选择的人物写入本地
	 * @param id 玩家id
	 * @param index 人物编号
	 */
	public void writeCharacter(String id,int index);
	/**
	 * 处理服务器传来的记录
	 * @param 
	 */
	public void dealRecords(ArrayList<String> records);
}

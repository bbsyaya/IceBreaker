package team.mosaic.icebreaker.viewservice;

import java.util.ArrayList;

import team.mosaic.icebreaker.vo.InfoVO;

public interface InfoViewService {
	
	
	/*
	 * 改变人物接口
	 */
	public void setCharacter(int i,boolean b);
	
	/*
	 * 显示用户信息接口
	 */
	
	public void showInfo(InfoVO iv);
	

	/**
	 * 得到购买人物的结果
	 * @param i
	 * @param b
	 */
	public void getChar(int i,boolean b);
	/**
	 * 展示总记录
	 * @param 总局数，总分数
	 */
	public void showTotalRecord(int gameCount,int avgScore);
	/**
	 * 展示每日平均分
	 * @param 
	 */
	public void showAvg(ArrayList<String> list);
	/**
	 * 展示每日最好成绩
	 * @param 
	 */
	public void showBest(ArrayList<String> list);
	/**
	 * 展示局数的风统计
	 * @param 
	 */
	public void showEveryGame(ArrayList<String> list);
	/**
	 * 展示每日局数统计
	 * @param 
	 */
	public void showGameCount(ArrayList<String> list);
	/**
	 * 展示每日最高连击
	 * @param 
	 */
	public void showMaxCombo(ArrayList<String> list);


}

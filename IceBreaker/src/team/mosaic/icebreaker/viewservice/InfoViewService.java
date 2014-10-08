package team.mosaic.icebreaker.viewservice;

import java.util.ArrayList;

import team.mosaic.icebreaker.vo.InfoVO;

public interface InfoViewService {
	
	
	/*
	 * �ı�����ӿ�
	 */
	public void setCharacter(int i,boolean b);
	
	/*
	 * ��ʾ�û���Ϣ�ӿ�
	 */
	
	public void showInfo(InfoVO iv);
	

	/**
	 * �õ���������Ľ��
	 * @param i
	 * @param b
	 */
	public void getChar(int i,boolean b);
	/**
	 * չʾ�ܼ�¼
	 * @param �ܾ������ܷ���
	 */
	public void showTotalRecord(int gameCount,int avgScore);
	/**
	 * չʾÿ��ƽ����
	 * @param 
	 */
	public void showAvg(ArrayList<String> list);
	/**
	 * չʾÿ����óɼ�
	 * @param 
	 */
	public void showBest(ArrayList<String> list);
	/**
	 * չʾ�����ķ�ͳ��
	 * @param 
	 */
	public void showEveryGame(ArrayList<String> list);
	/**
	 * չʾÿ�վ���ͳ��
	 * @param 
	 */
	public void showGameCount(ArrayList<String> list);
	/**
	 * չʾÿ���������
	 * @param 
	 */
	public void showMaxCombo(ArrayList<String> list);


}

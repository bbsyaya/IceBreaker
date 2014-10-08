package team.mosaic.icebreaker.viewservice;

import java.util.ArrayList;

import team.mosaic.icebreaker.vo.MoveandCreateActionVO;
import team.mosaic.icebreaker.vo.PositionVO;

public interface BoardViewService {

	/**
	 * ��ʼ��/ˢ�½���
	 * @param  ����Ϊint[9][9],��ֵ1~6�������ַ���
	 */
	public void initBoard(int[][] dot);
	
	/**
	 * ������Ϸ��Ԫ
	 * @param PositionVO p1,PositionVO p2 ������������Ԫ��λ��
	 */
	public void swapUnit(PositionVO p1,PositionVO p2);
	
	/**
	 * ������Ϸ��Ԫ����
	 * @param p1 λ��1
	 * @param p2 λ��2
	 */
	public void swapUnit2(PositionVO p1,PositionVO p2);
	
	/**
	 * ����һϵ�е�Ԫ
	 * @param int[][] dbChange
	 */
	public void deleteUnits(int[][] dbChange);
	
	/**
	 * �ƶ����������µ���Ϸ��Ԫ
	 * @param ArrayList<MoveandCreateActionVO> avoList
	 * @throws Exception 
	 */
	public void dealWithMoveAction(ArrayList<MoveandCreateActionVO> avoList) throws Exception;
	
	/**
	 * ������ɫ����
	 * @param dot 9*9����
	 */
	public void setGrids(int[][] dot);

	/**
	 * ��ʾ
	 * @param ���Խ�����2��λ��
	 */
	public void prompt(PositionVO[] pvo);
	/**
	 * ȡ����ʾ
	 */
	public void noprompt();
	/**
	 * ��ʾ��ѩ����
	 */
	public void iceCombo();
	/**
	 * ����ĳ����ɫ�����ƶ�
	 * @param color ��ɫ
	 */
	public void lock(int color);
	/**
	 * ����ĳ�ֲ����ƶ�����ɫ����
	 * @param color ��ɫ
	 */
	public void unlock(int color);
	
}

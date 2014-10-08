package team.mosaic.icebreaker.modelservice;

import java.util.ArrayList;
import team.mosaic.icebreaker.vo.InfoVO;
import team.mosaic.icebreaker.vo.RecordVO;


public interface InfoModelService {
	/**
	 * �õ�������Ϣ
	 * @param
	 */
	public void fetchInfo();
	/**
	 * ��ʾ������Ϣ
	 * @param
	 */
	public void showInfo(InfoVO ivo);
	/**
	 * �ж������Ƿ񱻽���
	 * @param int
	 */
	public void ifBought(int i);
	
	/**
	 * ��������
	 * @param i
	 */
	public void buyChar(int i);
	
	/**
	 * ��net���ã��õ������Ƿ����б�
	 */
	public void setChar(ArrayList<Boolean> list);
	/**
	 * ��model���ã����������б�
	 */
	public void updateChar();
	/**
	 * ������Ϸ�����󱣴��¼
	 * @param 
	 */
	public void saveRecord(RecordVO rvo);
	/**
	 * չʾ�ܾ�����ƽ����
	 * @param 
	 */
	public void showTotalRecord();
	/**
	 * չʾÿ��ƽ����
	 * @param 
	 */
	public void showAvg();
	/**
	 * չʾÿ����óɼ�
	 * @param 
	 */
	public void showBest();
	/**
	 * չʾÿ�ֵ÷�
	 * @param 
	 */
	public void showEveryGame();
	/**
	 * չʾÿ�վ���ͳ��
	 * @param 
	 */
	public void showGameCount();
	/**
	 * չʾÿ���������
	 * @param 
	 */
	public void showMaxCombo();
	/**
	 * ����������
	 * @param b ���
	 */
	public void buyCharacterResult(boolean b);
	/**
	 * ��ѡ�������д�뱾��
	 * @param id ���id
	 * @param index ������
	 */
	public void writeCharacter(String id,int index);
	/**
	 * ��������������ļ�¼
	 * @param 
	 */
	public void dealRecords(ArrayList<String> records);
}

package team.mosaic.icebreaker.viewservice;

import team.mosaic.icebreaker.vo.GainVO;

/**
 * ��Ϸ����ӿ�
 */
public interface GameViewService {
	
	/**
	 * ��ʾ����
	 * @param character 1ΪAnna��2ΪKristoff��3ΪSven��4ΪElsa
	 */
	public void showChar(int character);
	
	/**
	 * �ı��ʱ��  
	 * @param time ����Ϊint����λΪ��
	 */
	public void refreshTime(int time);
	
	/**
	 * �ı����������� �������δ�����������
	 * @param count ����Ϊint
	 */
	public void refreshCombo(int count);
	
	/**
	 * �ı�÷�
	 * @param score
	 */
	public void refreshScore(int score);
	/**
	 * ������Ϸ
	 * @param gvo �����ҡ����顢����
	 */
	public void end(GainVO gvo);

	/**
	 * ��ʼ��������Ϸ����
	 * @param character ��ѡ����
	 * @param tools ��ѡ����
	 */
	public void initSingle(int character,boolean[] tools);
	
	/**
	 * ��ʼ������Э����Ϸ����
	 * @param character ��ѡ����
	 * @param player2 �Է����id
	 * @param tools ��ѡ����
	 */
	public void initCoop(int character,String player2,boolean[] tools);
	
	/**
	 * ��ʼ�����˶�ս��Ϸ����
	 * @param character ��ѡ����
	 * @param player2 �Է����id
	 * @param tools ��ѡ����
	 */
	public void initPk(int character,String player2,boolean[] tools);
	
	/**
	 * ��ʼ������Э����Ϸ����
	 * @param character ��ѡ����
	 * @param others ������
	 * @param tools ��ѡ����
	 */
	public void initCoopFour(int character,String[] others,boolean[] tools);
	
	/**
	 * ��ʼ�����˶�ս��Ϸ����
	 * @param character ��ѡ����
	 * @param others ������
	 * @param tools ��ѡ����
	 */
	public void initPKFour(int character,String[] others,boolean[] tools);
	
	/**
	 * ���������;�˳���Ϸ
	 * @param id ���id
	 */
	public void someoneQuit(String id);
	
	/**
	 * ��������C
	 */
	public void lockToolC();
	
	/**
	 * ��������C
	 */
	public void unlockToolC();
	
}

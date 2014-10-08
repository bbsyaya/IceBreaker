package team.mosaic.icebreaker.modelservice;

import java.util.ArrayList;
public interface ReadyModelService {
	/**
	 * ��������������
	 * @param
	 */
	public void askCouple(String id);
	/**
	 * �����������������
	 * @param
	 */
	public void askCoupleRandom();
	/**
	 * �������Э��ƥ��
	 */
	public void randomCoopFour();
	/**
	 * ��������������
	 * @param
	 */
	public void PKWith(String id);
	/**
	 * �����������������
	 * @param
	 */
	public void PKRandom();
	/**
	 * �������PKƥ��
	 */
	public void randomPkFour();
	/**
	 * ���������ȡ������Ϣ
	 * @param resultVO
	 */
	public void getFriends();
	/**
	 * ��ʾ����
	 * @param resultVO
	 */
	public void showFriends(ArrayList<String> friends);
	 /**
	  * �����Ƿ�ӵ�иõ����ж��ܷ�ѡ��
	  * @param i
	  * @param b
	  */
	public void SelectTool(int i);
	
	/**
	 * ����ѡ��ʹ�õ��ߵ�����
	 * @param tool
	 */
	public void setTool(boolean[] tool );
	
	/**************for net below*********************/
	
	public void findCoopPair(String id);
	
	public void findCoopFour(String[] id);
	
	public void findPKFour(String[] id);

	/**
	 * ��ʼ˫��Э��
	 */
	public void startTwosome();
	/**
	 * ��ʼ����Э��
	 */
	public void startFoursome();
	/**
	 * ��ʼpk
	 */
	public void startPk(final String id);
	
}

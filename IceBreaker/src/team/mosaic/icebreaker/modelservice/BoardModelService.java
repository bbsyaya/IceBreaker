package team.mosaic.icebreaker.modelservice;

import team.mosaic.icebreaker.vo.ColorQueueVO;
import team.mosaic.icebreaker.vo.PositionVO;

/**
 * ɫ�����ģ�ͽӿ�
 * @author acer
 *
 */
public interface BoardModelService {

	public void stopGame();
	
	/**
	 * �ж�����λ���Ƿ���Խ��н�����������Է�Э��ģʽ�ͽ��н�����Э��ģʽ�����������������
	 * Э��ģʽ�²����������ı����棬�ı������ȷ�������Ӧ��	
	 * @param p1
	 * @param p2
	 * @return
	 */
	
	public boolean trySwap(PositionVO p1,PositionVO p2);
	
	/**
	 * �Ե���A��B�ĵ�������
	 * ��������겻�ǵ��߷���false
	 */	
	public boolean useToolGrid(PositionVO p);

	/**
	 * ���������
	 */
	public void setMainPlayer();
	
	/**
	 * ���ø����
	 * @param queue
	 */
	public void setSubPlayer(ColorQueueVO queue);
	
	/**
	 * ������Ϸʱֱ�ӽ���
	 * @param p1
	 * @param p2
	 * @throws Exception
	 */
	public void swap(PositionVO p1,PositionVO p2) throws Exception;
	
	/**
	 * ������Ϸʱֱ����������	
	 * @param p
	 */
	public void useToolGridwithNet(PositionVO p);

	public void lockColor();
	
	public void lockToolC();
	
	public int[][] grids();

	public int direction();
	
	public void startTimer();
}

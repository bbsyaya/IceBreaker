package team.mosaic.icebreaker.viewservice;

import java.util.ArrayList;


/**
 *���µ��߽���ӿ�
 */
public interface ToolViewService {
	
	/**
	 * �ı����ӵ�еĵ�����
	 * @param num
	 */
public void updateToolNumbers(ArrayList<Integer> num);
/**
 * �����ı����ӵ�еĵ�����
 * @param num,b
 */
public void updateToolNumbers(ArrayList<Integer> num,boolean b);

}

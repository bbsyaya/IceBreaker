package team.mosaic.icebreaker.modelservice;

import java.util.ArrayList;

public interface ToolModelService {
	/**
	 * �������
	 * @param int
	 */
	public void buyTool(int toolID);
	/**
	 * ���ص��ߵĸ���
	 * @param
	 */
	public void toolNumber();
	/**
	 * չʾ����
	 * @param
	 */
	public void showTools();
	/**
	 * ���������ݱ��浽����
	 * @param
	 */
	public void saveTools(ArrayList<Integer> num);
	/**
	 * ���߹�����
	 * @param
	 */
	public void buyToolResult(boolean b);
	
}

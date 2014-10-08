package team.mosaic.icebreaker.dto;

import team.mosaic.icebreaker.dao.ToolDAO;

public class ToolDTO {
	
	private ToolDAO toolDAO = new ToolDAO();
	/*
	 * ��Tool���д�������
	 * ע��ʱ
	 */
	public void creatNewToolObject(String id){		
		toolDAO.creat(id);	
	}
	
	/*
	 * �������
	 * @param  id �˺�     tool ���ߴ���   count ��Ӧ���ߵ�����,tool Ϊ t1,t2,t3,t4,t5
	 */
	public void buytool(String id,String tool,int count){
		toolDAO.buytool(id, tool, count);
	}

	/*
	 * ʹ�õ��ߣ�Ĭ��ʹ��һ��
	 * @param id �˺�    tool ���ߴ���,t1,t2,t3,t4,t5
	 */
	public void usetool(String id,String tool){
        toolDAO.usetool(id, tool);
	}
	
	/*
	 * �鿴������Ŀ
	 * @param id �˺�  tool ���ߴ���
	 * @return  number
	 */
	public int toolnumber(String id,String tool){
		int number = toolDAO.toolnumber(id, tool);
		return number;
	}
}

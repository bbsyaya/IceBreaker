package team.mosaic.icebreaker.dto;

import team.mosaic.icebreaker.dao.ToolDAO;

public class ToolDTO {
	
	private ToolDAO toolDAO = new ToolDAO();
	/*
	 * 在Tool表中创建对象
	 * 注册时
	 */
	public void creatNewToolObject(String id){		
		toolDAO.creat(id);	
	}
	
	/*
	 * 购买道具
	 * @param  id 账号     tool 道具代号   count 相应道具的数量,tool 为 t1,t2,t3,t4,t5
	 */
	public void buytool(String id,String tool,int count){
		toolDAO.buytool(id, tool, count);
	}

	/*
	 * 使用道具，默认使用一次
	 * @param id 账号    tool 道具代号,t1,t2,t3,t4,t5
	 */
	public void usetool(String id,String tool){
        toolDAO.usetool(id, tool);
	}
	
	/*
	 * 查看道具数目
	 * @param id 账号  tool 道具代号
	 * @return  number
	 */
	public int toolnumber(String id,String tool){
		int number = toolDAO.toolnumber(id, tool);
		return number;
	}
}

package team.mosaic.icebreaker.dto;

import team.mosaic.icebreaker.dao.RoleDAO;

public class RoleDTO {
	
	private RoleDAO roleDAO = new RoleDAO();
	/*
	 * 在Role表中创建对象
	 * 注册时
	 * @return boolean 注册成功返回true  失败则说明账号已存在
	 */
	public void creatNewRoleObject(String id){
		
		roleDAO.creat(id);
	}

	/*
	 * 判断角色是否解锁，四个角色分别为  role1,  role2,  role3,  role4
	 * @param id 账号   role   (r1,r2,r3,r4)
	 * @return boolean  true 则已解锁，玩家可使用
	 */	
	public boolean ismyrole(String id,String role){
		boolean b = false;
		if (roleDAO.ismyrole(id, role)) {
			b = true;
		}
		return b;
	}
	
	
	/*
	 * 解锁人物
	 *  @param id 账号       role    ( r1,r2,r3,r4)
	 */
	public void getrole(String id,String role){
		roleDAO.getmyrole(id, role);
	}
	
}

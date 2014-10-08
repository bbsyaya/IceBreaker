package team.mosaic.icebreaker.dto;

import team.mosaic.icebreaker.dao.RoleDAO;

public class RoleDTO {
	
	private RoleDAO roleDAO = new RoleDAO();
	/*
	 * ��Role���д�������
	 * ע��ʱ
	 * @return boolean ע��ɹ�����true  ʧ����˵���˺��Ѵ���
	 */
	public void creatNewRoleObject(String id){
		
		roleDAO.creat(id);
	}

	/*
	 * �жϽ�ɫ�Ƿ�������ĸ���ɫ�ֱ�Ϊ  role1,  role2,  role3,  role4
	 * @param id �˺�   role   (r1,r2,r3,r4)
	 * @return boolean  true ���ѽ�������ҿ�ʹ��
	 */	
	public boolean ismyrole(String id,String role){
		boolean b = false;
		if (roleDAO.ismyrole(id, role)) {
			b = true;
		}
		return b;
	}
	
	
	/*
	 * ��������
	 *  @param id �˺�       role    ( r1,r2,r3,r4)
	 */
	public void getrole(String id,String role){
		roleDAO.getmyrole(id, role);
	}
	
}

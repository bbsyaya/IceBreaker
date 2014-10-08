package team.mosaic.icebreaker.dto;

import java.util.ArrayList;

import team.mosaic.icebreaker.dao.AccountDAO;
import team.mosaic.icebreaker.dao.HeadDAO;

public class AccountDTO {
	
	private AccountDAO accountDAO = new AccountDAO();
	private HeadDAO headDAO = new HeadDAO();
	
	/*
	 * �õ�����
	 * @param  id  �˺�
	 * @return  boolean �����ڷ��� null  
	 */
	public String getpassword(String id){
		String password = null;
		password = accountDAO.getpassword(id);
		return password;
	}

	/*
	 * �����û�
	 * @param   id �˺�     password  ����
	 * @return  boolean     
	 */
	public boolean addID(String id,String password){
		boolean b = false;
		if (accountDAO.addID(id, password)) {
			b = true;
		}
		return b;
	}
	
	/*
	 * �õ������û��˺�
	 * @return   ArrayList<String>   idArrayList
	 */
	public ArrayList<String>  getAllAccount(){
		ArrayList<String>  idArrayList = new ArrayList<>();
		idArrayList = accountDAO.getAllAccount();
		return idArrayList;
	}
	
	/*
	 * ����ͷ��
	 * @param id �˺� head_path ͼƬ·��
	 */
	public void sethead(String id,String head_path){
		headDAO.sethead(id, head_path);
	}
	
	/*
	 * �õ�ͼƬ·��
	 * @return string 
	 */
	public String gethead(String id){
		return headDAO.gethead(id);
	}
	
//	public static void main(String[] args){
//		AccountDTO accountDTO = new AccountDTO();
//		accountDTO.sethead("hgda","E:/HuY_Space/SelfPractise/photofile/photo/3.jpg");
//		System.out.println(accountDTO.gethead("hu"));
//		System.out.println(accountDTO.getAllAccount());
//		accountDTO.addID("anmy", "abcd123");
//		System.out.println(accountDTO.getpassword("anmy"));
//	}
}

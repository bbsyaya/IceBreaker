package team.mosaic.icebreaker.dto;

import java.util.ArrayList;

import team.mosaic.icebreaker.dao.AccountDAO;
import team.mosaic.icebreaker.dao.HeadDAO;

public class AccountDTO {
	
	private AccountDAO accountDAO = new AccountDAO();
	private HeadDAO headDAO = new HeadDAO();
	
	/*
	 * 得到密码
	 * @param  id  账号
	 * @return  boolean 不存在返回 null  
	 */
	public String getpassword(String id){
		String password = null;
		password = accountDAO.getpassword(id);
		return password;
	}

	/*
	 * 增加用户
	 * @param   id 账号     password  密码
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
	 * 得到所有用户账号
	 * @return   ArrayList<String>   idArrayList
	 */
	public ArrayList<String>  getAllAccount(){
		ArrayList<String>  idArrayList = new ArrayList<>();
		idArrayList = accountDAO.getAllAccount();
		return idArrayList;
	}
	
	/*
	 * 设置头像
	 * @param id 账号 head_path 图片路径
	 */
	public void sethead(String id,String head_path){
		headDAO.sethead(id, head_path);
	}
	
	/*
	 * 得到图片路径
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

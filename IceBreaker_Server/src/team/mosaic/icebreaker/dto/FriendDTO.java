package team.mosaic.icebreaker.dto;

import java.util.ArrayList;

import team.mosaic.icebreaker.dao.FriendDAO;

public class FriendDTO {
	
	
	private FriendDAO friendDAO = new FriendDAO();
	/*
	 * 得到好友列表
	 * @param   String   id 账号
	 * @return  ArrayList<String>   friendsList  好友列表
	 */
	public ArrayList<String> getFriends(String id){
		ArrayList<String>  friendsList = new ArrayList<>();
		friendsList = friendDAO.getFriends(id);
		return friendsList;
	}
	
	
	/*
	 * 增加好友
	 * @param   id1   账号     id2 另一方账号    
	 */
	public void addFriend(String id1,String id2){
		friendDAO.addFriend(id1, id2);
	}
	
	
	/*
	 * 删除好友
	 * @param   id1   账号     id2 另一方账号,删除id1的好友id2     
	 */
	public void removeFriend(String id1,String id2){
		friendDAO.removeFriend(id1, id2);
	}
	

}

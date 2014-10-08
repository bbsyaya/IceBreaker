package team.mosaic.icebreaker.dto;

import java.util.ArrayList;

import team.mosaic.icebreaker.dao.FriendDAO;

public class FriendDTO {
	
	
	private FriendDAO friendDAO = new FriendDAO();
	/*
	 * �õ������б�
	 * @param   String   id �˺�
	 * @return  ArrayList<String>   friendsList  �����б�
	 */
	public ArrayList<String> getFriends(String id){
		ArrayList<String>  friendsList = new ArrayList<>();
		friendsList = friendDAO.getFriends(id);
		return friendsList;
	}
	
	
	/*
	 * ���Ӻ���
	 * @param   id1   �˺�     id2 ��һ���˺�    
	 */
	public void addFriend(String id1,String id2){
		friendDAO.addFriend(id1, id2);
	}
	
	
	/*
	 * ɾ������
	 * @param   id1   �˺�     id2 ��һ���˺�,ɾ��id1�ĺ���id2     
	 */
	public void removeFriend(String id1,String id2){
		friendDAO.removeFriend(id1, id2);
	}
	

}

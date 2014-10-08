package team.mosaic.icebreaker.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import team.mosaic.icebreaker.entity.JDBC_Connection;

public class FriendDAO {
	
	private	JDBC_Connection jdbc_Connection = JDBC_Connection.getInstance();
	private Statement stmt = jdbc_Connection.getStatement();
	
	public ArrayList<String> getFriends(String id){
		ArrayList<String> friendsArrayList = new ArrayList<>();
		ResultSet rs;
		String sql = "select friendid from friend where userid = "+"'"+id+"'"+"";
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				
				friendsArrayList.add(rs.getString("friendid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return friendsArrayList;
	}
	
	
	public void addFriend(String id1,String id2){
		String sql = "insert into friend (userid,friendid) values ("+"'"+id1+"'"+","+"'"+id2+"'"+")";
		String sql2 = "insert into friend (userid,friendid) values ("+"'"+id2+"'"+","+"'"+id1+"'"+")";
		try {
			stmt.execute(sql);
			stmt.execute(sql2);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeFriend(String id1,String id2){
		String sql = "delete from friend where userid = "+"'"+id1+"'"+" and friendid = "+"'"+id2+"'"+"";
		String sql2 = "delete from friend where userid = "+"'"+id2+"'"+" and friendid = "+"'"+id1+"'"+"";
		try {
			stmt.execute(sql);
			stmt.execute(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
  
		FriendDAO friendDAO = new FriendDAO();
		ArrayList<String> friendsArrayList = new ArrayList<>();
		friendsArrayList = friendDAO.getFriends("1");
		System.out.println(friendsArrayList);
		
	}
    
}   
;
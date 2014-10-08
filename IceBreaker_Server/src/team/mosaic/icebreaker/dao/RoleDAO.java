package team.mosaic.icebreaker.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import team.mosaic.icebreaker.entity.JDBC_Connection;

public class RoleDAO {
	
	private	JDBC_Connection jdbc_Connection = JDBC_Connection.getInstance();
	private Statement stmt = jdbc_Connection.getStatement();
	
	
	public void creat(String id){
	
		String sql = "insert into role (id,Anna,Kristoff,Sven,Elsa) values ("+"'"+id+"'"+",'1','0','0','0')";
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public boolean ismyrole(String id,String role){
		boolean b = false;
		String sql = "select "+role+" from role where id = "+"'"+id+"'"+"";
		ResultSet rs;
		int i = 0;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				i = rs.getInt(role);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (i == 1) {
			b = true;
		}
		return b;
	}
	
	public void getmyrole(String id,String role){
		
		String sql = "update role set "+role+" = '1' where id = "+"'"+id+"'"+"";
		try {
			stmt.execute(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	public static void main(String[] args){
//		RoleDAO roleDAO = new RoleDAO();
//		roleDAO.getmyrole("2", "r3");
//		System.err.println(roleDAO.ismyrole("2", "r2"));
//		roleDAO.creat("1");
//	}

}

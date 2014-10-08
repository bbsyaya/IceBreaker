package team.mosaic.icebreaker.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import team.mosaic.icebreaker.entity.JDBC_Connection;

public class InfoDAO {
	private	JDBC_Connection jdbc_Connection = JDBC_Connection.getInstance();
	private Statement stmt = jdbc_Connection.getStatement();
	
	public void creat(String id){
		
		String sql = "insert into info (id,level,exp,coin,score) values ("+"'"+id+"'"+",'1','0','0','0')";
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	
	public int getcontent(String id,String item){
		int level = 1;
		ResultSet rs;
		String sql = "select "+item+" from info where id = "+"'"+id+"'"+"";
		try {
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				level= rs.getInt(item);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return level;
	}
		
	public void setcontent(String id,String item,int num){

		String sql = "update info set "+item+" = "+num+" where id = "+"'"+id+"'"+"";
		try {
			stmt.execute(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

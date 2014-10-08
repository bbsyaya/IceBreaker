package team.mosaic.icebreaker.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import team.mosaic.icebreaker.entity.JDBC_Connection;

public class AccountDAO {


    
    private	JDBC_Connection jdbc_Connection = JDBC_Connection.getInstance();
	private Statement stmt = jdbc_Connection.getStatement();
   
    
    public String getpassword(String id) {
    	
    	String sql_select = "select pwd from account where id = "+"'"+id+"'"+"";
		ResultSet rs;
		String line = "null";
		
		try {
			rs = stmt.executeQuery(sql_select);
			
			while (rs.next()) {
			 	line = rs.getString("pwd");			
			 	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
    }
    
    public boolean addID(String id,String password){
    	boolean b = false;
    	if (id.length()<=12 && password.length()<=18) {
			String sql = "insert into account (id,pwd) values ("+"'"+id+"'"+","+"'"+password+"'"+")";
			try {
				stmt.execute(sql);
				b = true ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block			
				e.printStackTrace();			
			}	
		}
    	return b;
    }
    
    public ArrayList<String> getAllAccount(){
    	String sql = "select id from account ";
    	ResultSet rs;
    	ArrayList<String> idArrayList = new ArrayList<>();
    	try {
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				idArrayList.add(rs.getString("id")) ;	
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return idArrayList;
    }
    
}

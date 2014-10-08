package team.mosaic.icebreaker.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import team.mosaic.icebreaker.entity.JDBC_Connection;

public class RecordDAO {

    private	JDBC_Connection jdbc_Connection = JDBC_Connection.getInstance();
	private Statement stmt = jdbc_Connection.getStatement();
   
    public void initialization(String id,String time,int conti,int score){
    	String sql = "insert into record (id,time,conti,score) values ("+"'"+id+"'"+","+"'"+time+"'"+","+conti+","+score+")";
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public ArrayList<String> getRecord(String id){  
    	ArrayList<String> records = new ArrayList<String>();
    	String record = new String();
    	String sql = "select * from record where id = "+"'"+id+"'"+" and (curdate() - date(time))<7;";
    	ResultSet rs = null;
		try {
	
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				record = rs.getString("id")+"\t"+rs.getString("time")+"\t"+rs.getInt("conti")+"\t"+rs.getInt("score");			
				records.add(record);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return records;
	
    }
    
//    public static void main(String[] args){
//    	RecordDAO recordDAO = new RecordDAO();
//    	recordDAO.initialization("2", "23423432", 2, 2);
//    	recordDAO.initialization("2", "234232", 1, 1);
//    	recordDAO.initialization("2", "2432", 3, 3);
//    	String s = recordDAO.getRecord("2");
//    	System.out.println(s);
//    }
}
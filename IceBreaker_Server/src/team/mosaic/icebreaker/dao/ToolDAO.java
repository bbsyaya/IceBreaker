package team.mosaic.icebreaker.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import team.mosaic.icebreaker.entity.JDBC_Connection;

public class ToolDAO {
	
	private	JDBC_Connection jdbc_Connection = JDBC_Connection.getInstance();
	private Statement stmt = jdbc_Connection.getStatement();
	
	
	public void creat(String id){
		
		String sql = "insert into tool (id,C,D,E,F) values ("+"'"+id+"'"+",'0','0','0','0')";
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void buytool(String id,String tool,int count){
		String sql1 = "select "+tool+" from tool where id = "+"'"+id+"'"+"";
		ResultSet rs;
		int number = 0;
		try {
			rs = stmt.executeQuery(sql1);
			while (rs.next()) {
			     number = rs.getInt(tool);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		number = number+count;
		String sql = "update tool set "+tool+" = "+number+" where id = "+"'"+id+"'"+"";
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void usetool(String id,String tool){
		String sql1 = "select "+tool+" from tool where id = "+"'"+id+"'"+"";
		ResultSet rs;
		int number = 0;
		try {
			rs = stmt.executeQuery(sql1);
			while (rs.next()) {
			     number = rs.getInt(tool);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		number = number-1;
		String sql = "update tool set "+tool+" = "+number+" where id = "+"'"+id+"'"+"";
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int toolnumber(String id,String tool){
		String sql1 = "select "+tool+" from tool where id = "+"'"+id+"'"+"";
		ResultSet rs;
		int number = 0;
		try {
			rs = stmt.executeQuery(sql1);
			while (rs.next()) {
			     number = rs.getInt(tool);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return number;
	}
//	public static void main(String[] args){
//		
//		ToolDAO toolDAO = new ToolDAO();
//		toolDAO.buytool("1", "t4", 5);
//		toolDAO.usetool("1", "t4");
//		System.out.println(toolDAO.toolnumber("1","t4"));
//		toolDAO.creat("2");
//	}

}

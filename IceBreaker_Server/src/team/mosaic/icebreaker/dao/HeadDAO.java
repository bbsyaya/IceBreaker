package team.mosaic.icebreaker.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import team.mosaic.icebreaker.entity.JDBC_Connection;

public class HeadDAO {
	
	private	JDBC_Connection jdbc_Connection = JDBC_Connection.getInstance();
	private Statement stmt = jdbc_Connection.getStatement();
    
	
	/*
	 * ����ͷ��
	 * @param  id �˺�  head_path  ͷ��ľ���·��
	 */
	public void sethead(String id,String head_path){    //д��StringҪ�ӵ�����
		String sql = "insert into head (id,headpath) values ("+"'"+id+"'"+","+"'"+head_path+"'"+")";
		
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * �õ�ͷ��
	 * @param   id
	 * @return  string   ͷ��ľ���·��
	 */
	public String gethead(String id){
		String headpath = null;
		String sql = "select headpath from head where id = "+"'"+id+"'"+"";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				headpath = rs.getString("headpath");			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return headpath;
	}
	
//	public static void main(String[] args){
//		HeadDAO headDAO = new HeadDAO();
//		headDAO.sethead("gadf", "E:/HuY_Space/SelfPractise/photofile/photo/1.jpg");
//		System.out.println(headDAO.gethead("gadf"));
//
//	}
}

package team.mosaic.icebreaker.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBC_Connection {
	
	private String url = "jdbc:mysql://localhost:3306/icebreaker_data?useUnicode=true&characterEncoding=gbk";
	private String username = "root";
	private String password = "000000";
	private Connection con;
	private static Statement stmt;
	private static boolean isConnected = false;
	private static JDBC_Connection jc = new JDBC_Connection(); // singleton


	/*
	 * ���캯����˽��
	 */
	private JDBC_Connection() {
		connect();		
	}
	
	/**
	 * �������ݿ�ķ���
	 */
	public void connect() {
		//
		boolean bo1 = false;
		boolean bo2 = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("�������سɹ�");
			bo1 = true;
		} catch (ClassNotFoundException e) {
			System.out.println("��������ʧ��");
			e.printStackTrace();
		}
		//
		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			System.out.println("�������ݿ�ɹ�");
			bo2 = true;
		} catch (SQLException e) {
			System.out.println("�������ݿ�ʧ��");
		}
		if (bo1 && bo2)
			isConnected = true;
	}

	/**
	 * �Ͽ��������ݿ�ķ���
	 */
	public void disconnect() {
		try {
			stmt.close();
			con.close();
			System.out.println("�ɹ��Ͽ�����");
			isConnected = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�Ͽ�����ʧ��");
		}
	}
		
	/*
	 * @return jc   jdbc����ĵ���
	 */
	public static JDBC_Connection getInstance(){
		return jc;		
	}
	
	public Statement getStatement(){
		return stmt;
	}
	/*
	 * @return stmt Statement����
	 */

	/**
	 * �õ��Ƿ����ӵĽ��
	 */
	public static boolean isConnected() {
		return isConnected;
	}
}

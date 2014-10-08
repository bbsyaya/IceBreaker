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
	 * 构造函数，私有
	 */
	private JDBC_Connection() {
		connect();		
	}
	
	/**
	 * 连接数据库的方法
	 */
	public void connect() {
		//
		boolean bo1 = false;
		boolean bo2 = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("驱动加载成功");
			bo1 = true;
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败");
			e.printStackTrace();
		}
		//
		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			System.out.println("链接数据库成功");
			bo2 = true;
		} catch (SQLException e) {
			System.out.println("链接数据库失败");
		}
		if (bo1 && bo2)
			isConnected = true;
	}

	/**
	 * 断开连接数据库的方法
	 */
	public void disconnect() {
		try {
			stmt.close();
			con.close();
			System.out.println("成功断开连接");
			isConnected = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("断开连接失败");
		}
	}
		
	/*
	 * @return jc   jdbc对象的单体
	 */
	public static JDBC_Connection getInstance(){
		return jc;		
	}
	
	public Statement getStatement(){
		return stmt;
	}
	/*
	 * @return stmt Statement对象
	 */

	/**
	 * 得到是否连接的结果
	 */
	public static boolean isConnected() {
		return isConnected;
	}
}

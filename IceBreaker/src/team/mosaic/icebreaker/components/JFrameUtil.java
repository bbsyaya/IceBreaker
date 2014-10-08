package team.mosaic.icebreaker.components;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class JFrameUtil {
	JFrame frame= null;
	
	public JFrameUtil(JFrame frame) {
		super();
		this.frame = frame;
	}
	
	
	/**
	 * 得到屏幕的宽
	 */
	
	public static int getScreenWidth()
	{
		//Dimension 类封装单个对象中组件的宽度和高度（精确到整数）。
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int screenwidth=(int)d.getWidth();
		
		return screenwidth;
	}
	
	
	/**
	 * 得到屏幕的中心点
	 */
	public static  Point getScreenCenterPoint(JFrame frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) dimension.getWidth();
		int screenHeigth = (int) dimension.getHeight();
		int x = (screenWidth - frame.getWidth()) / 2;
		int y = (screenHeigth - frame.getHeight()) / 2;
		return new Point(x, y);
	}
}

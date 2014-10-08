package team.mosaic.icebreaker.utility;

import java.awt.Cursor;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import team.mosaic.icebreaker.components.LoadingGlassPane;
import team.mosaic.icebreaker.view.game.TimePanel;

public class Loading {

	public static final LoadingGlassPane LOADING_GLASS_PANE = new LoadingGlassPane();
	public static final LoadingGlassPane Ready_GLASS_PANE = new LoadingGlassPane();
	public static  ImageIcon[] count=new ImageIcon[4];

	
	public static void addLoading(){
				LOADING_GLASS_PANE.setVisible(true);
//				LOADING_GLASS_PANE.setCursor(new Cursor(Cursor.WAIT_CURSOR));
	}
	public static void addReady(){
//		Ready_GLASS_PANE.setVisible(true);
//		Ready_GLASS_PANE.setCursor(new Cursor(Cursor.WAIT_CURSOR));
//		
		for(int i=0;i<=3;i++){
				ImageIcon icon=new ImageIcon("image/countdown/"+i+".png");
				count[i]=icon;
			}
		
		Thread thread=new Thread(new Runnable() {
				public void run(){
					
			Calendar startCalendar = Calendar.getInstance();
			long startTime = startCalendar.getTime().getTime(); // 获得开始时候的那个时间点
			long endTime = startTime + 1000*5; // 从开始时刻开始 加4秒
			long nowTime, leftTime;
			float leftSec;
			Calendar now;
	
			Ready_GLASS_PANE.setVisible(true);
			Ready_GLASS_PANE.setLabel(count[3]);

			//Ready_GLASS_PANE.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			while (true) {
				now = Calendar.getInstance();
				nowTime = now.getTime().getTime();
				leftTime = endTime - nowTime;
				leftSec = Math.round(leftTime / 1000);
		
//				System.out.println(leftSec);

				if(leftSec>=5)
					continue;
				if (leftSec == 0) {
//					TimePanel.clocker.start();
					removeReady();
					return;
				}
				Ready_GLASS_PANE.setLabel(count[(int)leftSec-1]);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				}
			
		});
		thread.start();
	}
	
	public static void regainLoading(){
		LOADING_GLASS_PANE.normalize();
		removeLoading();
	}
	
	public static void removeLoading(){
		LOADING_GLASS_PANE.setVisible(false);
//		LOADING_GLASS_PANE.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public static void removeReady(){
		Ready_GLASS_PANE.setVisible(false);
		Ready_GLASS_PANE.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
}

package team.mosaic.icebreaker.view.game;

import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TimePanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8829692805224398456L;
	private GamePanel parentPanel;
	private JLabel counter;
//	public static Thread clocker;
	private static ImageIcon[] times=new ImageIcon[61];

	public TimePanel(GamePanel gp) {
		parentPanel = gp;
		counter=new JLabel();
		for(int i=0;i<=60;i++){
			ImageIcon icon=new ImageIcon("image/countdown/"+i+".png");
			times[i]=icon;
		}
//		clocker = new Thread(this);
		//start 时间点问题
		//clocker.start();

		init();
	}

	public void init() {
		
		this.add(counter);
		counter.setBounds(0, 0, 200, 200);
		counter.setIcon(times[60]);
		this.setBounds(40, 100, 200, 210);
		this.setOpaque(false);
		parentPanel.add(this);

	}
	/**
	 * 设置时间面板的时间
	 * @param time
	 */
	public void setTime(int time){
		this.counter.setIcon(times[time]);
	}

//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.drawImage(new ImageIcon("image/Login/background/background.png")
//				.getImage(), 0, 0, 380, 292, this);
//
//	}

	public void run() {
		Calendar startCalendar = Calendar.getInstance();
		long startTime = startCalendar.getTime().getTime(); // 获得开始时候的那个时间点
		long endTime = startTime + 1000*60; // 从开始时刻开始 加一分钟 
		long nowTime,leftSec;
		float leftTime;
		Calendar now;


		while (true) {
			now = Calendar.getInstance();
			nowTime = now.getTime().getTime();
			leftTime = endTime - nowTime;
			leftSec = Math.round(leftTime / 1000);
//			System.out.println(leftSec);
			//每一秒一张图

			if (leftSec == 0) {
				JOptionPane.showMessageDialog(this, "时间到！", "提示",
						JOptionPane.OK_OPTION);
				//禁止响应
				break;
			}
			counter.setIcon(times[(int)leftSec]);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void startCount(){
		
			
	}

}

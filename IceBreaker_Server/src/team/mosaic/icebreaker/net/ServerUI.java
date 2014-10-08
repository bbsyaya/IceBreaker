package team.mosaic.icebreaker.net;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class ServerUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1289080078605533626L;
	private JPanel panel;
	
	public ServerUI(){
		panel = new ServerPanel();
		this.add(panel);
		init();
	}

	private void init(){
		this.setTitle("IceBreaker_Server");
		this.setSize(400, 200);// 设置大小
		this.setResizable(false);// 设置不能变更大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭操作
		this.setLocationRelativeTo(getOwner());// 设置显示位置
	}
	
	private static void showUp(){
		ServerUI ui = new ServerUI();
		ui.setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		showUp();
	}

}

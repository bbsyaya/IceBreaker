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
		this.setSize(400, 200);// ���ô�С
		this.setResizable(false);// ���ò��ܱ����С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ùرղ���
		this.setLocationRelativeTo(getOwner());// ������ʾλ��
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

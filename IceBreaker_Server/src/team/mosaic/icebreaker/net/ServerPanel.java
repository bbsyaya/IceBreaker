package team.mosaic.icebreaker.net;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import team.mosaic.icebreaker.entity.JDBC_Connection;

public class ServerPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5144654087743989346L;
	private JButton startButton;
	private JButton shutButton;
//	private MyServerSocket mss;
	private JLabel label;
	private JLabel countLabel;
	
	public ServerPanel(){
		init();
	}
	
	private void init(){
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		Font font = new Font("microsoft yahei", Font.BOLD, 12);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);
		
		label = new JLabel("��ǰ����������");
		label.setBounds(30, 20, 100, 40);
		this.add(label);
		
		countLabel = new JLabel("0");
		countLabel.setBounds(120, 20, 120, 40);
		this.add(countLabel);
		
		startButton = new JButton("�� ��");
		startButton.setBounds(80, 100, 100, 40);
		startButton.addActionListener(this);
		this.add(startButton);
		
		shutButton = new JButton("�� ��");
		shutButton.setBounds(220, 100, 100, 40);
		shutButton.addActionListener(this);
		shutButton.setEnabled(false);
		this.add(shutButton);
	}

	public void updateCount(int count){
		countLabel.setText(count+"");
		countLabel.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == startButton){
			MyServerSocket mss = new MyServerSocket(this);
			if(!JDBC_Connection.isConnected() || !mss.getState()){
				JOptionPane.showMessageDialog(null, "����������ʧ�ܣ�");
			}
			else{
				startButton.setEnabled(false);
				shutButton.setEnabled(true);
			}
		}
		else if(arg0.getSource() == shutButton){
//			mss.shut();
			System.exit(0);
		}
	}
	
}

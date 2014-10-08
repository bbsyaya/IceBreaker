package team.mosaic.icebreaker.view.login;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import team.mosaic.icebreaker.controller.RegisterController;

/**
 * 注销界面
 * @author acer
 *
 */
public class RegisterPanel extends JPanel {

	private static final long serialVersionUID = -6728984226213637203L;

	private RegisterController controller;
	private LoginFrame pframe;

	public JLabel registerLabel;
	public JLabel idLabel;
	public JLabel pwdLabel;
	public JLabel pwdLabel2;
	public JTextField textField;
	public JPasswordField pwdField;
	public JPasswordField pwdField2;
	public JLabel registerButton;
	public JLabel backButton;

	public RegisterPanel(LoginFrame f) {
		pframe = f;
		controller = new RegisterController(this);
        this.setVisible(true);
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(0, 0, 380, 292);

//		registerLabel = new JLabel("注  册");
//		registerLabel.setFont(new Font("microsoft yahei", Font.BOLD, 13));
//		registerLabel.setBounds(60, 65, 80, 30);
//		this.add(registerLabel);
		
		idLabel = new JLabel("用户名：");
		idLabel.setFont(new Font("microsoft yahei", Font.BOLD, 14));
		idLabel.setBounds(70, 100, 60, 30);
		this.add(idLabel);
		
		textField = new JTextField();
		textField.setBounds(160, 100, 160, 28);
		this.add(textField);
		
		pwdLabel = new JLabel("密   码：");
		pwdLabel.setFont(new Font("microsoft yahei", Font.BOLD, 14));
		pwdLabel.setBounds(70, 140, 60, 30);
		this.add(pwdLabel);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(160, 140, 160, 28);
		this.add(pwdField);
		
		pwdLabel2 = new JLabel("确认密码：");
		pwdLabel2.setFont(new Font("microsoft yahei", Font.BOLD, 14));
		pwdLabel2.setBounds(70, 180, 70, 30);
		this.add(pwdLabel2);
		
		pwdField2 = new JPasswordField();
		pwdField2.setBounds(160, 180, 160, 28);
		this.add(pwdField2);
		
		backButton = new JLabel(new ImageIcon("pic/back_word.png"));
		backButton.setBounds(100, 230, 60, 23);
		backButton.addMouseListener(controller);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(backButton);
		
		registerButton = new JLabel(new ImageIcon("pic/register.png"));
		registerButton.setBounds(210, 230, 60, 23);
		registerButton.addMouseListener(controller);
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(registerButton);
	}

	public LoginFrame getParentFrame() {
		return pframe;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(
				new ImageIcon("image/Login/background/background.png").getImage(),
				0, 0, 380, 292, this);
		//System.out.print("1");

	}


}

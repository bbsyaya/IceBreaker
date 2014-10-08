package team.mosaic.icebreaker.view.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;




import team.mosaic.icebreaker.components.JButtonUtil;
import team.mosaic.icebreaker.controller.LoginController;
import team.mosaic.icebreaker.file.FileIO;
import team.mosaic.icebreaker.file.Portrait;
import team.mosaic.icebreaker.model.OnlineStatus;
import team.mosaic.icebreaker.model.UserModel;
import team.mosaic.icebreaker.modelservice.UserModelService;
import team.mosaic.icebreaker.sound.BGMusic;
import team.mosaic.icebreaker.utility.Fade;
import team.mosaic.icebreaker.utility.Loading;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.view.main.MainFrame;
import team.mosaic.icebreaker.viewservice.LoginViewService;
import team.mosaic.icebreaker.vo.AccountVO;

public class LoginPanel extends JPanel implements LoginViewService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5184951079468612617L;

	public LoginController controller;
	private LoginFrame pframe;
	private UserModelService ums;
	
	private JLabel lblLogin = null; // 登录文字标签
	public JButton btnLogin = null;// 登录按钮
	public JLabel  offLabel=null;//离线文字标签
	public JButton btnOffline=null;//离线游戏按钮
//	public ErrorTipPane errorTipPane = null;//登录失败错误提示面板
	
	

	private JLabel lblLoginHeadbkg = null; // 登录头像背景
	private JLabel lblLoginHeadimg = null; // 登录头像
	
	public JComboBox<String> jtfAccount;//用户名框
	public JPasswordField pwdfPassword = null;// 密码输入框
	public JButton btnRegister = null; // 注册按钮
//	private JButton btnFindBackPwd = null;// 找回密码按钮
//	private JCheckBox chkAutoLogin = null;// 自动登录复选框
	public JCheckBox chkRememberPwd = null;// 记住密码复选框
	private JLabel lblRememberPwd = null;// 记住密码标签
//	private JLabel lblAutoLogin = null;// 自动登录标签
	Font font = new Font("微软雅黑", Font.PLAIN, 12);


	public LoginPanel(LoginFrame f) {
		pframe = f;
		controller = new LoginController(this);
		ums = new UserModel(this);
		
		initComponent();
		addComponent();
		showLocalAccount();
		this.setVisible(true);

	}

	public UserModelService getUserModel(){
		return this.ums;
	}

	public void initComponent(){
				// 主面板				
				// 登陆头像及背景
				lblLoginHeadimg = new JLabel(new ImageIcon("pic/Default.jpg"));
				lblLoginHeadbkg = new JLabel(new ImageIcon(
						"image/Login/login_head_white.png"));
				// 用户名文本框
				jtfAccount = new JComboBox<>();
				jtfAccount.setFont(new Font("Verdana", Font.PLAIN, 12));				
				jtfAccount.setEditable(true);
				jtfAccount.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						//在JComboBox的监听事件时总是执行两次,原因如下:
				        //ItemListener类中的方法itemStateChanged()事件的itemState有关，itemState在这里的状态有两个，Selected 和 deSelected（即选中和未被选中）
				        //所以，当改变下拉列表中被选中的项的时候，其实是触发了两次事件：
				        //第一次是上次被选中的项的 State 由 Selected 变为 deSelected ，即取消选择
				        //第二次是本次被选中的项的 State 由 deSelected 变为 Selected ，即新选中，所以，必然的 ItemStateChanged 事件中的代码要被执行两次了。
				        //加上最外面的if语句，就可以解决这个问题。
				        if(e.getStateChange() == ItemEvent.SELECTED) {
				        	int index=jtfAccount.getSelectedIndex();
				        	//选中已有账号
				        	if(index!=-1){
				        		lblLoginHeadimg.setIcon(Portrait.getHeadPortrait(ums.getHpList().get(index)));
				        		OnlineStatus.setCharacter(ums.getHpList().get(index));
				        		pwdfPassword.setText(ums.getPwdList().get(index));
				        		chkRememberPwd.setSelected(!ums.getPwdList().get(index).equals(""));
				        	}
				        	//自行输入
				        	else{
				        		pwdfPassword.setText("");
				        		lblLoginHeadimg.setIcon(Portrait.getDefault());
				        		OnlineStatus.setCharacter(-1);
				        	}
				        	
				        	//切换头像
				        }
				        
				        
					}
				}
				);



				// 密码输入框
				pwdfPassword = new JPasswordField("");
				pwdfPassword.setFont(font);
				pwdfPassword.setEchoChar('●');
				pwdfPassword.addKeyListener(controller);
				
				// 自动登陆复选框
//				chkAutoLogin = JButtonUtil.getIconCheckBox(
//						"image/Login/checkbox_normal.png",
//						"image/Login/checkbox_hover.png",
//						"image/Login/checkbox_press.png",
//						"image/Login/checkbox_selected_hover.png");
				//chkAutoLogin.add
				
				// 记住密码复选框
				chkRememberPwd = JButtonUtil.getIconCheckBox(
						"image/Login/checkbox_normal.png",
						"image/Login/checkbox_hover.png",
						"image/Login/checkbox_press.png",
						"image/Login/checkbox_selected_hover.png");
				chkRememberPwd.addActionListener(controller);
				
				
				// 自动登陆标签
//				lblAutoLogin = new JLabel("自动登录");
//				lblAutoLogin.setFont(font);
//				lblAutoLogin.setForeground(Color.black);
				 //记住密码标签
				lblRememberPwd = new JLabel("记住密码");
				lblRememberPwd.setFont(font);
				lblRememberPwd.setForeground(Color.black);
//				
				this.initButton();

		
		
	}
	
	
	public void addComponent(){
		this.setLayout(null);

		
//		this.add(mBar);
//		mBar.setBounds(85, 205, 32, 22);
		
		this.add(lblLoginHeadimg);
		lblLoginHeadimg.setBounds(23, 136, 80, 79);
		this.add(lblLoginHeadbkg);
		lblLoginHeadbkg.setBounds(21, 133, 84, 84);

		this.add(jtfAccount);
		jtfAccount.setBounds(113, 135, 185, 26);

		this.add(pwdfPassword);
		pwdfPassword.setBounds(113, 168, 185, 26);
		this.add(btnRegister);
		btnRegister.setBounds(310, 140, 51, 16);
//		this.add(btnFindBackPwd);
//		btnFindBackPwd.setBounds(310, 183, 51, 16);

//		this.add(chkAutoLogin);
		chkRememberPwd.setBounds(170, 201, 15, 15);
		this.add(lblRememberPwd);
		lblRememberPwd.setBounds(186, 201, 48, 15);
		this.add(chkRememberPwd);
//		chkAutoLogin.setBounds(189, 211, 15, 15);
//		this.add(lblAutoLogin);
//		lblAutoLogin.setBounds(206, 211, 48, 15);

		this.add(lblLogin);
		lblLogin.setBounds(135, 242, 50, 12);
		this.add(btnLogin);
		btnLogin.setBounds(102, 236, 100, 24);
		
		this.add(offLabel);
		offLabel.setBounds(224, 242, 50, 12);
		this.add(btnOffline);
		btnOffline.setBounds(198, 236, 100, 24);
		
		
		
	}
	
	
	public void initButton(){
			
			// 注册按钮
			btnRegister = JButtonUtil.getIconButton("image/Login/zhuce_normal.png",
					"image/Login/zhuce_press.png", "image/Login/zhuce_hover.png");
			
			btnRegister.addActionListener(controller);
			// 密码找回按钮
//			btnFindBackPwd = JButtonUtil.getIconButton(
//					"image/Login/mima_normal.png", "image/Login/mima_press.png",
//					"image/Login/mima_hover.png");
			
			
			
			lblLogin = new JLabel("登  录");
			lblLogin.setFont(font);
			lblLogin.setForeground(Color.BLACK);
			// 登录按钮
			btnLogin = JButtonUtil.getIconButton(
					"image/Login/button_blue_normal.png",
					"image/Login/button_blue_press.png",
					"image/Login/button_blue_hover.png");
			btnLogin.addActionListener(controller);
			
			
			offLabel = new JLabel("离线游戏");
			offLabel.setFont(font);
			offLabel.setForeground(Color.BLACK);
			// 离线游戏按钮
			btnOffline = JButtonUtil.getIconButton(
					"image/Login/button_blue_normal.png",
					"image/Login/button_blue_press.png",
					"image/Login/button_blue_hover.png");
			btnOffline.addActionListener(controller);
				}
	
	
	
	

//
//	/**
//	 * 登录超时提示面板
//	 * @author Jocelyn
//	 *
//	 */
//	public class ErrorTipPane extends JPanel{
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//		public JLabel lblTip = null;
//		private JButton btnClo = null;
//		private JButton btnTip = null;
//		
//		public ErrorTipPane() {
//			this.setSize(380, 51);
//			init();
//			this.setLayout(null);
//			this.add(lblTip);
//			lblTip.setBounds(80, 15, 300, 20);
//			this.add(btnTip);
//			btnTip.setBounds(45, 18, 16, 16);
//			this.add(btnClo);
//			btnClo.setBounds(358, 6, 12, 12);
//		}
//
//		private void init() {
//			lblTip = new JLabel("对不起，此账号已登录，请不要重复登录。");
//			lblTip.setFont(font);
//			lblTip.setForeground(Color.BLACK);
//			
//			btnTip = JButtonUtil.getIconButton("image/Login/Tip/i.png");
//			btnClo = JButtonUtil.getIconButton("image/Login/Tip/close.png",
//					"image/Login/Tip/close_press.png",
//					"image/Login/Tip/close_hover.png");
//			btnClo.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					LoginPanel.this.remove(errorTipPane);
//					LoginPanel.this.add(downPane);
//					downPane.setBounds(0, 241, 380, 51);
//					LoginPanel.this.updateUI();
//					LoginPanel.this.validate();
//				}
//			});
//			
//		}
//	}

	public LoginFrame getParentFrame() {
		return pframe;
	}

	
	private void showLocalAccount(){
		ArrayList<String> idList = ums.getIdList();
		for(String l:idList){
			this.jtfAccount.addItem(l);
		}
		lblLoginHeadimg.setIcon(Portrait.getHeadPortrait(ums.getHpList().get(idList.size()-1)));
		jtfAccount.setSelectedIndex(idList.size()-1);
		pwdfPassword.setText(ums.getPwdList().get(idList.size()-1));
		chkRememberPwd.setSelected(!ums.getPwdList().get(idList.size()-1).equals(""));
	}
	
		/**
	 * 进入游戏
	 * @param online 在线与否
	 */
	public void enter(boolean online){
		MainFrame.showUp(online);
		Fade.fadeOut(pframe);
//		BGMusic.play(BGMusic.BGM1);
//		pframe.dispose();
	}

	@Override
	public void dealWithLogin(boolean isVerified) {
		// TODO Auto-generated method stub
		if (isVerified) {
//			writeAccount();
			enter(true);
			
		} else {
//			Prompt.showWarning(pframe, "用户名或密码错误！");
			JOptionPane.showMessageDialog(pframe, "用户名或密码错误！");
			Loading.removeLoading();
		}
	}	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(
				new ImageIcon("image/Login/background/background.png").getImage(),
				0, 0, 380, 292, this);

	}

}


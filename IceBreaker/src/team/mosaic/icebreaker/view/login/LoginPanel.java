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
	
	private JLabel lblLogin = null; // ��¼���ֱ�ǩ
	public JButton btnLogin = null;// ��¼��ť
	public JLabel  offLabel=null;//�������ֱ�ǩ
	public JButton btnOffline=null;//������Ϸ��ť
//	public ErrorTipPane errorTipPane = null;//��¼ʧ�ܴ�����ʾ���
	
	

	private JLabel lblLoginHeadbkg = null; // ��¼ͷ�񱳾�
	private JLabel lblLoginHeadimg = null; // ��¼ͷ��
	
	public JComboBox<String> jtfAccount;//�û�����
	public JPasswordField pwdfPassword = null;// ���������
	public JButton btnRegister = null; // ע�ᰴť
//	private JButton btnFindBackPwd = null;// �һ����밴ť
//	private JCheckBox chkAutoLogin = null;// �Զ���¼��ѡ��
	public JCheckBox chkRememberPwd = null;// ��ס���븴ѡ��
	private JLabel lblRememberPwd = null;// ��ס�����ǩ
//	private JLabel lblAutoLogin = null;// �Զ���¼��ǩ
	Font font = new Font("΢���ź�", Font.PLAIN, 12);


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
				// �����				
				// ��½ͷ�񼰱���
				lblLoginHeadimg = new JLabel(new ImageIcon("pic/Default.jpg"));
				lblLoginHeadbkg = new JLabel(new ImageIcon(
						"image/Login/login_head_white.png"));
				// �û����ı���
				jtfAccount = new JComboBox<>();
				jtfAccount.setFont(new Font("Verdana", Font.PLAIN, 12));				
				jtfAccount.setEditable(true);
				jtfAccount.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						//��JComboBox�ļ����¼�ʱ����ִ������,ԭ������:
				        //ItemListener���еķ���itemStateChanged()�¼���itemState�йأ�itemState�������״̬��������Selected �� deSelected����ѡ�к�δ��ѡ�У�
				        //���ԣ����ı������б��б�ѡ�е����ʱ����ʵ�Ǵ����������¼���
				        //��һ�����ϴα�ѡ�е���� State �� Selected ��Ϊ deSelected ����ȡ��ѡ��
				        //�ڶ����Ǳ��α�ѡ�е���� State �� deSelected ��Ϊ Selected ������ѡ�У����ԣ���Ȼ�� ItemStateChanged �¼��еĴ���Ҫ��ִ�������ˡ�
				        //�����������if��䣬�Ϳ��Խ��������⡣
				        if(e.getStateChange() == ItemEvent.SELECTED) {
				        	int index=jtfAccount.getSelectedIndex();
				        	//ѡ�������˺�
				        	if(index!=-1){
				        		lblLoginHeadimg.setIcon(Portrait.getHeadPortrait(ums.getHpList().get(index)));
				        		OnlineStatus.setCharacter(ums.getHpList().get(index));
				        		pwdfPassword.setText(ums.getPwdList().get(index));
				        		chkRememberPwd.setSelected(!ums.getPwdList().get(index).equals(""));
				        	}
				        	//��������
				        	else{
				        		pwdfPassword.setText("");
				        		lblLoginHeadimg.setIcon(Portrait.getDefault());
				        		OnlineStatus.setCharacter(-1);
				        	}
				        	
				        	//�л�ͷ��
				        }
				        
				        
					}
				}
				);



				// ���������
				pwdfPassword = new JPasswordField("");
				pwdfPassword.setFont(font);
				pwdfPassword.setEchoChar('��');
				pwdfPassword.addKeyListener(controller);
				
				// �Զ���½��ѡ��
//				chkAutoLogin = JButtonUtil.getIconCheckBox(
//						"image/Login/checkbox_normal.png",
//						"image/Login/checkbox_hover.png",
//						"image/Login/checkbox_press.png",
//						"image/Login/checkbox_selected_hover.png");
				//chkAutoLogin.add
				
				// ��ס���븴ѡ��
				chkRememberPwd = JButtonUtil.getIconCheckBox(
						"image/Login/checkbox_normal.png",
						"image/Login/checkbox_hover.png",
						"image/Login/checkbox_press.png",
						"image/Login/checkbox_selected_hover.png");
				chkRememberPwd.addActionListener(controller);
				
				
				// �Զ���½��ǩ
//				lblAutoLogin = new JLabel("�Զ���¼");
//				lblAutoLogin.setFont(font);
//				lblAutoLogin.setForeground(Color.black);
				 //��ס�����ǩ
				lblRememberPwd = new JLabel("��ס����");
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
			
			// ע�ᰴť
			btnRegister = JButtonUtil.getIconButton("image/Login/zhuce_normal.png",
					"image/Login/zhuce_press.png", "image/Login/zhuce_hover.png");
			
			btnRegister.addActionListener(controller);
			// �����һذ�ť
//			btnFindBackPwd = JButtonUtil.getIconButton(
//					"image/Login/mima_normal.png", "image/Login/mima_press.png",
//					"image/Login/mima_hover.png");
			
			
			
			lblLogin = new JLabel("��  ¼");
			lblLogin.setFont(font);
			lblLogin.setForeground(Color.BLACK);
			// ��¼��ť
			btnLogin = JButtonUtil.getIconButton(
					"image/Login/button_blue_normal.png",
					"image/Login/button_blue_press.png",
					"image/Login/button_blue_hover.png");
			btnLogin.addActionListener(controller);
			
			
			offLabel = new JLabel("������Ϸ");
			offLabel.setFont(font);
			offLabel.setForeground(Color.BLACK);
			// ������Ϸ��ť
			btnOffline = JButtonUtil.getIconButton(
					"image/Login/button_blue_normal.png",
					"image/Login/button_blue_press.png",
					"image/Login/button_blue_hover.png");
			btnOffline.addActionListener(controller);
				}
	
	
	
	

//
//	/**
//	 * ��¼��ʱ��ʾ���
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
//			lblTip = new JLabel("�Բ��𣬴��˺��ѵ�¼���벻Ҫ�ظ���¼��");
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
	 * ������Ϸ
	 * @param online �������
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
//			Prompt.showWarning(pframe, "�û������������");
			JOptionPane.showMessageDialog(pframe, "�û������������");
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


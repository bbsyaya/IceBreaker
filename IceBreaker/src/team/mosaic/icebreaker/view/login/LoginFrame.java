package team.mosaic.icebreaker.view.login;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
//import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

//import org.jvnet.lafwidget.LafWidget;
//import org.jvnet.substance.SubstanceLookAndFeel;
//import org.jvnet.substance.skin.BusinessBlueSteelSkin;
//import org.jvnet.substance.skin.SubstanceSkin;
//import org.jvnet.substance.title.RandomCubesTitlePainter;
//import org.jvnet.substance.title.SubstanceTitlePainter;
//import org.jvnet.substance.watermark.SubstanceMosaicWatermark;
//import org.jvnet.substance.watermark.SubstanceWatermark;














import team.mosaic.icebreaker.components.JButtonUtil;
import team.mosaic.icebreaker.components.WindowCloseListener;
import team.mosaic.icebreaker.components.WindowMinListener;
import team.mosaic.icebreaker.file.FileIO;
import team.mosaic.icebreaker.sound.BGMusic;
import team.mosaic.icebreaker.utility.Fade;
import team.mosaic.icebreaker.utility.Loading;
import team.mosaic.icebreaker.view.main.MainFrame;

public class LoginFrame extends JFrame {

//	private static LookAndFeel theme = new SubstanceLookAndFeel();
//	private static SubstanceSkin skin = new BusinessBlueSteelSkin();
//	private static SubstanceTitlePainter title = new RandomCubesTitlePainter();
//	private static SubstanceWatermark watermark = new SubstanceMosaicWatermark();
	private static Font buttonFont = new Font("microsoft yahei", Font.BOLD, 12);

	private LoginPanel lp;
	private RegisterPanel rp;
	public JPanel currentPanel;
	
//	public SystemTray sysTray ; // 系统托盘
	public TrayIcon trayIcon ; // 托盘图标
	Font font = new Font("微软雅黑", Font.PLAIN, 12);
	PopupMenu pupup;


	/**
	 * 
	 */
	private static final long serialVersionUID = -2933279555217181415L;
	private JButton btnMin = null; // 最小化按钮
	private JButton btnClose = null; // 关闭按钮

	private int xOld = 0;
	private int yOld = 0;
	
	public LoginFrame() {
		this.setTitle("IceBreaker");
		this.setSize(380, 292);// 设置大小
		this.setResizable(false);// 设置不能变更大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭操作
		this.setLocationRelativeTo(getOwner());// 设置显示位置
		this.setGlassPane(Loading.LOADING_GLASS_PANE);
		this.setUndecorated(true);

		btnMin = JButtonUtil.getBtnMin();
		btnClose = JButtonUtil.getBtnClose();
		btnClose.setActionCommand("exit");
		btnMin = JButtonUtil.getBtnMin();
		this.add(btnClose);
		btnClose.setBounds(342, -2, 39, 20);
		this.add(btnMin);
		btnMin.setBounds(315, -2, 28, 20);
		// 给关闭按钮添加活动事件
		btnClose.addActionListener(new WindowCloseListener(this));
		// 给最小化按钮添加活动事件
	    btnMin.addActionListener(new WindowMinListener(this));

		initPupupMenu();
//		initSystemTray();
		
		lp = new LoginPanel(this);
		rp = new RegisterPanel(this);
		this.add(lp);
		currentPanel = lp;
		
	    /**
	     * 处理窗口拖动的监听
	     */
        this.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mousePressed(MouseEvent e) {  
                xOld = e.getX();  
                yOld = e.getY();  
            }  
        });  
        this.addMouseMotionListener(new MouseMotionAdapter() {  
            @Override  
            public void mouseDragged(MouseEvent e) {  
                int xOnScreen = e.getXOnScreen();  
                int yOnScreen = e.getYOnScreen();  
                int xx = xOnScreen - xOld;  
                int yy = yOnScreen - yOld;  
                LoginFrame.this.setLocation(xx, yy);
            }  
        });  
	}

	public LoginPanel getLoginPanel() {
		return lp;
	}

	public RegisterPanel getRegisterPanel() {
		return rp;
	}

	public void modifyPanel(JPanel p) {
		if (currentPanel != null) {
			this.remove(currentPanel);
			this.add(p);
			this.repaint();
			this.validate();
			p.repaint();
			currentPanel = p;
		}
	}
	
//	public void initSystemTray() {
//		sysTray = SystemTray.getSystemTray();
//		trayIcon = new TrayIcon(new ImageIcon(
//				"image/Login/status/offline16.png").getImage(), "QQ", pupup);
//		try {
//			if (SystemTray.isSupported() && sysTray != null) {
//				sysTray.add(trayIcon);
//			}
//		} catch (AWTException e1) {
//			e1.printStackTrace();
//		}
//		trayIcon.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				LoginFrame.this.setExtendedState(JFrame.NORMAL);
//				setVisible(true);
//			}
//		});
//	}
//	
	/**
	 * 初始化右键菜单
	 */
	private void initPupupMenu() {
		pupup = new PopupMenu();
		
		MenuItem itemExit = new MenuItem("退出");
		itemExit.setActionCommand("exit");
		itemExit.setFont(font);
		pupup.addSeparator();
		pupup.add(itemExit);

		itemExit.addActionListener(new WindowCloseListener(this));
	}

		
	
	public static void showUp(){
//		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			//UIManager.setLookAndFeel(theme);// 设置主题
			//SubstanceLookAndFeel.setSkin(skin);// 设置皮肤
			//SubstanceLookAndFeel.setCurrentTitlePainter(title);// 标题栏
			//SubstanceLookAndFeel.setCurrentWatermark(watermark);// 水印
//			UIManager.put( LafWidget.COMBO_BOX_NO_AUTOCOMPLETION, Boolean.TRUE );//解决combobox问题

			UIManager.put("Button.font", buttonFont);
		} catch (Exception e) {
			System.out.println("Substance Raven Graphite failed to initialize");
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LoginFrame w = new LoginFrame();
				w.setVisible(true);
				Fade.fadeIn(w);
			}
		});
	}

	
	public static ArrayList<String>  readAuto(){
		ArrayList<String> auto=FileIO.readAuto();
		if(auto.size()!=0)
			return auto;
		else 
			return null;
		
	}
	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//自动登录 本地账户验证若通过则直接到主界面，否则提示用户名错误
		
//		if(readAuto()!=null){
//			String s=readAuto().get(0);
//			String[] account=s.split(" ");
//			String user=account[0];
//			String pwd=LoginPanel.decode(account[1]);
//			new LoginFrame().lp.controller.autoSignIn(user, pwd);
//		}
//		else
			showUp();
	}

}

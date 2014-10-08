package team.mosaic.icebreaker.view.main;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

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
import team.mosaic.icebreaker.sound.BGMusic;
import team.mosaic.icebreaker.utility.Fade;
import team.mosaic.icebreaker.utility.Loading;
import team.mosaic.icebreaker.view.game.GamePanel;

/**
 * 父窗体
 * @author acer
 *
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -3076509752450088802L;
//	
//	private static LookAndFeel theme = new SubstanceLookAndFeel();
//	private static SubstanceSkin skin = new BusinessBlueSteelSkin();
//	private static SubstanceTitlePainter title = new RandomCubesTitlePainter();
//	private static SubstanceWatermark watermark = new SubstanceMosaicWatermark();
	private static Font buttonFont = new Font("microsoft yahei", Font.BOLD, 12);
	
	private JButton btnMin = null; // 最小化按钮
	private JButton btnClose = null; // 关闭按钮
	
	private  BgPanel bp;
	private  GamePanel gp;
	private  JPanel currentPanel;
	public  static boolean onLine;
	
	private int xOld = 0;
	private int yOld = 0;
	
	public MainFrame() {
		this.setTitle("IceBreaker");
		this.setSize(960, 600);// 设置大小
		this.setResizable(false);// 设置不能变更大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭操作
		this.setLocationRelativeTo(getOwner());// 设置显示位置
		this.setGlassPane(Loading.LOADING_GLASS_PANE);
//		this.setGlassPane(Loading.Ready_GLASS_PANE);

		this.setUndecorated(true);

		
		btnMin = JButtonUtil.getBtnMin();
		btnClose = JButtonUtil.getBtnClose();
		btnClose.setActionCommand("exit");
		btnMin = JButtonUtil.getBtnMin();
		this.add(btnClose);
		btnClose.setBounds(921, -2, 39, 20);
		this.add(btnMin);
		btnMin.setBounds(893, -2, 28, 20);
		// 给关闭按钮添加活动事件
		btnClose.addActionListener(new WindowCloseListener(this));
		// 给最小化按钮添加活动事件
	    btnMin.addActionListener(new WindowMinListener(this));
	    
	    
		
		gp = new GamePanel(this);
		bp = new BgPanel(this,onLine);
		this.add(bp);
		currentPanel = bp;
		
		BGMusic.play(BGMusic.BGM1);
		
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
                MainFrame.this.setLocation(xx, yy);
            }  
        });  
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
	
	public BgPanel getBgPanel(){
		return bp;
	}
	
	public GamePanel getGamePanel(){
		return gp;
	}
	
	public static void showUp(boolean ol){
		onLine = ol;
		//JFrame.setDefaultLookAndFeelDecorated(true);
		try {
//			UIManager.setLookAndFeel(theme);// 设置主题
//			SubstanceLookAndFeel.setSkin(skin);// 设置皮肤
//			SubstanceLookAndFeel.setCurrentTitlePainter(title);// 标题栏
//			SubstanceLookAndFeel.setCurrentWatermark(watermark);// 水印
//			UIManager.put( LafWidget.COMBO_BOX_NO_AUTOCOMPLETION, Boolean.TRUE );//解决combobox问题

			
			UIManager.put("Button.font", buttonFont);
		} catch (Exception e) {
			System.out.println("Substance Raven Graphite failed to initialize");
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame w = new MainFrame();
				w.setVisible(true);
				Fade.fadeIn(w);
			}
		});
	}
	
	public void setGamePanel(GamePanel gp){
		this.gp=gp;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		showUp(false);
	}
	
}

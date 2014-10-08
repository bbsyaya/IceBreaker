package team.mosaic.icebreaker.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.utility.Fade;
import team.mosaic.icebreaker.utility.Number;
import team.mosaic.icebreaker.view.main.MainFrame;

public class GainDialog extends JDialog implements ComponentListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel scoreLabel;
	private JLabel coinLabel;
	private JLabel expLabel;
//	private JLabel breakLabel;
//	private JLabel levelUpLabel;
//	private JLabel winLabel;
	private Color bgColor = Color.WHITE;// 背景色
	private JButton button;
	private MainFrame pf;
	private int width;
	private int height;
	private int score;
	private int exp;
	private int coin;
	private boolean breakRecord;
	private boolean levelUp;
	private int win;// <0 输；=0 平；>0 赢
	private boolean isPK;
	private static String pre = "<html><font color='black'>";
	private static String post = "</font></html>";
	private static ImageIcon scoreImg = new ImageIcon("pic/defen.png");
	private static ImageIcon coinImg = new ImageIcon("pic/jinbi.png");
	private static ImageIcon expImg = new ImageIcon("pic/jingyan.png");
	private static ImageIcon winImg = new ImageIcon("pic/win.png");
	private static ImageIcon loseImg = new ImageIcon("pic/lose.png");
	private static ImageIcon dogfallImg = new ImageIcon("pic/dogfall.png");
	private static ImageIcon newRecordImg = new ImageIcon("pic/new_record.png");
	private static ImageIcon levelUpImg = new ImageIcon("pic/level_up.png");
	private static ImageIcon[] imgs = new ImageIcon[3];
	private JLabel commonLabel;
	private JLabel commonLabel2;
	private JLabel[] scoreLabels = new JLabel[5];
	private JLabel[] coinLabels = new JLabel[3];
	private JLabel[] expLabels = new JLabel[3];

	public GainDialog(MainFrame frameParent, boolean isPK, int score, int coin,
			int exp, boolean breakRecord, boolean levelUp, int win) {
		super(frameParent);

		this.pf = frameParent;
		this.isPK = isPK;
		this.score = score;
		this.coin = coin;
		this.exp = exp;
		this.breakRecord = breakRecord;
		this.levelUp = levelUp;
		this.win = win;
		this.button = new SimpleButton(ColorMap.getColor(ColorMap.BLUE), "确定",
				80, 25);
		this.width = 960;
		this.height = 220;
		
		setPanel();
		init();
		Fade.fadeIn(this);
		flash();
	}

	private void init() {
		this.add(panel);

		this.setSize(width, height);
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false);
		this.setUndecorated(true);
		this.setBackground(new Color(0,0,0,0));
		this.setVisible(true);
		if(pf !=null)
			pf.addComponentListener(this);
	}

	private void setPanel() {
		panel = new DialogPanel();
		this.panel.setLayout(null);
		this.panel.setBackground(bgColor);

		for (int i = 0; i < scoreLabels.length; i++) {
			scoreLabels[i] = new JLabel();
			scoreLabels[i].setBounds(505+i*Number.DIGIT_WIDTH, 30, Number.DIGIT_WIDTH, Number.DIGIT_HEIGHT);
			panel.add(scoreLabels[i]);
		}
		Number.setNumber(scoreLabels, score);
		
		for (int i = 0; i < coinLabels.length; i++) {
			coinLabels[i] = new JLabel();
			coinLabels[i].setBounds(505+i*Number.DIGIT_WIDTH, 90, Number.DIGIT_WIDTH, Number.DIGIT_HEIGHT);
			panel.add(coinLabels[i]);
		}
		Number.setNumber(coinLabels, coin);
		
		for (int i = 0; i < expLabels.length; i++) {
			expLabels[i] = new JLabel();
			expLabels[i].setBounds(505+i*Number.DIGIT_WIDTH, 150, Number.DIGIT_WIDTH, Number.DIGIT_HEIGHT);
			panel.add(expLabels[i]);
		}
		Number.setNumber(expLabels, exp);
		
		scoreLabel = new JLabel(scoreImg,JLabel.LEFT);
//		scoreLabel.setText(pre+score+post);
		scoreLabel.setFont(new Font("microsoft yahei", Font.BOLD, 20));
		scoreLabel.setSize(107, 60);
		scoreLabel.setLocation(400, 10);
		this.add(scoreLabel);

		coinLabel = new JLabel(coinImg,JLabel.LEFT);
//		coinLabel.setText(pre+coin+post);
		coinLabel.setFont(new Font("microsoft yahei", Font.BOLD, 20));
		coinLabel.setSize(107, 60);
		coinLabel.setLocation(400, 70);
		this.add(coinLabel);

		expLabel = new JLabel(expImg,JLabel.LEFT);
//		expLabel.setText(pre+exp+post);
		expLabel.setFont(new Font("microsoft yahei", Font.BOLD, 20));
		expLabel.setSize(107, 60);
		expLabel.setLocation(400, 130);
		this.add(expLabel);

		// 按钮
		button.setLocation((width - button.getWidth()) / 2,
				height - 32);
		this.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pf.modifyPanel(pf.getBgPanel());
				Fade.fadeOut(GainDialog.this);
			}
		});
	}

	private void flash(){
		commonLabel = new JLabel();
		commonLabel.setBounds(600, 30, 300, 127);
		panel.add(commonLabel);
		commonLabel2 = new JLabel();
		commonLabel2.setBounds(70, 30, 300, 127);
		panel.add(commonLabel2);
		int index = 0;
		imgs[0] = null;
		imgs[1] = null;
		imgs[2] = null;
		if(isPK){
			if(win > 0)
				imgs[index] = winImg;
			else if(win == 0)
				imgs[index] = dogfallImg;
			else {
				imgs[index] = loseImg;
			}
			index ++;
		}
		if(breakRecord){
			imgs[index] = newRecordImg;
			index ++;
		}
		if(levelUp){
			imgs[index] = levelUpImg;
			index ++;
		}
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			int i = 0;
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(imgs[0]!= null){
				while(imgs[i] == null){
					i = (i+1)%3;
				}
//				if(imgs[i] != null){
					commonLabel.setIcon(imgs[i]);
					commonLabel2.setIcon(imgs[i]);
					GainDialog.this.repaint();
					i = (i+1)%3;
//				}
				}
			}
		}, 0, 500);
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		this.setLocation(pf.getX(),
				pf.getY() + (pf.getHeight() - this.getHeight()) / 2);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}
}

package team.mosaic.icebreaker.view.main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import team.mosaic.icebreaker.controller.SetController;
import team.mosaic.icebreaker.file.FileIO;
import team.mosaic.icebreaker.file.SubImg;

/**
 * 设置界面
 * @author acer
 *
 */
public class SetPanel extends JPanel {

	private static final long serialVersionUID = -7115381047290844996L;

	private SetController controller;
	private BgPanel parentPanel;

	private JLabel setLabel;
	private JLabel musicLabel,soundLabel;
	private JLabel direction;
	private JLabel note1;

	public boolean sou,dir,mus;

	public JLabel backButton;
	
	public JCheckBox down,left,open1,close1,open2,close2 ;
	public JLabel switcher_1,switcher_2;
	private SubImg switcher = new SubImg(SubImg.SWITCH,98,61);
	private SubImg musical = new SubImg(SubImg.MUSICALNOTE,120,140);
	private ImageIcon open = new ImageIcon( switcher.getImage(0));
	private ImageIcon close = new ImageIcon( switcher.getImage(1));
	private ImageIcon back = new ImageIcon( SubImg.BACK);
	public ImageIcon icons[] = {close,open};
	public int icon_1 = 0;
	public int icon_2 = 0;

	public SetPanel(BgPanel p){
		parentPanel = p;
		getConfig();
		controller = new SetController(this);
		init();
	}

	private void init(){
		this.setLayout(null);
		this.setOpaque(false);
		this.setBounds(0, 0, 960, 600);

		setLabel = new JLabel(new ImageIcon("pic/game_set.png"));
		setLabel.setBounds(355, 30, 250, 83);
		this.add(setLabel);
		
		musicLabel = new JLabel("音乐开关");
		musicLabel.setFont(new Font("microsoft yahei", Font.BOLD, 20));
		musicLabel.setBounds(340, 190, 100, 30);
		this.add(musicLabel);
		
		icon_1 = mus?1:0;
		switcher_1 = new JLabel();
		switcher_1.setIcon(icons[icon_1]);
		switcher_1.setBounds(580,180,98,61);
		switcher_1.addMouseListener(controller);
		switcher_1.setOpaque(false);
		switcher_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(switcher_1);
		
		soundLabel = new JLabel("音效开关");
		soundLabel.setFont(new Font("microsoft yahei", Font.BOLD, 20));
		soundLabel.setBounds(340, 340, 100, 30);
		this.add(soundLabel);
		
//		note3 = new JLabel();
//		note3.setBounds(600,250,120,140);
//		note3.setIcon(new ImageIcon( musical.getHorImage(2)));
//		this.add(note3);
//		
//		note4 = new JLabel();
//		note4.setBounds(700,150,120,140);
//		note4.setIcon(new ImageIcon( musical.getHorImage(3)));
//		this.add(note4);
		
		note1 = new JLabel();
		note1.setBounds(20,210,300,300);
		note1.setIcon(new ImageIcon( "pic/liusheng.png"));
		this.add(note1);
		
	
		icon_2 = sou?1:0;
		switcher_2 = new JLabel();
		switcher_2.setIcon(icons[icon_2]);
		switcher_2.setBounds(580,330,98,61);
		switcher_2.addMouseListener(controller);
		switcher_2.setOpaque(false);
		switcher_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(switcher_2);

		ButtonGroup bg2=new ButtonGroup();
		bg2.add(open2);
		bg2.add(close2);

		direction = new JLabel("掉落方向");
		direction.setFont(new Font("microsoft yahei", Font.BOLD, 20));
		direction.setBounds(340,490,80,30);
		this.add(direction);

		down = new JCheckBox("  从上向下");
		down.setForeground(new Color(69,106,28));
		down.setFont(new Font("microsoft yahei", Font.BOLD, 24));
		down.setBounds(555,465,160,40);
		down.setSelected(dir);
		down.addItemListener(controller);
		down.setOpaque(false);
		this.add(down);

		left = new JCheckBox("  从左向右");
		left.setForeground(new Color(69,106,28));
		left.setFont(new Font("microsoft yahei", Font.BOLD, 24));
		left.setBounds(555,525,160,40);
		left.setSelected(!dir);
		left.addItemListener( controller);
		left.setOpaque(false);
		this.add(left);

		ButtonGroup bg=new ButtonGroup();
		bg.add(down);
		bg.add(left);

		backButton = new JLabel();
		backButton.setIcon(back);
		backButton.setBounds(0, 0, 50, 50);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.addMouseListener(controller);
		this.add(backButton);

	}

	/**
	 * 读取配置文件
	 */
	private void getConfig(){
		ArrayList<String> configList = FileIO.getConfig();
		String con1 = null;
		String con2 = null;
		String con3 = null;

		for(String s:configList){
			if(s.contains("sound")){
				con1 = s;
			}
			if(s.contains("direction")){
				con2 = s;
			}
			if(s.contains("music")){
				con3 = s;
			}
		}
		String[] split = con1.split(" ");
		if(Integer.parseInt(split[1])!=0){//0表示禁音
			sou = true;
		}
		else
			sou = false;

		String[] split1 = con2.split(" ");
		if(Integer.parseInt(split1[1])!=0){//0表示往下落
			dir = false;
		}
		else
			dir = true;
		
		String[] split2 = con3.split(" ");
		if(Integer.parseInt(split2[1])!=0){//0表示禁音
			mus = true;
		}
		else
			mus = false;

	}

	public BgPanel getParentPanel() {
		return parentPanel;
	}
	

}

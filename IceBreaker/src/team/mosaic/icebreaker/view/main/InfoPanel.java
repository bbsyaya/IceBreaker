package team.mosaic.icebreaker.view.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import team.mosaic.icebreaker.components.MyPanel;
import team.mosaic.icebreaker.components.MyScrollPane;
import team.mosaic.icebreaker.controller.InfoController;
import team.mosaic.icebreaker.file.FileIO;
import team.mosaic.icebreaker.file.SubImg;
import team.mosaic.icebreaker.model.OnlineStatus;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.view.login.LoginPanel;
import team.mosaic.icebreaker.viewservice.InfoViewService;
import team.mosaic.icebreaker.vo.GainVO;
import team.mosaic.icebreaker.vo.InfoVO;

/**
 * 个人信息界面
 * @author acer
 *
 */
public class InfoPanel extends JPanel implements InfoViewService{

	private static final long serialVersionUID = -5606085704820837994L;

	private InfoController controller;
	private BgPanel parentPanel;

	private JLabel titleLabel;
	public JLabel label,left,right;

	public JLabel avgLabel,bestLabel,comboLabel,gameCount,everyGame;

	private JLabel totalGame;
	private JLabel avgScore;
	private JLabel id,coins,level,exp;
	public JLabel price;
	public boolean get = true;

	private JScrollPane scrollPane;
	private MyPanel data;

	public JLabel backButton;

	public int i = 0;
	private SubImg ch;

	public SubImg ar;
	private ImageIcon character;
	private GainVO gvo;
	private LoginPanel lp;
	
	private SubImg pic = new SubImg(SubImg.BUTTON_INFO,100,50);
	public ImageIcon button1 = new ImageIcon( pic.getImage(0));
	public ImageIcon button2 = new ImageIcon( pic.getImage(1));
	
	private SubImg dollar = new SubImg(SubImg.DOLLAR,120,32);
	
	private Image infobg = new SubImg(SubImg.INFO_BG,550,400).getImage(0);
	private ImageIcon back = new ImageIcon( SubImg.BACK);
	private ImageIcon coinIcon = new ImageIcon( SubImg.coin);
	private ImageIcon expIcon = new ImageIcon( SubImg.exp);
	private ImageIcon lvIcon = new ImageIcon( SubImg.lv);
	private ImageIcon idIcon = new ImageIcon( SubImg.id);
	
	
	
//	public ImageIcon buttons[] = {button1,button2};
//	public int icon_1 = 0;
//	public int icon_2 = 0;
//	public int icon_3 = 0;
//	public int icon_4 = 0;

	public ArrayList<String> idList;
	public ArrayList<Integer> hpList;

	public InfoPanel(BgPanel bp){
		parentPanel = bp;
		ch = new SubImg(SubImg.CHARACTER,323,453);	
		ar = new SubImg(SubImg.ARROW,50,50);
		controller = new InfoController(this);
		init();

	}

	private void init(){
		this.setLayout(null);
		this.setOpaque(false);
		this.setBounds(0, 0, 960, 600);

		titleLabel = new JLabel(new ImageIcon("pic/self_info.png"));
		titleLabel.setBounds(70, 15, 250, 83);
		this.add(titleLabel);

		backButton = new JLabel();
		backButton.setBounds(0, 0, 50, 50);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.setIcon(back);
		backButton.addMouseListener(controller);
		this.add(backButton);

		character = new ImageIcon(ch.getHorImage(getLocalRole()+4));
		controller.choose = getLocalRole();
		controller.i  = getLocalRole() ;

		label = new JLabel(character);
		label.setBounds(10,86,323,453);
		label.addMouseListener(controller);
		this.add(label);

		left = new JLabel(new ImageIcon(ar.getHorImage(0)));
		left.setBounds(40,370,50,50);
		left.addMouseListener(controller);
		left.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.add(left);

		right = new JLabel(new ImageIcon(ar.getHorImage(2)));
		right.setBounds(230,370,50,50);
		right.addMouseListener(controller);
		right.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.add(right);

		Font font = new Font("隶书",1,15);
		
		avgLabel = new JLabel("每日平均分");
		avgLabel.setFont(font);
		avgLabel.setBounds(350, 500, 100, 50);
		avgLabel.setOpaque(false);
		avgLabel.setIcon(button2);
		avgLabel.setHorizontalTextPosition(JLabel.CENTER);
        avgLabel.setVerticalTextPosition(JLabel.CENTER);
		avgLabel.addMouseListener(controller);
		this.add(avgLabel);

		bestLabel = new JLabel("每日最高分");
		bestLabel.setFont(font);
		bestLabel.setBounds(460, 500, 100, 50);
		bestLabel.setOpaque(false);
		bestLabel.setIcon(button1);
		bestLabel.setHorizontalTextPosition(JLabel.CENTER);
		bestLabel.setVerticalTextPosition(JLabel.CENTER);
		bestLabel.addMouseListener(controller);
		this.add(bestLabel);
		
		everyGame = new JLabel("每局得分");
		everyGame.setFont(font);
		everyGame.setBounds(570, 500, 100, 50);
		everyGame.setOpaque(false);
		everyGame.setIcon(button1);
		everyGame.setHorizontalTextPosition(JLabel.CENTER);
		everyGame.setVerticalTextPosition(JLabel.CENTER);
		everyGame.addMouseListener(controller);
		this.add(everyGame);

		comboLabel = new JLabel("每日最高连击");
		comboLabel.setFont(font);
		comboLabel.setBounds(680, 500, 100, 50);
		comboLabel.setOpaque(false);
		comboLabel.setIcon(button1);
		comboLabel.setHorizontalTextPosition(JLabel.CENTER);
		comboLabel.setVerticalTextPosition(JLabel.CENTER);
		comboLabel.addMouseListener(controller);
		this.add(comboLabel);

		gameCount = new JLabel("每日游戏局数");
		gameCount.setFont(font);
		gameCount.setBounds(790, 500, 100, 50);
		gameCount.setOpaque(false);
		gameCount.setIcon(button1);
		gameCount.setHorizontalTextPosition(JLabel.CENTER);
		gameCount.setVerticalTextPosition(JLabel.CENTER);
		gameCount.addMouseListener(controller);
		this.add(gameCount);

		Font f = new Font("隶书",1,16);
		
		totalGame = new JLabel("总局数");
		totalGame.setBounds(830, 40, 100, 30);
		totalGame.setFont(f);
		totalGame.setOpaque(false);
		this.add(totalGame);

		avgScore = new JLabel("平均分");
		avgScore.setBounds(830, 70, 130, 30);
		avgScore.setFont(f);
		avgScore.setOpaque(false);
		this.add(avgScore);

		id = new JLabel(idIcon);
		id.setBounds(350, 40, 130, 40);
		id.setFont(f);
		id.setOpaque(false);
		this.add(id);

		coins = new JLabel(coinIcon);
		coins.setBounds(710, 40, 130, 40);
		coins.setFont(f);
		coins.setOpaque(false);
		this.add(coins);

		level = new JLabel(lvIcon);
		level.setBounds(470,40, 130, 40);
		level.setFont(f);
		level.setOpaque(false);
		this.add(level);

		exp = new JLabel(expIcon);
		exp.setBounds(590, 40, 130, 40);
		exp.setFont(f);
		exp.setOpaque(false);
		this.add(exp);
		
		price = new JLabel();
		price.setBounds(110,180,120,32);
		price.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		price.addMouseListener(controller);

		data = new MyPanel();
		data.setVisible(true);
		data.setOpaque(false);
//		data.setBackground(new Color(55,140,200));
		
		data.setLineColor(Color.YELLOW);
		data.setWordColor(Color.BLUE);
		data.setXaxisColor(Color.YELLOW);
		data.setDotColor(Color.DARK_GRAY);
		data.setHeightPerHundredScores(1);

		scrollPane = new MyScrollPane(infobg);
		scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(350,100,550,400);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setViewportView(data);

		this.add(scrollPane);

		//		String[] data1 = {"2014-05-12\t100","2014-05-13\t90","2014-05-14\t110","2014-05-15\t80","2014-05-16\t120"};
		//		ArrayList<String> c = new ArrayList<String>();
		//		for(int i = 0;i<data1.length;i++){
		//			c.add(data1[i]);
		//		}
		//		showMaxCombo(c);
		this.repaint();

	}

	public BgPanel getParentPanel() {
		return parentPanel;
	}

	public void setCharacter(int i,boolean have){
		this.i = i;		
		label.remove(price);
		this.remove(label);
		if(!have && (i!=0)){			
			character = new ImageIcon(ch.getHorImage(i+8));
			label.setIcon(character);
			price.setIcon(new ImageIcon(dollar.getImage(i-1)));
			label.add(price);
			get = false;
		}
		else{
			character = new ImageIcon(ch.getHorImage(i));
			label.setIcon(character);
			get = true;
		}
		this.add(label);
		this.repaint();
	}

	@Override
	public void showInfo(InfoVO iv) {
		// TODO Auto-generated method stub
		id.setText("	"+iv.getID());
		coins.setText("	"+iv.getCoin());
		level.setText("	"+iv.getLevel());
		exp.setText("	"+iv.getExp());
	}

	public void getInfo(){
		controller.getInfo();
	}

	public void updateChar(){
		controller.updateChar();
	}

	@Override
	public void getChar(int k,boolean bool) {
		// TODO Auto-generated method stub

		if(!bool){
			Prompt.showWarning(this.parentPanel.getParentFrame(), "赚点金币再来吧！");
		}
		else{
			Prompt.showWarning(this.parentPanel.getParentFrame(), "购买成功！");
			//			updateChar();
		}
		setCharacter(k,bool);

	}

	@Override
	public void showTotalRecord(int gameCount, int avgScore) {
		// TODO Auto-generated method stub
		totalGame.setText("总局数:"+String.valueOf(gameCount));
		this.avgScore.setText("平均分:"+String.valueOf(avgScore));
	}

	@Override
	public void showAvg(ArrayList<String> list) {
		// TODO Auto-generated method stub
		avgLabel.setIcon(button2);
		bestLabel.setIcon(button1);
		comboLabel.setIcon(button1);
		gameCount.setIcon(button1);
		everyGame.setIcon(button1);
		ArrayList<String> date = new ArrayList<String>();
		ArrayList<Integer> record  = new ArrayList<Integer>();
		for(String s:list){
			date.add(s.split("\t")[0]);
			record.add(Integer.valueOf(s.split("\t")[1]));
		}


		//		int[] data1 = {100,90,110,80,120};
		//		ArrayList<Integer> c = new ArrayList<Integer>();
		//		for(int i = 0;i<data1.length;i++){
		//			c.add(data1[i]);
		//		}
		//		data.setBounds(0,0,400,300);
		//		data.setPreferredSize(new Dimension(800,600));
		//		scrollPane.add(data);
		//		scrollPane.getViewport().setView(data);
		//		try {
		//			Thread.sleep(500);
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		c.add(500);
		//		l.setData(c);
		//		try {
		//			Thread.sleep(2000);
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
//		data.setOpaque(false);
		data.setData(record);
		data.setInfo(date);
		data.repaint();
		scrollPane.revalidate();
		scrollPane.repaint();
		//		this.repaint();
	}

	@Override
	public void showBest(ArrayList<String> list) {
		// TODO Auto-generated method stub
		avgLabel.setIcon(button1);
		bestLabel.setIcon(button2);
		comboLabel.setIcon(button1);
		gameCount.setIcon(button1);
		everyGame.setIcon(button1);
		ArrayList<String> date = new ArrayList<String>();
		ArrayList<Integer> record  = new ArrayList<Integer>();
		for(String s:list){
			date.add(s.split("\t")[0]);
			record.add(Integer.valueOf(s.split("\t")[1]));
		}

		data.setData(record);
		data.setInfo(date);
		data.repaint();
		scrollPane.revalidate();
		scrollPane.repaint();
	}

	@Override
	public void showGameCount(ArrayList<String> list) {
		// TODO Auto-generated method stub
		avgLabel.setIcon(button1);
		bestLabel.setIcon(button1);
		comboLabel.setIcon(button1);
		gameCount.setIcon(button2);
		everyGame.setIcon(button1);
		ArrayList<String> date = new ArrayList<String>();
		ArrayList<Integer> record  = new ArrayList<Integer>();
		for(String s:list){
			date.add(s.split("\t")[0]);
			record.add(Integer.valueOf(s.split("\t")[1]));
		}

		data.setData(record);
		data.setInfo(date);

		data.repaint();
		scrollPane.revalidate();
		scrollPane.repaint();
	}

	@Override
	public void showMaxCombo(ArrayList<String> list) {
		// TODO Auto-generated method stub
		avgLabel.setIcon(button1);
		bestLabel.setIcon(button1);
		comboLabel.setIcon(button2);
		gameCount.setIcon(button1);
		everyGame.setIcon(button1);
		ArrayList<String> date = new ArrayList<String>();
		ArrayList<Integer> record  = new ArrayList<Integer>();
		for(String s:list){
			date.add(s.split("\t")[0]);
			record.add(Integer.valueOf(s.split("\t")[1]));
		}

		data.setData(record);
		data.setInfo(date);
		data.repaint();
		scrollPane.revalidate();
		scrollPane.repaint();
	}
	

	@Override
	public void showEveryGame(ArrayList<String> list) {
		// TODO Auto-generated method stub
		avgLabel.setIcon(button1);
		bestLabel.setIcon(button1);
		comboLabel.setIcon(button1);
		gameCount.setIcon(button1);
		everyGame.setIcon(button2);
		ArrayList<String> date = new ArrayList<String>();
		ArrayList<Integer> record  = new ArrayList<Integer>();
		for(String s:list){
			date.add(s.split("\t")[0]);
			record.add(Integer.valueOf(s.split("\t")[1]));
		}

		data.setData(record);
		data.setInfo(date);
		data.repaint();
		scrollPane.revalidate();
		scrollPane.repaint();
	}


	public int getCharacter(){
		return controller.charnumber();
	}

	public int getLocalRole() {
		// TODO Auto-generated method stub
		if(!OnlineStatus.isOnline())
			return 0;
		ArrayList<String> accountList=FileIO.read(FileIO.ACCOUNT_FILE);
		idList  = new ArrayList<String>();
		hpList = new ArrayList<Integer>();
		idList.clear();
		hpList.clear();

		int num = accountList.size();
		for(int i=0;i<num;i++){
			String[] account=accountList.get(i).split(" ");

			hpList.add(Integer.parseInt(account[0]));
			String userName=account[1];
			idList.add(userName);

		}
		return hpList.get(idList.size()-1);
	}


}

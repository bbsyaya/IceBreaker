package team.mosaic.icebreaker.view.main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import team.mosaic.icebreaker.components.MyTable;
import team.mosaic.icebreaker.controller.ReadyController;
import team.mosaic.icebreaker.file.SubImg;
import team.mosaic.icebreaker.model.ClientModel;
import team.mosaic.icebreaker.model.GameModel;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.view.game.GamePanel;
import team.mosaic.icebreaker.viewservice.ReadyViewService;

/**
 * 游戏准备界面
 * @author acer
 *
 */
public class ReadyPanel extends JPanel implements ReadyViewService{
	private static final long serialVersionUID = 7890887964835446353L;

	private ReadyController controller;
	private BgPanel parentPanel;
	private ImageIcon role;
	private SubImg r;
	private DefaultTableModel dtm;
	private ImageIcon tool_C,tool_D,tool_E,tool_F;
	public JLabel tc,td,te,tf;
	
	public JCheckBox t1,t2,t3,t4;
	public JScrollPane scroll;
	public JLabel singleButton;
	public JLabel doubleButton;
	public JLabel pkButton;
	public JLabel roleLabel;
	public JLabel startGame,invite;
	public JLabel Random_Couple;
	public JLabel random_fousome;
	public JTable friends;
	public String player;
	
	private SubImg modeButton = new SubImg(SubImg.MODE_BUTTON, 220, 60); 
	private ImageIcon single1;
	private ImageIcon single2;
	private ImageIcon coop1;
	private ImageIcon coop2;
	private ImageIcon pk1;
	private ImageIcon pk2;
	
	public ReadyPanel(BgPanel bp){
		parentPanel = bp;
		r = new SubImg(SubImg.CHARACTER_SMALL,200,300);	
		controller = new ReadyController(this);
		ClientModel.getGameModel().setReadyView(this);
		single1 = new ImageIcon(modeButton.getImage(0));
		single2 = new ImageIcon(modeButton.getImage(3));
		coop1 = new ImageIcon(modeButton.getImage(1));
		coop2 = new ImageIcon(modeButton.getImage(4));
		pk1 = new ImageIcon(modeButton.getImage(2));
		pk2 = new ImageIcon(modeButton.getImage(5));
		init();
	}

	private void init(){
		this.setLayout(null);
//		this.setBackground(Color.DARK_GRAY);
		this.setOpaque(false);
		this.setBounds(250, 30, 680, 540);

		singleButton = new JLabel(single2);
		singleButton.setBounds(0, 80, 220, 60);
		singleButton.addMouseListener(controller);
		singleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		doubleButton = new JLabel(coop1);
		doubleButton.setBounds(0, 220, 220, 60);
		doubleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		pkButton = new JLabel(pk1);
		pkButton.setBounds(0,360,220,60);
		pkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		role = new ImageIcon(r.getHorImage(parentPanel.getInfoPanel().getLocalRole()));
		roleLabel = new JLabel(role);
		roleLabel.setBounds(300, 82, 200, 300);
		this.add(roleLabel);
		
		Random_Couple = new JLabel(new ImageIcon("pic/randomtwo.png"));
		Random_Couple.setBounds(430,480,100,38);
		Random_Couple.addMouseListener(controller);
		Random_Couple.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		random_fousome = new JLabel(new ImageIcon("pic/randomfour.png"));
		random_fousome.setBounds(560,480,100,38);
		random_fousome.addMouseListener(controller);
		random_fousome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		String[][] tableData = {};
		final String[] columnTitle = { "在线空闲好友" };
		dtm = new DefaultTableModel(tableData,
				columnTitle) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int colunm) {
				return false;
			}

		};

		friends = new MyTable(dtm,false);
		friends.setBorder(BorderFactory.createLineBorder(Color.white));
		friends.setDragEnabled(false);
		friends.getTableHeader().setResizingAllowed(false);
		friends.setOpaque(false);

		scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.getViewport().setOpaque(false);
		scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setOpaque(false);
		JScrollBar bar = scroll.getVerticalScrollBar(); 
		bar.setOpaque(false);
		scroll.setViewportView(friends);
		scroll.setBounds(330, 82, 150, 350);
		scroll.addMouseListener(controller);	

		t1 = new JCheckBox();
		t1.setBounds(550,80,90,50);
		t1.setSelected(false);
		t1.addItemListener(controller);
		t1.setOpaque(false);

		t2 = new JCheckBox();
		t2.setBounds(550,180,90,50);
		t2.setSelected(false);
		t2.addItemListener( controller);
		t2.setOpaque(false);


		t3 = new JCheckBox();
		t3.setBounds(550,280,90,50);
		t3.setSelected(false);
		t3.addItemListener( controller);
		t3.setOpaque(false);


		t4 = new JCheckBox();
		t4.setBounds(550,380,90,50);
		t4.setSelected(false);
		t4.addItemListener( controller);
		t4.setOpaque(false);
		
		tool_C = new ImageIcon(SubImg.TOOL_C);
		tc = new JLabel(tool_C,JLabel.CENTER);
		tc.setBounds(590,80,50,50);
		tc.addMouseListener(controller);

		
		tool_D = new ImageIcon(SubImg.TOOL_D);
		td = new JLabel(tool_D,JLabel.CENTER);
		td.setBounds(590,180,50,50);
		td.addMouseListener(controller);

		
		tool_E = new ImageIcon(SubImg.TOOL_E);
		te = new JLabel(tool_E,JLabel.CENTER);
		te.setBounds(590,280,50,50);
		te.addMouseListener(controller);

		
		tool_F = new ImageIcon(SubImg.TOOL_F);
		tf = new JLabel(tool_F,JLabel.CENTER);
		tf.setBounds(590,380,50,50);
		tf.addMouseListener(controller);

		this.add(doubleButton);
		this.add(pkButton);

		if(MainFrame.onLine){
			doubleButton.addMouseListener(controller);
			pkButton.addMouseListener(controller);
			this.add(t1);
			this.add(t2);
			this.add(t3);
			this.add(t4);
			this.add(tc);
			this.add(td);
			this.add(te);
			this.add(tf);
		}
		else {
			doubleButton.setEnabled(false);
			pkButton.setEnabled(false);
		}
		
		this.add(singleButton);
		
		startGame = new JLabel(new ImageIcon("pic/start.png"));
		startGame.setBounds(370,450,100,38);
		startGame.addMouseListener(controller);
		startGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(startGame);
		
		invite = new JLabel(new ImageIcon("pic/invite.png"));
		invite.setBounds(290,480,100,38);
		invite.addMouseListener(controller);
		invite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
	}

	public BgPanel getParentPanel() {
		return parentPanel;
	}

	/**
	 * 显示人物
	 */
	public void showRole(){
		if(controller.mode == 1){
		this.remove(scroll);
		this.remove(Random_Couple);
		this.remove(random_fousome);
		this.remove(invite);
		this.remove(roleLabel);
		role = new ImageIcon(r.getHorImage(parentPanel.getInfoPanel().getCharacter()));
		roleLabel.setIcon(role);
		this.add(startGame);
		this.add(roleLabel);
		this.repaint();
		}
	}

	//道具不足，取消选中
	public void cancelSelect(int i){
		if(i == 0){		
				t1.setSelected(false);			
		}
		if(i == 1){
				t2.setSelected(false);
			
		}
		if(i == 2){
				t3.setSelected(false);
			
		}
		if(i == 3){
				t4.setSelected(false);
			
		}
	}

	private void cleanTable() {
		while (dtm.getRowCount() > 0)
			dtm.removeRow(0);
	}

	/**
	 * 显示好友
	 */
	public void showFriends(ArrayList<String> friends) {
		// TODO Auto-generated method stub
		this.remove(roleLabel);
		this.remove(startGame);
		fillInTable(friends);
		this.add(scroll);
		this.add(invite);
		this.add(Random_Couple);
		this.add(random_fousome);
		this.repaint();
	}
	

	
	public void setFocus(int mode){
		switch (mode) {
		case 1:
			singleButton.setIcon(single2);
			doubleButton.setIcon(coop1);
			pkButton.setIcon(pk1);
			break;
		case 2:
			singleButton.setIcon(single1);
			doubleButton.setIcon(coop2);
			pkButton.setIcon(pk1);
			break;
		case 3:
			singleButton.setIcon(single1);
			doubleButton.setIcon(coop1);
			pkButton.setIcon(pk2);
			break;
		default:
			break;
		}
	}
	
	private void fillInTable(ArrayList<String> list) {
		cleanTable();
		String[] split = null;
		String[] row = new String[2];
		for (String c : list) {
			split = c.split(" "); //暂时写为空格
			row[0] = split[0];
//			row[1] = split[1];
			dtm.addRow(row);
		}
	}
	
	/************************************************************************/
	@Override
	public void findCoopPair(String player){
		MainFrame frame = parentPanel.getParentFrame();
		GamePanel gp = frame.getGamePanel();
		gp.initCoop(parentPanel.getInfoPanel().getCharacter(), player,controller.getTools());
		Prompt.showMessage(this.parentPanel.getParentFrame(), "即将与  "+player+" 开始游戏咯！");	
	}
	
	@Override
	public void findCoopFour(String[] id) {
		// TODO Auto-generated method stub
		MainFrame frame = parentPanel.getParentFrame();
		GamePanel gp = frame.getGamePanel();
		gp.initCoopFour(parentPanel.getInfoPanel().getCharacter(), id,controller.getTools());
		Prompt.showMessage(this.parentPanel.getParentFrame(), "即将与  "+id[0]+"、"+id[1]+" 、"+id[2]+" 开始游戏咯！");	
	}
	
	@Override
	public void findPKFour(String[] id) {
		// TODO Auto-generated method stub
		MainFrame frame = parentPanel.getParentFrame();
		GamePanel gp = frame.getGamePanel();
		gp.initPKFour(parentPanel.getInfoPanel().getCharacter(), id,controller.getTools());
		Prompt.showMessage(this.parentPanel.getParentFrame(), "匹配到搭档 "+id[1]+" 和对手 "+id[2]+"、"+id[3]);	
	}

	@Override
	public void startTwosome() {
		// TODO Auto-generated method stub
		MainFrame frame = parentPanel.getParentFrame();
		frame.modifyPanel(frame.getGamePanel());
		ClientModel.getBoardModel().startTimer();
	}

	@Override
	public void startFoursome() {
		// TODO Auto-generated method stub
		MainFrame frame = parentPanel.getParentFrame();
		frame.modifyPanel(frame.getGamePanel());
		ClientModel.getBoardModel().startTimer();
	}

	@Override
	public void startPk(final String id) {
		Prompt.showMessage(this.parentPanel.getParentFrame(), "即将与  "+id+" 开始单挑咯！");	
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainFrame frame = parentPanel.getParentFrame();
				GamePanel gp = frame.getGamePanel();
				gp.initPk(parentPanel.getInfoPanel().getCharacter(),id,controller.getTools());
				frame.modifyPanel(gp);
				ClientModel.getBoardModel().startTimer();
	}
	/************************************************************************/
	
	public String getPlayer(){
		return player;
	}

	@Override
	public MainFrame getFrame() {
		// TODO Auto-generated method stub
		return this.parentPanel.getParentFrame();
	}

	
}
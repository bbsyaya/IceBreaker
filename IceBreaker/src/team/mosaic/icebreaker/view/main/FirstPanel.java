package team.mosaic.icebreaker.view.main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import team.mosaic.icebreaker.components.MyTable;
import team.mosaic.icebreaker.components.OvalButton;
import team.mosaic.icebreaker.components.PlayerLabel;
import team.mosaic.icebreaker.controller.MenuController;
import team.mosaic.icebreaker.file.SubImg;
import team.mosaic.icebreaker.model.OnlineStatus;
import team.mosaic.icebreaker.utility.Loading;
import team.mosaic.icebreaker.utility.Prompt;
//import team.mosaic.icebreaker.sound.BGMusic;
import team.mosaic.icebreaker.viewservice.FirstViewService;

/**
 * 首界面
 * @author acer
 *
 */
public class FirstPanel extends JPanel implements FirstViewService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6631894244062998728L;

	private static final int GAP_TIME = 15; //线程睡眠时间
	private MenuController controller;
	private BgPanel parentPanel;
	private FirstPanel self = this;
	private boolean ready;
	private boolean onLine;
	private SubImg si,op;
	private JTable rank;
	private JScrollPane scrollPane ;
	private  DefaultTableModel dtm ;
	private PlayerLabel pl;
	
	public ImageIcon ball,tool,info,set,help;

	public JLabel beginButton;
	public JLabel toolButton;
	public JLabel infoButton;
	public JLabel setButton;
	public JLabel helpButton;
	public JLabel logoutButton;
	public JLabel backButton;
	public OvalButton ob;
	public JLabel friendRank,worldRank;
	public JLabel addFriend;

	private static ImageIcon friendRankIcon = new ImageIcon("pic/friend_rank.png");
	private static ImageIcon worldRankIcon = new ImageIcon("pic/world_rank.png");
	private static ImageIcon friendRankIcon2 = new ImageIcon("pic/friend_rank2.png");
	private static ImageIcon worldRankIcon2 = new ImageIcon("pic/world_rank2.png");
	
	public FirstPanel(BgPanel p, boolean ol) {
		parentPanel = p;
		onLine = ol;
		ready = false;
		si = new SubImg(SubImg.BALL,370,370);
		op = new SubImg(SubImg.OPTION,260,115);
		dtm = new DefaultTableModel();
		controller = new MenuController(this);
		init();
	}

	public boolean getReady() {
		return ready;
	}

	public void setReady(boolean r) {
		ready = r;
	}

	public boolean getOnline() {
		return onLine;
	}

	private void init() {
		this.setLayout(null);
		this.setOpaque(false);
		this.setBounds(0, 0, 960, 600);

//		BGMusic.play(BGMusic.BGM1);
	      String[][] tableData = {};
	    	final String[] columnTitle = { "NO", "ID","Score" };
	    	dtm = new DefaultTableModel(tableData,
	    			columnTitle) {
	    		private static final long serialVersionUID = 1L; 

	    		public boolean isCellEditable(int row, int colunm) {
	    			return false;
	    		}

	    	};
	    	
		
		ball = new ImageIcon(si.getImage(0));
		tool = new ImageIcon(op.getImage(0));
		info = new ImageIcon(op.getImage(1));
		set = new ImageIcon(op.getImage(2));
		help = new ImageIcon(op.getImage(3));

		beginButton = new JLabel(ball);
		beginButton.setBounds(20, 155, 370, 370);
		beginButton.addMouseListener(controller);
		beginButton.addMouseMotionListener(controller);
		this.add(beginButton);

		toolButton = new JLabel(tool);
		toolButton.setBounds(410, 120, 260, 115);
		if (onLine) {
			toolButton.addMouseListener(controller);
		} else {
			toolButton.setEnabled(false);
		}
		this.add(toolButton);

		infoButton = new JLabel(info);
		infoButton.setBounds(410, 230, 260,115 );
		if (onLine) {
			infoButton.addMouseListener(controller);
		} else {
			infoButton.setEnabled(false);
		}
		this.add(infoButton);

		setButton = new JLabel(set);
		setButton.setBounds(410, 340, 260, 115);
		setButton.addMouseListener(controller);
		this.add(setButton);

		helpButton = new JLabel(help);
		helpButton.setBounds(410, 450, 260, 115);
		helpButton.addMouseListener(controller);
		this.add(helpButton);

		logoutButton = new JLabel(new ImageIcon("pic/logout.png"));
		logoutButton.setBounds(900, 30, 48, 48);
		logoutButton.addMouseListener(controller);
		logoutButton.setToolTipText("注销");
		logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(logoutButton);

		backButton = new JLabel(new ImageIcon("pic/back.png"));
		backButton.setBounds(0, 0, 50, 50);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.addMouseListener(controller);

		friendRank = new JLabel(friendRankIcon2);
		friendRank.setBounds(710,80,100,48);
		friendRank.addMouseListener(controller);
		friendRank.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(friendRank);
		
		worldRank = new JLabel(worldRankIcon);
		worldRank.setBounds(850,80,100,48);
		worldRank.addMouseListener(controller);
		worldRank.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(worldRank);
        
    	
        rank = new MyTable(dtm,false);
        rank.setBorder(BorderFactory.createLineBorder(Color.white));
        rank.setDragEnabled(false);
        rank.getTableHeader().setResizingAllowed(false);
        rank.getTableHeader().setReorderingAllowed(false);
        JTableHeader head = rank.getTableHeader();
 //       head.setOpaque(false);
 //       head.setBackground(new Color(255,255,255));
        rank.setOpaque(false);
        
        scrollPane = new JScrollPane();
//        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
   //    scrollPane.setColumnHeaderView(head);//设置头部（HeaderView部分）  
   //     scrollPane.getColumnHeader().setOpaque(false);//再取出头部，并设置为透明  
        JScrollBar bar = scrollPane.getVerticalScrollBar(); 
 //       bar.setBackground(new Color(255,255,255));
        bar.setOpaque(false);
        scrollPane.setViewportView(rank);
        scrollPane.setBounds(710, 120, 240, 400);
        this.add(scrollPane);	
        
        addFriend = new JLabel();
        addFriend.setIcon(new ImageIcon(SubImg.ADD_FRIEND));
        addFriend.setBounds(225, -2, 200, 80);
        addFriend.addMouseListener(controller);
        addFriend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addFriend.setToolTipText("添加好友");
        if(OnlineStatus.isOnline())
        	this.add(addFriend);
        
        pl = new PlayerLabel();
        pl.setLocation(-5, 0);
        this.add(pl);
	}

	public void ready() {
		Loading.addLoading();
		ready = true;
		this.remove(logoutButton);
		this.add(backButton);
		this.remove(scrollPane);
		this.remove(friendRank);
		this.remove(worldRank);
		this.remove(pl);
		this.repaint();

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				double x = beginButton.getX();
				double y = beginButton.getY();

				double x1 = toolButton.getX();
				double y1 = toolButton.getY();
				double y2 = infoButton.getY();
				double y3 = setButton.getY();
				double y4 = helpButton.getY();
				try {
					for (int i = 0; i < 11; i++, x -= 20, x1 -= 60, y1 += 10, y2 += 5, y3 -= 5, y4 -= 10) {
						beginButton.setLocation((int) x, (int) y);
						toolButton.setLocation((int) x1, (int) y1);
						infoButton.setLocation((int) x1, (int) y2);
						setButton.setLocation((int) x1, (int) y3);
						helpButton.setLocation((int) x1, (int) y4);
						ball.setImage(si.getImage(i));
						Thread.sleep(GAP_TIME);
					}
					self.repaint();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Loading.removeLoading();
			}
		});
		thread.start();
		
	}

	public void back() {
		Loading.addLoading();
		ready = false;
		this.remove(backButton);
		this.add(pl);
		this.add(logoutButton);	

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				double x = beginButton.getX();
				double y = beginButton.getY();

				double x1 = toolButton.getX();
				double y1 = toolButton.getY();
				double y2 = infoButton.getY();
				double y3 = setButton.getY();
				double y4 = helpButton.getY();
				try {
					for (int i = 0; i < 11; i++, x += 20, x1 += 60, y1 -= 10, y2 -= 5, y3 += 5, y4 += 10) {
						beginButton.setLocation((int) x, (int) y);
						toolButton.setLocation((int) x1, (int) y1);
						infoButton.setLocation((int) x1, (int) y2);
						setButton.setLocation((int) x1, (int) y3);
						helpButton.setLocation((int) x1, (int) y4);
						ball.setImage(si.getImage(10-i));
						Thread.sleep(GAP_TIME);
					}
					self.repaint();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Loading.removeLoading();
			}
		});
		thread.start();
		this.add(friendRank);
		this.add(worldRank);
		this.add(scrollPane);
	}

	public BgPanel getParentPanel() {
		return parentPanel;
	}

	@Override
	public void refreshFriendsRank(ArrayList<String> friends) {
		// TODO Auto-generated method stub
		cleanTable();
		fillInTable(friends);
		this.repaint();
	}

	@Override
	public void refreshAllRank(ArrayList<String> allplayers) {
		// TODO Auto-generated method stub
		cleanTable();
		fillInTable(allplayers);
		this.repaint();
	}
	
	private void cleanTable() {
		while (dtm.getRowCount() > 0)
			dtm.removeRow(0);
	}
	
	private void fillInTable(ArrayList<String> list) {
		cleanTable();
		String[] split = null;
		String[] row = new String[3];
		int rank = 1;
		for (String c : list) {
			split = c.split("\t"); 
			row[0] = String.valueOf(rank);
			row[1] = split[0];
			row[2] = split[1];
			dtm.addRow(row);
			rank++;
		}
	}

	@Override
	public void friendApply(String id) {
		// TODO Auto-generated method stub
//		new Dialogs().friendApply(id,(Frame)(parentPanel.getParentFrame()));
		Prompt.showFriendApp(parentPanel.getParentFrame(), id);
		
	}
	
	public void showFriendRank(){
		friendRank.setIcon(friendRankIcon2);
		worldRank.setIcon(worldRankIcon);
	}
	
	public void showWorldRank(){
		worldRank.setIcon(worldRankIcon2);
		friendRank.setIcon(friendRankIcon);
	}

}

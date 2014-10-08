package team.mosaic.icebreaker.view.main;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import team.mosaic.icebreaker.components.MyScrollPane;
import team.mosaic.icebreaker.controller.HelpController;
import team.mosaic.icebreaker.file.SubImg;
import team.mosaic.icebreaker.viewservice.HelpViewService;

public class HelpPanel extends JPanel implements HelpViewService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5463519679042036009L;
	
	private HelpController controller;
	private BgPanel parentPanel;
	
	private JLabel titleLabel;
	
	public JLabel backButton;
	
	JLabel helpField;
	
	public HelpPanel(BgPanel bp){
		parentPanel = bp;
		controller = new HelpController(this);
		init();
	}
	
	private void init(){
		this.setLayout(null);
		this.setOpaque(false);
		this.setBounds(0, 0, 960, 600);
		
		titleLabel = new JLabel(new ImageIcon("pic/help_title.png"));
		titleLabel.setBounds(355, 30, 250, 83);
		this.add(titleLabel);
		
		backButton = new JLabel(new ImageIcon("pic/back.png"));
		backButton.setBounds(0, 0, 50, 50);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.addMouseListener(controller);
		this.add(backButton);
		
		helpField = new JLabel();
		helpField.setPreferredSize(new Dimension(629,1743));
//		JScrollPane scroll = new JScrollPane(helpField);
//		scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
//		scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scroll.setVisible(true);
//		scroll.setBounds(100, 100,770, 400);
//		this.add(helpField);
		
		ImageIcon help = new ImageIcon( new SubImg(SubImg.HELP,629,1743).getImage(0));
		helpField.setIcon(help);
		
		MyScrollPane myScroll = new MyScrollPane(new ImageIcon("pic/help_bg.png").getImage());
		myScroll.add(helpField);
		myScroll.setBounds(165, 100,647, 444);
		myScroll.getViewport().setOpaque(false);
		myScroll.setViewportView(helpField);
		myScroll.revalidate();
		myScroll.repaint();
		this.add(myScroll);
		
		
	  
		
	}
	
	public BgPanel getParentPanel() {
		return parentPanel;
	}

	@Override
	public void showHelp(ArrayList<String> help) {
		// TODO Auto-generated method stub
//		StringBuilder str = new StringBuilder();
//		for(String s:help){
//			str.append(s+"\r\n");
//		}
//		helpField.setText(str.toString());
//		helpField.setText("<html><img src = \"file:///D:\\JAVA\\IceBreaker\\1.png\"></img></html>");
	}
	
	public void getHelpTips(){
		  controller.getHelpTips();
	}
	
}

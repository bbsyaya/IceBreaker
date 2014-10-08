package team.mosaic.icebreaker.view.main;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import team.mosaic.icebreaker.controller.ToolController;
import team.mosaic.icebreaker.file.SubImg;
import team.mosaic.icebreaker.utility.Number;
import team.mosaic.icebreaker.viewservice.ToolViewService;

/**
 * 道具界面
 * @author acer
 *
 */
public class ToolPanel extends JPanel implements ToolViewService{
	private static final long serialVersionUID = -214279793394996196L;
	
	private ToolController controller;
	private BgPanel parentPanel;
	private ImageIcon tool_C,tool_D,tool_E,tool_F;
	private ImageIcon add_button;
	
	private JLabel name1;
	private JLabel name2;
	private JLabel name3;
	private JLabel name4;
	
	private JLabel titleLabel;
	private JLabel t1,t2,t3,t4;
//	public JLabel n1,n2,n3,n4;
	public JLabel a1,a2,a3,a4;
	private ArrayList<Integer> tool;
	
	private JLabel[] n1 = new JLabel[4];
	private JLabel[] n2 = new JLabel[4];
	private JLabel[] n3 = new JLabel[4];
	private JLabel[] n4 = new JLabel[4];
	
	public JLabel backButton;
	
	public ToolPanel(BgPanel bp){
		parentPanel = bp;
		controller = new ToolController(this);
		init();
	}
	
	private void init(){
		this.setLayout(null);
		this.setOpaque(false);
		this.setBounds(0, 0, 960, 600);
		
		titleLabel = new JLabel(new ImageIcon("pic/my_tools.png"));
		titleLabel.setBounds(355, 30, 250, 83);
		this.add(titleLabel);
		
		backButton = new JLabel(new ImageIcon("pic/back.png"));
		backButton.setBounds(0, 0, 50, 50);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.addMouseListener(controller);
		this.add(backButton);
		
		tool_C = new ImageIcon(SubImg.TOOLC_LARGE);
		t1 = new JLabel(tool_C,JLabel.CENTER);
		t1.setBounds(230,100,128,128);
		this.add(t1);
		
		tool_D = new ImageIcon(SubImg.TOOLD_LARGE);
		t2 = new JLabel(tool_D,JLabel.CENTER);
		t2.setBounds(620,100,128,128);
		this.add(t2);
		
		tool_E = new ImageIcon(SubImg.TOOLE_LARGE);
		t3 = new JLabel(tool_E,JLabel.CENTER);
		t3.setBounds(230, 350, 128, 128);
		this.add(t3);
		
		tool_F = new ImageIcon(SubImg.TOOLF_LARGE);
		t4 = new JLabel(tool_F,JLabel.CENTER);
		t4.setBounds(620,350,128,128);
		this.add(t4);
				
		name1 = new JLabel(new ImageIcon("pic/easy_combo.png"));
		name1.setBounds(220, 230, 150, 37);
		this.add(name1);
		
		name2 = new JLabel(new ImageIcon("pic/score_up.png"));
		name2.setBounds(620, 230, 150, 37);
		this.add(name2);
		
		name3 = new JLabel(new ImageIcon("pic/fast_prompt.png"));
		name3.setBounds(220, 480, 150, 37);
		this.add(name3);
		
		name4 = new JLabel(new ImageIcon("pic/time_back.png"));
		name4.setBounds(620, 480, 150, 37);
		this.add(name4);
		
		tool = new ArrayList<Integer>();
		tool = null;
		
//		Font font = new Font("Times New Roman",1,30);
		
		for(int i = 0;i<n1.length;i++){
			n1[i] = new JLabel();
			n1[i].setBounds(180+i*Number.DIGIT_WIDTH, 280, Number.DIGIT_WIDTH, Number.DIGIT_HEIGHT);
			this.add(n1[i]);
		}
		for(int i = 0;i<n2.length;i++){
			n2[i] = new JLabel();
			n2[i].setBounds(600+i*Number.DIGIT_WIDTH, 280, Number.DIGIT_WIDTH, Number.DIGIT_HEIGHT);
			this.add(n2[i]);
		}
		for(int i = 0;i<n3.length;i++){
			n3[i] = new JLabel();
			n3[i].setBounds(180+i*Number.DIGIT_WIDTH, 530, Number.DIGIT_WIDTH, Number.DIGIT_HEIGHT);
			this.add(n3[i]);
		}
		for(int i = 0;i<n4.length;i++){
			n4[i] = new JLabel();
			n4[i].setBounds(600+i*Number.DIGIT_WIDTH, 530, Number.DIGIT_WIDTH, Number.DIGIT_HEIGHT);
			this.add(n4[i]);
		}
		
//		n1 = new JLabel();
//		n1.setHorizontalAlignment(JLabel.CENTER);
//		n1.setFont(font);
//		n1.setBounds(220,260,100,50);
////		n1.setOpaque(true);
//		this.add(n1);
//		
//		n2 = new JLabel();
//		n2.setHorizontalAlignment(JLabel.CENTER);
//		n2.setFont(font);
//		n2.setBounds(640,260,100,50);
////		n2.setOpaque(true);
//		this.add(n2);
//		
//		n3 = new JLabel();
//		n3.setHorizontalAlignment(JLabel.CENTER);
//		n3.setFont(font);
//		n3.setBounds(220,510,100,50);
////		n3.setOpaque(true);
//		this.add(n3);
//		
//		n4 = new JLabel();
//		n4.setHorizontalAlignment(JLabel.CENTER);
//		n4.setFont(font);
//		n4.setBounds(640,510,100,50);
////		n4.setOpaque(true);
//		this.add(n4);
		
		add_button = new ImageIcon(SubImg.ADD_BUTTON);
		a1 = new JLabel(add_button);
		a1.setBounds(340,260,50,50);
		a1.addMouseListener(controller);
		a1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(a1);
		
		a2 = new JLabel(add_button);
		a2.setBounds(740,260,50,50);
		a2.addMouseListener(controller);
		a2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(a2);
		
		a3 = new JLabel(add_button);
		a3.setBounds(340,510,50,50);
		a3.addMouseListener(controller);
		a3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(a3);
		
		a4 = new JLabel(add_button);
		a4.setBounds(740,510,50,50);
		a4.addMouseListener(controller);
		a4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(a4);
		
	}
	
	public BgPanel getParentPanel() {
		return parentPanel;
	}
	

	@Override
	public void updateToolNumbers(ArrayList<Integer> num) {
		// TODO Auto-generated method stub
//		if(tool!=null && num .equals(tool)){
//			JOptionPane.showMessageDialog(this, "金币不足，无法购买！");	
//		}
//		else{			
//	    	n1.setText(num.get(0).toString());
//	    	n2.setText(num.get(1).toString());
//	    	n3.setText(num.get(2).toString());
//	    	n4.setText(num.get(3).toString());
		Number.setNumber(n1, num.get(0));
		Number.setNumber(n2, num.get(1));
		Number.setNumber(n3, num.get(2));
		Number.setNumber(n4, num.get(3));
	    	tool = num;
//		}
		
	}
	
	public void getToolNumber(){
		controller.getToolNumber();
	}

	@Override
	public void updateToolNumbers(ArrayList<Integer> num, boolean b) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "金币不足，无法购买！");
	}
	
}

package team.mosaic.icebreaker.view.game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import team.mosaic.icebreaker.controller.ToolController;

public class ToolPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6937612557831200851L;
	private GamePanel parentPanel;
	private static int toolNum=4;
	private boolean[] tool=new boolean[toolNum];
	private JLabel[] tools=new JLabel[toolNum];
	
	private static final ImageIcon C = new ImageIcon("pic/toolC.png");
	private static final ImageIcon D = new ImageIcon("pic/toolD.png");
	private static final ImageIcon E = new ImageIcon("pic/toolE.png");
	private static final ImageIcon F = new ImageIcon("pic/toolF.png");
	
	
	public ToolPanel(GamePanel gp) {
		parentPanel=gp;
		tool=gp.getTools();
		
		tools[0] = new JLabel(C);
		tools[1] = new JLabel(D);
		tools[2] = new JLabel(E);
		tools[3] = new JLabel(F);
		
		//初始化道具图标
		for(int i=0;i<toolNum;i++){
			if(!tool[i]){
				tools[i].setEnabled(false);
			}
		}
		init();
	}
	
	public void init(){
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(230, 50);
		//初始化道具图标位置
		for(int i=0;i<toolNum;i++){
			tools[i].setBounds(60*i, 0, 50, 50);
			this.add(tools[i]);
		}
	}
	
	public void setTool(boolean[] tools){
		this.tool = tools;
		for(int i = 0;i<toolNum;i++){
			if(tools[i])
				this.tools[i].setEnabled(true);
			else
				this.tools[i].setEnabled(false);
		}
	}
	
	public void lockC(){
		tools[0].setEnabled(false);
		this.repaint();
	}
	
	public void unlockC(){
		if(tool[0])
			tools[0].setEnabled(true);
		this.repaint();
	}

}

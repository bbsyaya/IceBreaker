package team.mosaic.icebreaker.view.main;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import team.mosaic.icebreaker.file.SubImg;

/**
 * ±≥æ∞ΩÁ√Ê
 * @author acer
 *
 */
public class BgPanel extends JPanel{

	private static final long serialVersionUID = 4211345218861859531L;
	
	private MainFrame parentFrame;
	private JPanel currentPanel;
	private FirstPanel firstPanel;
	private InfoPanel infoPanel;
	private ToolPanel toolPanel;
	private SetPanel setPanel;
	private HelpPanel helpPanel;
	private ReadyPanel readyPanel;
	
	private boolean onLine;
	
	public BgPanel(MainFrame f,boolean ol){
		this.setLayout(null);
		
		onLine = ol;
		parentFrame = f;
		firstPanel = new FirstPanel(this,onLine);
		infoPanel = new InfoPanel(this);
		toolPanel = new ToolPanel(this);
		setPanel = new SetPanel(this);
		helpPanel = new HelpPanel(this);
		readyPanel = new ReadyPanel(this);
		currentPanel = firstPanel;
		this.add(firstPanel);
	}
	
	public FirstPanel getFirstPanel(){
		return firstPanel;
	}
	
	public InfoPanel getInfoPanel(){
		return infoPanel;
	}
	
	public ToolPanel getToolPanel(){
		return toolPanel;
	}
	
	public SetPanel getSetPanel(){
		return setPanel;
	}
	
	public HelpPanel getHelpPanel(){
		return helpPanel;
	}
	
	public ReadyPanel getReadyPanel(){
		return readyPanel;
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
	
	public MainFrame getParentFrame(){
		return parentFrame;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon img = new ImageIcon(SubImg.BACKGROUND);
		g.drawImage(img.getImage(), 0, 0, this);
	}
	
}

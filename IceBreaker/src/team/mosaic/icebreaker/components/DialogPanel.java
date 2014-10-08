package team.mosaic.icebreaker.components;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DialogPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DialogPanel(){
		super();
		this.setSize(960, 220);
		this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon img = new ImageIcon("pic/dialog.png");
		g.drawImage(img.getImage(), 0, 0, this);
	}
	
}

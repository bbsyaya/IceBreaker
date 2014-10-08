package team.mosaic.icebreaker.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JScrollPane;

public class MyScrollPane extends JScrollPane {

	Image bg;
	public MyScrollPane(Image bg){
		super();
		this.bg = bg;
	}
	
	public void paintComponent(Graphics g){
//		super.paintComponent(g);
		if(bg != null){
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(bg, 0, 0, this);
		}
	}
	
}

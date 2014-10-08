package team.mosaic.icebreaker.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Arc2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class OvalButton extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 467621499439632572L;
	
	private Arc2D arc;
	private Image ii;
	
	
	public OvalButton(){
		super();
//		this.ii = (Image)ii;
		init();
	}

	private void init(){
		arc = new Arc2D.Float(Arc2D.PIE);
		arc.setAngleStart(0);
		arc.setAngleExtent(360);
	}
	
	public void paintComponent(Graphics g) {
		Color old = g.getColor();
		g.setColor(Color.magenta);
		g.fillArc(0, 0, getWidth(), getHeight(), (int)arc.getAngleStart(), (int)arc.getAngleExtent());
		g.setColor(old);
	}
	
	public boolean contains(int x,int y){
		return arc.contains(x, y);
	}
}

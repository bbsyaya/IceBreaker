package team.mosaic.icebreaker.utility;

import java.awt.Component;

public class Resize {
	
	public static void setBigger(Component c,int range){
		c.setBounds(c.getX()-range/2, c.getY()-range/2, c.getWidth()+range, c.getHeight()+range);
	}
	
	public static void setSmaller(Component c,int range){
		c.setBounds(c.getX()+range/2, c.getY()+range/2, c.getWidth()-range, c.getHeight()-range);
	}
	
}

package team.mosaic.icebreaker.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import team.mosaic.icebreaker.file.SubImg;
import team.mosaic.icebreaker.view.game.BoardPanel;

public class Grid extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 470988461546350093L;
	private static final SubImg ICONS = new SubImg(SubImg.GRID, 50, 50);//普通图标
	private static final SubImg TOOL_ICONS = new SubImg(SubImg.TOOL_A,50,50);//道具A图标
	private static final ImageIcon SUPER_ICON = new ImageIcon(SubImg.TOOL_B);//道具B
	private static final SubImg SHINY_ICONS = new SubImg(SubImg.SHINY_GRID,50,50);
	private static final SubImg SHINY_TOOLS = new SubImg(SubImg.SHINY_TOOL_A, 50, 50);
	private static final ImageIcon SHINY_B = new ImageIcon(SubImg.SHINY_B);
	
	private boolean isMoving;
	private int color;
	
	public Grid() {
		super();
		this.setSize(BoardPanel.GRID_LENGTH, BoardPanel.GRID_LENGTH);
		this.setOpaque(false);
		this.isMoving = false;
	}
	
	public void setIcon(int i){//i from -6 to 7
		this.color = i;
		if(i >= 1 && i <= 6)
			this.setIcon(new ImageIcon(ICONS.getHorImage(i-1)));
		else if(i >= -6 && i <= -1)
			this.setIcon(new ImageIcon(TOOL_ICONS.getHorImage(-i-1)));
		else
			this.setIcon(SUPER_ICON);
		this.repaint();
	}
	
	public void lighten(){
		if(color >= 1 && color <= 6)
			this.setIcon(new ImageIcon(SHINY_ICONS.getHorImage(color-1)));
		else if(color >= -6 && color <= -1)
			this.setIcon(new ImageIcon(SHINY_TOOLS.getHorImage(-color-1)));
		else
			this.setIcon(SHINY_B);
		this.repaint();
	}
	
	public void normalize(){
		this.setIcon(color);
	}
	
	public boolean getMovement(){
		return isMoving;
	}
	
	public void setMovement(boolean m){
		this.isMoving = m;
	}

}

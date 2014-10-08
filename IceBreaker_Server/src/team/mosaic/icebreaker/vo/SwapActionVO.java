package team.mosaic.icebreaker.vo;

import java.io.Serializable;

public class SwapActionVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5733228470694635913L;
	private PositionVO p1,p2;
	private int mark;
	
	public SwapActionVO(PositionVO p1,PositionVO p2,int mark){
		this.p1 = p1;
		this.p2 = p2;
		this.mark = mark;
	}
	
	public PositionVO getPosition1(){
		return p1;
	}
	
	public PositionVO getPosition2(){
		return p2;
	}
	
	public int getMark(){
		return mark;
	}
	
}

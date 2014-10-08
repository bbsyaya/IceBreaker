package team.mosaic.icebreaker.vo;

import java.io.Serializable;

public class PositionVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//网格中的位置 x,y都从0开始
	//网格外的位置？？
	private int x;
	private int y;
	
	public PositionVO(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean adjacent(PositionVO another){
		int x2 = another.getX();
		int y2 = another.getY();
		int tmp = Math.abs(x-x2)+Math.abs(y-y2);
		return (tmp==1);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isValid(PositionVO v2){
		if(((this.getX()-v2.getX()==0)&&Math.abs(this.getY()-v2.getY())==1)||((this.getY()-v2.getY()==0)&&Math.abs(this.getX()-v2.getX())==1))
			return true;
		else
			return false;
	}
}

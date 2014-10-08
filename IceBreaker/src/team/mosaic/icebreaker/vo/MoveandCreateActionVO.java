package team.mosaic.icebreaker.vo;

import java.io.Serializable;

public class MoveandCreateActionVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//0代表是盘面已有的方块进行移动，1代表是新方块生成
	private int type;
	
	//方块移动的起始位置，如果是生成新方块，位置是动画效果中的界外的位置
	private PositionVO gridOld;
	
	//颜色，仅适用于生成新方块
	private int color;
	
	//移动方向，0为向下，1为向右
	private int direction;
	
	//移动的格数
	private int steps;
	
	//moveaction的构造
	public MoveandCreateActionVO(int type,PositionVO gridOld,int direction,int steps) throws Exception{
		if(type!=0){
			throw new Exception("类型与构造函数不匹配");
		}
		this.type = 0;
		this.gridOld = gridOld;
		this.direction = direction;
		this.steps = steps;
		
	}
	
	//createaction的构造
	public MoveandCreateActionVO(int type,int color,PositionVO gridOld,int direction,int steps) throws Exception{
		if(type!=1){
			throw new Exception("类型与构造函数不匹配");
		}
		this.type = 1;
		this.color = color;
		this.gridOld = gridOld;
		this.direction = direction;
		this.steps = steps;
	}
	
	public int type(){
		return type;
	}
	
	public int color() throws Exception{
		if(type!=1){
			throw new Exception("类型与函数不匹配");
		}
		return color;
	}

	public PositionVO gridOld(){
		return gridOld;
	}
	
	public int direction(){
		return direction;
	}

	public int steps(){
		return steps;
	}
}

package team.mosaic.icebreaker.vo;

import java.io.Serializable;

public class ResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3218488234729294292L;
	private boolean isRecordBroken;
	private boolean isLevelUp;
	private int score;//对方分数,-2表示非pk
	
	public ResultVO(boolean isRecordBroken,boolean isLevelUp){
		this(isRecordBroken, isLevelUp, -2);
	}
	
	public ResultVO(boolean isRecordBroken,boolean isLevelUp,int score){
		this.isRecordBroken = isRecordBroken;
		this.isLevelUp = isLevelUp;
		this.score = score;
	}
	
	public boolean getRecordBroken(){
		return isRecordBroken;
	}
	
	public boolean getLevelUp(){
		return isLevelUp;
	}
	
	public int getScore(){
		return this.score;
	}
	
}

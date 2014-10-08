package team.mosaic.icebreaker.vo;

import java.io.Serializable;

public class ScoreVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7083295618997381308L;
	private String id;
	private int score;
	
	public ScoreVO(String id,int score){
		this.id = id;
		this.score = score;
	}
	
	public String getID(){
		return id;
	}
	
	public int getScore(){
		return score;
	}
	
}

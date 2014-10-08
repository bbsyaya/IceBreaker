package team.mosaic.icebreaker.vo;

import java.io.Serializable;

public class GainVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2650424778583391934L;
	private int score;
	private int exp;
	private int coin;
	
	public GainVO(int s,int e,int c){
		score = s;
		exp = e;
		coin = c;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getExp(){
		return exp;
	}
	
	public int getCoin(){
		return coin;
	}
	
}

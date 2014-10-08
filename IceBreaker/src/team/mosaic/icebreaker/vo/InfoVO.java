package team.mosaic.icebreaker.vo;

import java.io.Serializable;

public class InfoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1525398331705977472L;
	private String id;//id
	private int level;//等级
	private int exp;//经验
	private int best;//最好成绩
	private int coin;//金币
	
	public InfoVO(String id,int level,int exp,int best,int coin){
		this.id = id;
		this.level = level;
		this.exp = exp;
		this.best = best;
		this.coin = coin;
	}
	
	public String getID(){
		return id;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getExp(){
		return exp;
	}
	
	public int getBest(){
		return best;
	}
	
	public int getCoin(){
		return coin;
	}
	
}

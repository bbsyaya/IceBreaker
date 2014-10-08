package team.mosaic.icebreaker.vo;

import java.io.Serializable;

public class InfoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1525398331705977472L;
	private String id;//id
	private int level;//�ȼ�
	private int exp;//����
	private int best;//��óɼ�
	private int coin;//���
	
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

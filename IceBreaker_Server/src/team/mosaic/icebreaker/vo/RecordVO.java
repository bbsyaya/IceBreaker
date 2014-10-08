package team.mosaic.icebreaker.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordVO	implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8762605936736210218L;
	private int score;
	private int maxCombo;
	private String date;
	
	public RecordVO(int score,int maxCombo){
		this.score = score;
		this.maxCombo = maxCombo;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		date = df.format(new Date()).toString();// new Date()Ϊ��ȡ��ǰϵͳʱ��
	}
	
	public RecordVO(int score,int maxCombo,String date){
		this.score = score;
		this.maxCombo = maxCombo;
		this.date = date;
	}
	public int getScore(){
		return score;
	}
	
	public int getMaxCombo(){
		return maxCombo;
	}
	
	public String getDate(){
		return date;
	}
	
	
}

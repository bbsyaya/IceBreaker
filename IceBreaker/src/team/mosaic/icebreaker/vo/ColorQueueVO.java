package team.mosaic.icebreaker.vo;

import java.io.Serializable;
import java.util.Random;

public class ColorQueueVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3021607062916927788L;
	private int[] list = new int[500];
	private int pointer;
	public ColorQueueVO(){
		for(int n = 0;n<500;n++){
			list[n] = new Random().nextInt(6)+1;
		}
		pointer = 0;
	}
	
	public int next(){
		int color = list[pointer];
		pointer = (pointer+1)%500;
		return color;
	}
}
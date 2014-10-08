package team.mosaic.icebreaker.vo;

import java.io.Serializable;

public class ToolVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5314183648771894371L;
	private String tool_id;//����id
	private int num;//��������
	private int price;//���߼۸�
	
	public ToolVO(String tool_id,int num,int price){
		this.tool_id = tool_id;
		this.num = num;
		this.price = price;
	}
	
	public String getToolID(){
		return tool_id;
	}

	public int getNum(){
		return num;
	}
	
	public int getPrice(){
		return price;
	}
	
}

package team.mosaic.icebreaker.vo;

import java.io.Serializable;

public class MessageVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3251310226508238364L;
	private String cmd;
	private Object obj;
	
	public MessageVO(String cmd,Object obj){
		this.cmd = cmd;
		this.obj = obj;
	}
	
	public String getCommand(){
		return cmd;
	}
	
	public Object getObject(){
		return obj;
	}
	
}

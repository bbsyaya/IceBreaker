package team.mosaic.icebreaker.vo;

import java.io.Serializable;

public class AccountVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8904862036172036545L;
	private String id;
	private String pwd;
	
	public AccountVO(String id,String pwd){
		this.id = id;
		this.pwd = pwd;
	}
	
	public String getID(){
		return id;
	}
	
	public String getPassword(){
		return pwd;
	}
	
}

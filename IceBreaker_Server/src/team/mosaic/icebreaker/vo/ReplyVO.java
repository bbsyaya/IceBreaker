package team.mosaic.icebreaker.vo;

import java.io.Serializable;

public class ReplyVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6230384551054621879L;
	private boolean isAgree;// �Ƿ�ͬ�����Э����pk
	private String palID;// �Է�id

	public ReplyVO(boolean i, String p) {
		isAgree = i;
		palID = p;
	}

	public boolean getAgreement() {
		return isAgree;
	}

	public String getPalID() {
		return palID;
	}

}

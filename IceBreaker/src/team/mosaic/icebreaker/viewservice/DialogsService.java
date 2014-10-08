package team.mosaic.icebreaker.viewservice;

import java.awt.Frame;

public interface DialogsService {
	/**
	 * 绘制好友申请提示框
	 * 
	 * @param String 申请者id
	 */
	public void friendApply(String id,Frame owner);
}

package team.mosaic.icebreaker.viewservice;

import java.util.ArrayList;

public interface LoginViewService {

	/**
	 * 登陆界面
	 * @param isVerified 账号有效性验证
	 */
	public void dealWithLogin(boolean isVerified);
	
}

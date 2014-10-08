package team.mosaic.icebreaker.viewservice;

import java.util.ArrayList;

/**
 * 个人信息界面接口
 * @author jbj
 *
 */
public interface PersonalInfoViewService {
	
	/**
	 * 刷新个人信息
	 * @param roles
	 */
	public void refreshRole(ArrayList<String> roles);
	
	/**
	 * 刷新联机游戏个人数据
	 * @param netPInfo
	 */
	public void refreshNetPInfo(ArrayList<String> netPInfo);
	
	/**
	 * 刷新本地游戏个人数据
	 * @param localInfo
	 */
	public void refreshLocalPInfo(ArrayList<String> localInfo);
	
	
	
	
	

}

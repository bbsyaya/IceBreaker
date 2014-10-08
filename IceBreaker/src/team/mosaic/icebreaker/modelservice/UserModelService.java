package team.mosaic.icebreaker.modelservice;

import java.util.ArrayList;

import team.mosaic.icebreaker.vo.AccountVO;

public interface UserModelService {
	/**
	 * 登录游戏
	 * @param AccountVO
	 */
	public void login(AccountVO avo);
	/**
	 * 返回登录结果
	 * @param boolean
	 */
	public void responseLogin(boolean isVerified);
	/**
	 * 退出游戏
	 * @param
	 */
	public void signOut();
	/**
	 * 设置是否记住密码
	 * @param r 是否记住密码
	 */
	public void setRemember(boolean r);
	
	public void setAccount(AccountVO avo);
	
	public ArrayList<String> getIdList();
	
	public ArrayList<String> getPwdList();
	
	public ArrayList<Integer> getHpList();
	
	public AccountVO getAccount();
	
}

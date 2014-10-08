package team.mosaic.icebreaker.modelservice;

import java.util.ArrayList;

import team.mosaic.icebreaker.vo.AccountVO;

public interface UserModelService {
	/**
	 * ��¼��Ϸ
	 * @param AccountVO
	 */
	public void login(AccountVO avo);
	/**
	 * ���ص�¼���
	 * @param boolean
	 */
	public void responseLogin(boolean isVerified);
	/**
	 * �˳���Ϸ
	 * @param
	 */
	public void signOut();
	/**
	 * �����Ƿ��ס����
	 * @param r �Ƿ��ס����
	 */
	public void setRemember(boolean r);
	
	public void setAccount(AccountVO avo);
	
	public ArrayList<String> getIdList();
	
	public ArrayList<String> getPwdList();
	
	public ArrayList<Integer> getHpList();
	
	public AccountVO getAccount();
	
}

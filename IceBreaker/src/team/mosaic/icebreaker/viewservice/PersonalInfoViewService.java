package team.mosaic.icebreaker.viewservice;

import java.util.ArrayList;

/**
 * ������Ϣ����ӿ�
 * @author jbj
 *
 */
public interface PersonalInfoViewService {
	
	/**
	 * ˢ�¸�����Ϣ
	 * @param roles
	 */
	public void refreshRole(ArrayList<String> roles);
	
	/**
	 * ˢ��������Ϸ��������
	 * @param netPInfo
	 */
	public void refreshNetPInfo(ArrayList<String> netPInfo);
	
	/**
	 * ˢ�±�����Ϸ��������
	 * @param localInfo
	 */
	public void refreshLocalPInfo(ArrayList<String> localInfo);
	
	
	
	
	

}

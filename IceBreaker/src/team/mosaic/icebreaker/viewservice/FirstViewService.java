package team.mosaic.icebreaker.viewservice;

import java.util.ArrayList;

/**
 * ������ӿ�
 */

public interface FirstViewService {

	/**
	 * ˢ�º������а�
	 * 
	 * @param friends
	 */
	public void refreshFriendsRank(ArrayList<String> friends);

	/**
	 * ˢ��ȫ�����а�
	 * 
	 * @param allplayers
	 */
	public void refreshAllRank(ArrayList<String> allplayers);
	/**
	 * ����������ʾ��
	 * 
	 * @param allplayers
	 */
	public void friendApply(String id);

}

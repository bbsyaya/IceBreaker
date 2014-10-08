package team.mosaic.icebreaker.modelservice;

import java.util.ArrayList;

import team.mosaic.icebreaker.vo.ScoreVO;

public interface MenuModelService {
	/**
	 * ��������
	 * @param
	 */
	 public void friendRank();
	 /**
	  * ��������
	  * @param
	  */
	 public void worldRank();
	 /**
	  * չʾ�������а�
	  * @param
	  */
	 public void showFriendRank(ArrayList<ScoreVO> rank);
	 /**
	  * ��ʾ��������
	  * @param
	  */
	 public void showWorldRank(ArrayList<ScoreVO> rank);
	 /**
	  * ��Ӻ���
	  * @param String ����id
	  */
	 public void addFriend(String id);
	 /**
	  * ��������
	  * @param String ������id
	  */
	 public void friendApply(String id);
	 /**
	  * ������ѵĽ��
	  * @param
	  */
	 public void resultOfFriendApply(String id,boolean b);
	 
	 public void notFoundPlayer();
	
}

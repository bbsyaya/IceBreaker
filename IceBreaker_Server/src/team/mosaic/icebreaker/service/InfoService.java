package team.mosaic.icebreaker.service;

import java.util.ArrayList;

import team.mosaic.icebreaker.vo.InfoVO;
import team.mosaic.icebreaker.vo.RecordVO;
import team.mosaic.icebreaker.vo.ScoreVO;
import team.mosaic.icebreaker.vo.ToolVO;

public interface InfoService {
	/**
	 * �õ���Ҹ�����Ϣ
	 * @param id ���id
	 * @return ��Ҹ�����Ϣ
	 */
	public InfoVO getInfo(String id);
	/**
	 * �õ���ҵĺ����б�
	 * @param id ���id
	 * @return �����б�
	 */
	public ArrayList<String> getMyFriends(String id);
	/**
	 * �õ���ҵĺ������а�
	 * @param id ���id
	 * @return ���а�id�Ͷ�Ӧ�ɼ���
	 */
	public ArrayList<ScoreVO> getFriendRank(String id);
	/**
	 * �õ��������а�
	 * @return ���а�id�Ͷ�Ӧ�ɼ���
	 */
	public ArrayList<ScoreVO> getWorldRank();
	/**
	 * �õ��ҵĵ���
	 * @param ���id
	 * @return �ҵĵ���
	 */
	public ArrayList<ToolVO> getMyTools(String id);
	/**
	 * ��Ӻ���
	 * @param id ����id
	 */
	public void addFriend(String id1,String id2);
	/**
	 * ��ѯ��Ϸ��¼
	 * @param id ����id
	 */
	public ArrayList<RecordVO> queryRecord(String id);
	/**
	 * �ύ��Ϸ��¼
	 * @param id ����id
	 */
	public void updateRecord(String id,RecordVO rvo);
	
}

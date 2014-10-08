package team.mosaic.icebreaker.modelservice;

import java.util.ArrayList;

import team.mosaic.icebreaker.vo.ColorQueueVO;
import team.mosaic.icebreaker.vo.InfoVO;
import team.mosaic.icebreaker.vo.RecordVO;
import team.mosaic.icebreaker.vo.ReplyVO;
import team.mosaic.icebreaker.vo.ResultVO;
import team.mosaic.icebreaker.vo.ScoreVO;
import team.mosaic.icebreaker.vo.SwapActionVO;
import team.mosaic.icebreaker.vo.ToolVO;

public interface ClientService {
	/**
	 * ���ص�½��֤���
	 * @param isVerified ��֤���
	 */
	public void login(boolean isVerified);
	/**
	 * ����ע����֤���
	 * @param isVerified ��֤���
	 */
	public void register(boolean isVerified);
	/**
	 * ���ظ�����Ϣ
	 * @param ivo ������Ϣ
	 */
	public void myInfo(InfoVO ivo);
	/**
	 * �����ҵĵ���
	 * @param tools ����(id,����)
	 */
	public void myTools(ArrayList<ToolVO> tools);
	/**
	 * ������߽��
	 * @param b ���
	 */
	public void buyToolResult(boolean b);
	/**
	 * ���غ������а�
	 * @param rank �������а�������
	 */
	public void firendRank(ArrayList<ScoreVO> rank);
	/**
	 * �����������а�
	 * @param rank �������а�������
	 */
	public void worldRank(ArrayList<ScoreVO> rank);
	/**
	 * �������ߺ���
	 * @param friends ���ߺ���
	 */
	public void onlineFriends(ArrayList<String> friends);
	/**
	 * �յ�Э������
	 * @param id ������id
	 */
	public void coopInvited(String id);
	/**
	 * �յ�pk����
	 * @param id ������id
	 */
	public void pkInvited(String id);
	/**
	 * �Է���Ӧ����������ͬ�⣬ͬ��Ļ���ֱ�ӿ�ʼ��Ϸ��
	 * @param rvo �𸴽��������id
	 */
	public void invitationResult(ReplyVO rvo);
	/**
	 * ����Ϊ����
	 */
	public void setHost();
	/**
	 * ͬ������
	 * @param action �ƶ��������������·���
	 */
	public void act(SwapActionVO svo);
	/**
	 * ����������
	 * @param b ���
	 */
	public void buyCharacterResult(boolean b);
	/**
	 * ��ɫ��Ϣ
	 * @param CharacterVO[] 
	 */
	public void myCharacters(boolean[] c);
	/**
	 * ����Ӻ���
	 * @param String ������id 
	 */
	public void friendsApply(String id);
	/**
	 * ����������
	 * @param String ������id 
	 */
	public void resultOffriendsApply(ReplyVO rvo);

	/**
	 * �յ���ʼ��������
	 * @param qvo ��ʼ���Ķ���
	 */
	public void sendQueue(ColorQueueVO qvo);
	/**
	 * ��ʾ��Ϸ���
	 * @param rvo ��Ϸ���
	 */
	public void showResult(ResultVO rvo);
	/**
	 * pkģʽ������ĳһ�ַ���
	 */
	public void randomLock();
	/**
	 * pkģʽ�µ���CʧЧ
	 */
	public void lockC();
	/**
	 * �����ǰ�˳�����Ϸ
	 * @param id ���id
	 */
	public void someoneQuit(String id);
	
	public void findCoopPair(String id);
	
	public void findCoopFour(String[] id);
	
	public void findPKFour(String[] id);

	/**
	 * ��ʼ˫��Э��
	 */
	public void startTwosome();
	/**
	 * ��ʼ����Э��
	 */
	public void startFoursome();
	/**
	 * ��ʼpk
	 */
	public void startPk(final String id);
	/**
	 * �ӷ�����������¼����
	 */
	public void sendRecords(ArrayList<RecordVO> records);
	
	public void notFoundPlayer();
}

package team.mosaic.icebreaker.service;

import team.mosaic.icebreaker.vo.GainVO;
import team.mosaic.icebreaker.vo.ResultVO;

public interface GameService {
	/**
	 * �ύ������Ϸ����
	 * @param id ���id
	 * @param gvo ��Ϸ��ã������÷֣����飬��ң�
	 * @param mode ��Ϸģʽ
	 * @return �Ƿ�ˢ�¼�¼
	 */
	public ResultVO submitScore(String id,GainVO gvo,int mode);
	/**
	 * �������
	 * @param id ���id
	 * @param tool_id ����id
	 * @return �Ƿ���ɹ�
	 */
	public boolean buyTool(String id,String tool_id);
	/**
	 * ʹ�õ���
	 * @param id ���id
	 * @param tool_id ����id
	 */
	public void useTool(String id,String tool_id);
	/**
	 * ��������
	 * @param id ���id
	 * @param char_id ����id
	 * @return �Ƿ���ɹ�
	 */
	public boolean buyCharacter(String id,String char_id);
	/**
	 * �õ�����������
	 * @param id ���id
	 * @return �������
	 */
	public boolean[] getCharacter(String id);
	
}

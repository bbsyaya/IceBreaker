package team.mosaic.icebreaker.service;

import team.mosaic.icebreaker.vo.AccountVO;

public interface AccountService {
	/**
	 * ��֤��½��Ϣ
	 * @param avo ��½��Ϣ
	 * @return �Ƿ��½�ɹ�
	 */
	public boolean verify(AccountVO avo);
	/**
	 * ע��
	 * @param avo ע����Ϣ
	 * @return �Ƿ�ע��ɹ�
	 */
	public boolean register(AccountVO avo);
}

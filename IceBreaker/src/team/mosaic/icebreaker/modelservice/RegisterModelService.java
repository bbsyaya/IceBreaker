package team.mosaic.icebreaker.modelservice;

import team.mosaic.icebreaker.vo.AccountVO;

public interface RegisterModelService {
	/**
	 * ע���˺�
	 * @param AccountVO
	 */
	public void Register(AccountVO av);
	/**
	 * չʾע���˺Ž��
	 * @param boolean
	 */
	public void showResult(boolean b);
}

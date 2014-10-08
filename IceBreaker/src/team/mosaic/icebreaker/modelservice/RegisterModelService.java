package team.mosaic.icebreaker.modelservice;

import team.mosaic.icebreaker.vo.AccountVO;

public interface RegisterModelService {
	/**
	 * 注册账号
	 * @param AccountVO
	 */
	public void Register(AccountVO av);
	/**
	 * 展示注册账号结果
	 * @param boolean
	 */
	public void showResult(boolean b);
}

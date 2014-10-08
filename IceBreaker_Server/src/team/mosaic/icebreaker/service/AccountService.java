package team.mosaic.icebreaker.service;

import team.mosaic.icebreaker.vo.AccountVO;

public interface AccountService {
	/**
	 * 验证登陆信息
	 * @param avo 登陆信息
	 * @return 是否登陆成功
	 */
	public boolean verify(AccountVO avo);
	/**
	 * 注册
	 * @param avo 注册信息
	 * @return 是否注册成功
	 */
	public boolean register(AccountVO avo);
}

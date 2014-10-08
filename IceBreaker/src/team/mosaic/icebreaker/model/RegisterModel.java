package team.mosaic.icebreaker.model;

import javax.swing.JOptionPane;

import team.mosaic.icebreaker.modelservice.RegisterModelService;
import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.view.login.RegisterPanel;
import team.mosaic.icebreaker.vo.AccountVO;

/**
 * 注册登录模型类
 * @author acer
 *
 */
public class RegisterModel implements RegisterModelService {
	
	private static RegisterPanel rp;
	
	public RegisterModel(){};
	
	public RegisterModel(RegisterPanel rp){
		this.rp = rp;
		ClientModel.setRegisterModel(this);
	}
	
	/**
	 * 注册
	 */
	@Override
	public void Register(AccountVO av) {
		// TODO Auto-generated method stub
		NetService.register(av);
	}

	@Override
	public void showResult(boolean b) {
		// TODO Auto-generated method stub		
		if(b)
			JOptionPane.showMessageDialog(rp.getParentFrame(), "注册成功");
		else {
			JOptionPane.showMessageDialog(rp.getParentFrame(), "用户名已存在");
		}
	}

}

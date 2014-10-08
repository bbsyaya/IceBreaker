package team.mosaic.icebreaker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import team.mosaic.icebreaker.model.ClientModel;
import team.mosaic.icebreaker.utility.Loading;
import team.mosaic.icebreaker.utility.PanelChanger;
import team.mosaic.icebreaker.view.login.LoginFrame;
import team.mosaic.icebreaker.view.login.LoginPanel;
import team.mosaic.icebreaker.vo.AccountVO;

/*
 * 登陆界面控制器
 */
public class LoginController implements ActionListener, KeyListener {
	private LoginPanel lp;
	private LoginFrame lf;

	public LoginController(LoginPanel p) {
		lp = p;
		lf = lp.getParentFrame();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == lp.btnRegister) {
			PanelChanger.slideLeft(lp, lf.getRegisterPanel(), lf);
			lf.getRegisterPanel().pwdField.setText("");
			lf.getRegisterPanel().pwdField2.setText("");
			lf.getRegisterPanel().textField.setText("");
			//lf.modifyPanel(lf.getRegisterPanel());

		} else if (arg0.getSource() == lp.btnLogin) {
			signin();
		} else if (arg0.getSource() ==lp.btnOffline) {
			lp.enter(false);
		} else if(arg0.getSource() == lp.chkRememberPwd){
			lp.getUserModel().setRemember(lp.chkRememberPwd.isSelected());
		}
		
		
		//找回密码
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			signin();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	/**
	 * 向服务器发送登陆请求
	 */
	private void signin() {
		AccountVO avo = new AccountVO(lp.jtfAccount
				.getSelectedItem().toString(), String.valueOf(lp.pwdfPassword
						.getPassword()));
		lp.getUserModel().setAccount(avo);
		lp.getUserModel().setRemember(lp.chkRememberPwd.isSelected());
		new ClientModel();//启动网络服务
		Loading.addLoading();
//		lf.setVisible(false);
//		lf.sysTray.remove(lf.trayIcon);
		lp.getUserModel().login(avo);
	}
	
	/**
	 * 自动登录时的登录请求
	 * @param user
	 * @param pwd
	 */
	
	public void autoSignIn(String user,String pwd){
		Loading.addLoading();
		lf.setVisible(false);
//		lf.sysTray.remove(lf.trayIcon);
		lp.getUserModel().login(new AccountVO(user, pwd));
	}

}

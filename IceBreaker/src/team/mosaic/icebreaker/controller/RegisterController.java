package team.mosaic.icebreaker.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import team.mosaic.icebreaker.model.ClientModel;
import team.mosaic.icebreaker.model.RegisterModel;
import team.mosaic.icebreaker.modelservice.RegisterModelService;
import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.utility.PanelChanger;
import team.mosaic.icebreaker.view.login.LoginFrame;
import team.mosaic.icebreaker.view.login.RegisterPanel;
import team.mosaic.icebreaker.vo.AccountVO;

/*
 * 注销界面控制器
 */
public class RegisterController implements MouseListener {
	private RegisterPanel rp;
	private LoginFrame lf;
	private RegisterModelService rms;

	public RegisterController(RegisterPanel p) {
		rp = p;
		lf = rp.getParentFrame();
		rms = new RegisterModel(rp);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == rp.backButton) {
//			lf.modifyPanel(lf.getLoginPanel());
			PanelChanger.slideRight(rp, lf.getLoginPanel(), lf);
		}
		
		if(e.getSource()==rp.registerButton){
			new ClientModel();
			String id = rp.textField.getText();
			String pwd = String.valueOf(rp.pwdField.getPassword());
			String pwd2 = String.valueOf(rp.pwdField2.getPassword());
			if(id.equals("") || pwd.equals("") || pwd2.equals(""))
				JOptionPane.showMessageDialog(lf, "没填完！");
			else if(id.length()>12||pwd.length()>18){
				JOptionPane.showMessageDialog(lf, "用户名或密码长度过长");
			}
			else if(!pwd.equals(pwd2))
				JOptionPane.showMessageDialog(lf, "两次密码不一样啊！");
			else if(!isString(id)||!isString(pwd)){
				JOptionPane.showMessageDialog(lf, "用户名或密码格式不合法！");
			}
			else {
				rms.Register(new AccountVO(id, pwd));
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public Boolean isString(String str){
		  
		  Boolean bl = false;
		  
		  //首先,使用Pattern解释要使用的正则表达式，其中^表是字符串的开始，$表示字符串的结尾。
		  Pattern pt = Pattern.compile("^[0-9a-zA-Z_]+$");
		 
		  //然后使用Matcher来对比目标字符串与上面解释得结果
		  Matcher mt = pt.matcher(str);
		  
		  //如果能够匹配则返回true。实际上还有一种方法mt.find()，某些时候，可能不是比对单一的一个字符串，
		  //可能是一组，那如果只要求其中一个字符串符合要求就可以用find方法了.
		  if(mt.matches()){
		   
		   bl = true;
		  }
		  return bl;
	}
}

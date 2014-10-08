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
 * ע�����������
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
				JOptionPane.showMessageDialog(lf, "û���꣡");
			else if(id.length()>12||pwd.length()>18){
				JOptionPane.showMessageDialog(lf, "�û��������볤�ȹ���");
			}
			else if(!pwd.equals(pwd2))
				JOptionPane.showMessageDialog(lf, "�������벻һ������");
			else if(!isString(id)||!isString(pwd)){
				JOptionPane.showMessageDialog(lf, "�û����������ʽ���Ϸ���");
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
		  
		  //����,ʹ��Pattern����Ҫʹ�õ�������ʽ������^�����ַ����Ŀ�ʼ��$��ʾ�ַ����Ľ�β��
		  Pattern pt = Pattern.compile("^[0-9a-zA-Z_]+$");
		 
		  //Ȼ��ʹ��Matcher���Ա�Ŀ���ַ�����������͵ý��
		  Matcher mt = pt.matcher(str);
		  
		  //����ܹ�ƥ���򷵻�true��ʵ���ϻ���һ�ַ���mt.find()��ĳЩʱ�򣬿��ܲ��ǱȶԵ�һ��һ���ַ�����
		  //������һ�飬�����ֻҪ������һ���ַ�������Ҫ��Ϳ�����find������.
		  if(mt.matches()){
		   
		   bl = true;
		  }
		  return bl;
	}
}

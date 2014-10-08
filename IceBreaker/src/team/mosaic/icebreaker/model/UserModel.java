package team.mosaic.icebreaker.model;

import java.util.ArrayList;
import team.mosaic.icebreaker.file.FileIO;
import team.mosaic.icebreaker.modelservice.UserModelService;
import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.viewservice.LoginViewService;
import team.mosaic.icebreaker.vo.AccountVO;

/**
 * �û�ģ����
 * @author acer
 *
 */
public class UserModel implements UserModelService {

	private LoginViewService lvs;
	private AccountVO avo;
	private boolean remember;
	private ArrayList<String> idList;
	private ArrayList<String> pwdList;
	private ArrayList<Integer> hpList;

	public UserModel(LoginViewService lvs) {
		this.lvs = lvs;
		idList = new ArrayList<>();
		pwdList = new ArrayList<>();
		hpList = new ArrayList<>();
		readLocalAccount();
		ClientModel.setUserModel(this);
	}
	
	public UserModel(){}

	/**
	 * �����˺�
	 */
	public void setAccount(AccountVO avo) {
		this.avo = avo;
	}

	/**
	 * ���ü�ס����
	 */
	public void setRemember(boolean r) {
		this.remember = r;
	}

	/**
	 * ��½
	 */
	@Override
	public void login(AccountVO avo) {
		// TODO Auto-generated method stub
		NetService.signIn(avo);
	}

	/**
	 * ��½����ж���������ͼ
	 */
	@Override
	public void responseLogin(boolean isVerified) {
		// TODO Auto-generated method stub

		if (isVerified) {// ��½�ɹ�
			OnlineStatus.setID(avo.getID());
			ArrayList<String> old = FileIO.read(FileIO.ACCOUNT_FILE);
			String a = null;
			int index = 0;
			for (String obj : old) {
				String[] split = obj.split(" ");
				if (split[1].equals(avo.getID())) {
					a = split[0] + " " + split[1];
					break;
				}
				index++;
			}
			if (a != null) // �Ѵ��û�
				old.remove(index);
			else 
				a = "0 "+avo.getID();
			String nUser = null;
			if(remember)
				nUser = a + " " + encode(avo.getPassword());
			else
				nUser = a;
			old.add(nUser);
			FileIO.write(FileIO.ACCOUNT_FILE, old);
		}

		lvs.dealWithLogin(isVerified);
	}

	/**
	 * �ǳ�
	 */
	@Override
	public void signOut() {
		// TODO Auto-generated method stub
		NetService.signOut();
	}

	/**
	 * ��ȡ�����˺���Ϣ
	 */
	private void readLocalAccount() {
		// TODO Auto-generated method stub
		ArrayList<String> accountList=FileIO.read(FileIO.ACCOUNT_FILE);
		idList.clear();
		pwdList.clear();
		hpList.clear();
		
		int num = accountList.size();
		for(int i=0;i<num;i++){
			String[] account=accountList.get(i).split(" ");
			
			hpList.add(Integer.parseInt(account[0]));
			String userName=account[1];
			idList.add(userName);
			
			if(account.length!=2){
				String code=decode(account[2]);
				pwdList.add(code);
			}else {
				pwdList.add("");
			}
		}
	}
	
	/**
	 * �������
	 * @param str ԭ����
	 * @return ���ܺ�����
	 */
	private String encode(String str){
		char[] cs = str.toCharArray();
		for(int i = 0;i<cs.length;i++){
			cs[i] += (20-i);
		}
		return String.valueOf(cs);
	}
	/**
	 * �������
	 * @param code ��������
	 * @return ԭ����
	 */
	private String decode(String code){
		char[] cs = code.toCharArray();
		for(int i = 0;i<cs.length;i++){
			cs[i] -= (20-i);
		}
		return String.valueOf(cs);
	}

	@Override
	public ArrayList<String> getIdList() {
		// TODO Auto-generated method stub
		return this.idList;
	}

	@Override
	public ArrayList<String> getPwdList() {
		// TODO Auto-generated method stub
		return this.pwdList;
	}

	@Override
	public ArrayList<Integer> getHpList() {
		// TODO Auto-generated method stub
		return this.hpList;
	}
	
	@Override
	public AccountVO getAccount() {
		// TODO Auto-generated method stub
		return this.avo;
	}

}

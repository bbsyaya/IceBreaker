package team.mosaic.icebreaker.serviceimp;

import java.util.ArrayList;

import team.mosaic.icebreaker.dto.AccountDTO;
import team.mosaic.icebreaker.dto.FriendDTO;
import team.mosaic.icebreaker.dto.InfoDTO;
import team.mosaic.icebreaker.dto.RoleDTO;
import team.mosaic.icebreaker.dto.ToolDTO;
import team.mosaic.icebreaker.service.AccountService;
import team.mosaic.icebreaker.vo.AccountVO;

public class AccountServiceImp  implements AccountService{

	private AccountDTO adto = new AccountDTO();
	private InfoDTO idto = new InfoDTO();
	private RoleDTO rdto = new RoleDTO();
	private ToolDTO tdto = new ToolDTO();

	@Override
	public boolean verify(AccountVO avo) {
		// TODO Auto-generated method stub
		if (avo.getPassword().equals(adto.getpassword(avo.getID()))) {
			return true;
		}
		return false;
	}

	@Override
	public boolean register(AccountVO avo) {
		// TODO Auto-generated method stub
		ArrayList<String> list = adto.getAllAccount();
		for(String i:list){
			if(i.equals(avo.getID()))
				return false;
		}
		adto.addID(avo.getID(), avo.getPassword());
		idto.creatNewInfoObject(avo.getID());
		rdto.creatNewRoleObject(avo.getID());
		tdto.creatNewToolObject(avo.getID());
		return true;
	}

}

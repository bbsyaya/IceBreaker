package team.mosaic.icebreaker.serviceimp;

import team.mosaic.icebreaker.dto.InfoDTO;
import team.mosaic.icebreaker.dto.RoleDTO;
import team.mosaic.icebreaker.dto.ToolDTO;
import team.mosaic.icebreaker.service.GameService;
import team.mosaic.icebreaker.utility.Character;
import team.mosaic.icebreaker.utility.FileIO;
import team.mosaic.icebreaker.vo.GainVO;
import team.mosaic.icebreaker.vo.ResultVO;

public class GameServiceImp implements GameService {

	private InfoDTO idto = new InfoDTO();
	private ToolDTO tdto = new ToolDTO();
	private RoleDTO rdto = new RoleDTO();
	private FileIO tool_io = new FileIO(FileIO.TOOL_FILE);
	private FileIO character_io = new FileIO(FileIO.CHARACTER_FILE);

	@Override
	public ResultVO submitScore(String id, GainVO gvo,int mode) {
		// TODO Auto-generated method stub
		boolean isRecordBroken = false;
		boolean isLevelUp = false;

		int oldScore = idto.getScore(id);
		int oldExp = idto.getExp(id);
		int oldCoin = idto.getCoin(id);
		int oldLevel = idto.getLevel(id);
		idto.changeCoin(id, oldCoin + gvo.getCoin());
		idto.changeExp(id, oldExp + gvo.getExp());
		if ((mode == 1||mode == 3) && gvo.getScore() > oldScore) {
			idto.changeScore(id, gvo.getScore());
			isRecordBroken = true;
		}
		// Éý¼¶²ßÂÔ...
		int i = 0;
		int exp = oldExp + gvo.getExp();
		while (exp >= Math.pow(2, i+4)) {
			i++;
		}
		if (i > oldLevel){
			isLevelUp = true;
			idto.voidchangeLevel(id, i);
		}

		return new ResultVO(isRecordBroken, isLevelUp);
	}

	@Override
	public boolean buyTool(String id, String tool_id) {
		// TODO Auto-generated method stub
		int cost = Integer.parseInt(tool_io.getValue(tool_id));
		int coin = idto.getCoin(id);
		if (coin >= cost) {
			idto.changeCoin(id, coin - cost);
			tdto.buytool(id, tool_id, 1);
			return true;
		}
		return false;
	}

	@Override
	public void useTool(String id, String tool_id) {
		// TODO Auto-generated method stub
		tdto.usetool(id, tool_id);
	}

	@Override
	public boolean buyCharacter(String id, String char_id) {
		// TODO Auto-generated method stub
		int cost = Integer.parseInt(character_io.getValue(char_id));
		int coin = idto.getCoin(id);
		if(coin >= cost){
			idto.changeCoin(id, coin - cost);
			rdto.getrole(id, char_id);
			return true;
		}
		return false;
	}

	@Override
	public boolean[] getCharacter(String id) {
		// TODO Auto-generated method stub
		boolean[] ret = new boolean[Character.values().length];
		for(int i = 0;i<Character.values().length;i++){
			ret[i] = rdto.ismyrole(id, Character.values()[i].getName()); 
		}
		return ret;
	}

}

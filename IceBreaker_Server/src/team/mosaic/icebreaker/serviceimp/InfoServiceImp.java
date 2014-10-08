package team.mosaic.icebreaker.serviceimp;

import java.util.ArrayList;
import java.util.Collections;

import team.mosaic.icebreaker.dto.AccountDTO;
import team.mosaic.icebreaker.dto.FriendDTO;
import team.mosaic.icebreaker.dto.InfoDTO;
import team.mosaic.icebreaker.dto.RecordDTO;
import team.mosaic.icebreaker.dto.ToolDTO;
import team.mosaic.icebreaker.service.InfoService;
import team.mosaic.icebreaker.utility.CommonSort;
import team.mosaic.icebreaker.utility.FileIO;
import team.mosaic.icebreaker.utility.RankSort;
import team.mosaic.icebreaker.vo.InfoVO;
import team.mosaic.icebreaker.vo.RecordVO;
import team.mosaic.icebreaker.vo.ScoreVO;
import team.mosaic.icebreaker.vo.ToolVO;

public class InfoServiceImp implements InfoService {

	private AccountDTO adto = new AccountDTO();
	private InfoDTO idto = new InfoDTO();
	private FriendDTO fdto = new FriendDTO();
	private ToolDTO tdto = new ToolDTO();
	private RecordDTO rdto = new RecordDTO();
	
	private FileIO tool_io = new FileIO(FileIO.TOOL_FILE);

	@Override
	public InfoVO getInfo(String id) {
		// TODO Auto-generated method stub
		return new InfoVO(id, idto.getLevel(id), idto.getExp(id),
				idto.getScore(id), idto.getCoin(id));
	}

	@Override
	public ArrayList<ScoreVO> getFriendRank(String id) {
		// TODO Auto-generated method stub
		ArrayList<ScoreVO> ret = new ArrayList<>();
		ArrayList<String> friends = fdto.getFriends(id);
		for (String fid : friends) {
			ret.add(new ScoreVO(fid, idto.getScore(fid)));
		}
		ret.add(new ScoreVO(id, idto.getScore(id)));//Ìí¼Ó×Ô¼º
		Collections.sort(ret, new RankSort());
		return ret;
	}

	@Override
	public ArrayList<ScoreVO> getWorldRank() {
		// TODO Auto-generated method stub
		ArrayList<ScoreVO> ret = new ArrayList<>();
		ArrayList<String> world = adto.getAllAccount();
		for (String id : world) {
			ret.add(new ScoreVO(id, idto.getScore(id)));
		}
		Collections.sort(ret, new RankSort());
		return ret;
	}

	@Override
	public ArrayList<ToolVO> getMyTools(String id) {
		// TODO Auto-generated method stub
		ArrayList<ToolVO> ret = new ArrayList<>();
		ArrayList<String> list = tool_io.getList();
		for(String l:list){
			String[] split = l.split(":");
			ret.add(new ToolVO(split[0], tdto.toolnumber(id, split[0]), Integer.parseInt(split[1])));
		}
		return ret;
	}

	@Override
	public ArrayList<String> getMyFriends(String id) {
		// TODO Auto-generated method stub
		ArrayList<String> friends = fdto.getFriends(id);
		Collections.sort(friends, new CommonSort());
		return friends;
	}

	@Override
	public void addFriend(String id1,String id2) {
		// TODO Auto-generated method stub
		fdto.addFriend(id1, id2);
	}

	@Override
	public ArrayList<RecordVO> queryRecord(String id) {
		// TODO Auto-generated method stub
		ArrayList<String> tmp = rdto.getRecord(id);
		ArrayList<RecordVO> records = new ArrayList<RecordVO>();
		for(String s:tmp){
			String[] str = s.split("\t");
			String date = str[1].split(" ")[0];
			String maxCombo = str[2];
			String score = str[3];
			RecordVO rvo = new RecordVO(Integer.valueOf(score)
					,Integer.valueOf(maxCombo),date);
			records.add(rvo);
		}
		return records;
	}

	@Override
	public void updateRecord(String id,RecordVO rvo) {
		// TODO Auto-generated method stub
		rdto.initialization(id, rvo.getDate(), rvo.getMaxCombo(), rvo.getScore());
	}

}

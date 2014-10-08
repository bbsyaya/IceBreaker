package team.mosaic.icebreaker.model;

import java.util.ArrayList;

import team.mosaic.icebreaker.modelservice.MenuModelService;
import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.viewservice.FirstViewService;
import team.mosaic.icebreaker.vo.ScoreVO;

/**
 * 主界面模型类
 * @author acer
 *
 */
public class MenuModel implements MenuModelService {
    private static FirstViewService fvs;
    ArrayList<String> friends = new ArrayList<String>();
	
    public MenuModel(){};
    
	public MenuModel(FirstViewService fvs,boolean onLine){
		this.fvs = fvs;
		OnlineStatus.setOnline(onLine);
		if(onLine)
			NetService.getMyRank();
		ClientModel.setMenuModel(this);
	}
	
	/**
	 * 好友排行
	 */
	@Override
	public void friendRank() {
		// TODO Auto-generated method stub
		NetService.getMyRank();
//		fvs.refreshFriendsRank(null);
		
	}

	/**
	 * 世界排名
	 */
	@Override
	public void worldRank() {
		// TODO Auto-generated method stub
		NetService.getWorldRank();
//		fvs.refreshAllRank(null);
	}

	/**
	 * 更改视图，显示好友排名
	 */
	@Override
	public void showFriendRank(ArrayList<ScoreVO> rank) {
		// TODO Auto-generated method stub
		ArrayList<String> tops = new ArrayList<String>();
		for(int i = 0;i<rank.size();i++){
			friends.add(rank.get(i).getID());
			tops.add(rank.get(i).getID()+"\t"+rank.get(i).getScore());
		}
		fvs.refreshFriendsRank(tops);
	}

	/**
	 * 更改视图，显示世界排名
	 */
	@Override
	public void showWorldRank(ArrayList<ScoreVO> rank) {
		// TODO Auto-generated method stub
		ArrayList<String> tops = new ArrayList<String>();
		for(int i = 0;i<rank.size();i++){
			tops.add(rank.get(i).getID()+"\t"+rank.get(i).getScore());
		}
		fvs.refreshAllRank(tops);
	}

	/**
	 * 添加好友
	 */
	@Override
	public void addFriend(String id) {
		// TODO Auto-generated method stub
		if(id.equals(OnlineStatus.getID())){
			Prompt.showWarning(null, "这是你自己啊啊啊！");
		}
		else if(friends.contains(id)){
			Prompt.showWarning(null,id+ " 已经是您的好友！");
		}
		else
			NetService.applyFriend(id); 
	}
	
	@Override
	public void friendApply(String id) {
		// TODO Auto-generated method stub
		fvs.friendApply(id);
	}

	/**
	 * 更改视图，显示添加好友结果
	 */
	@Override
	public void resultOfFriendApply(String id, boolean b) {
		// TODO Auto-generated method stub
		String result = b?"接受了":"拒绝了";
		Prompt.showWarning(null, id+result+"您的好友申请");
		if(b){
			friendRank();
		}
	}

	@Override
	public void notFoundPlayer() {
		// TODO Auto-generated method stub
		Prompt.showWarning(null, "不存在该玩家或对方不在线");
	}
	
}

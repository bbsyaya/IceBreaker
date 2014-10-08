package team.mosaic.icebreaker.model;

import java.util.ArrayList;

import team.mosaic.icebreaker.modelservice.MenuModelService;
import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.viewservice.FirstViewService;
import team.mosaic.icebreaker.vo.ScoreVO;

/**
 * ������ģ����
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
	 * ��������
	 */
	@Override
	public void friendRank() {
		// TODO Auto-generated method stub
		NetService.getMyRank();
//		fvs.refreshFriendsRank(null);
		
	}

	/**
	 * ��������
	 */
	@Override
	public void worldRank() {
		// TODO Auto-generated method stub
		NetService.getWorldRank();
//		fvs.refreshAllRank(null);
	}

	/**
	 * ������ͼ����ʾ��������
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
	 * ������ͼ����ʾ��������
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
	 * ��Ӻ���
	 */
	@Override
	public void addFriend(String id) {
		// TODO Auto-generated method stub
		if(id.equals(OnlineStatus.getID())){
			Prompt.showWarning(null, "�������Լ���������");
		}
		else if(friends.contains(id)){
			Prompt.showWarning(null,id+ " �Ѿ������ĺ��ѣ�");
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
	 * ������ͼ����ʾ��Ӻ��ѽ��
	 */
	@Override
	public void resultOfFriendApply(String id, boolean b) {
		// TODO Auto-generated method stub
		String result = b?"������":"�ܾ���";
		Prompt.showWarning(null, id+result+"���ĺ�������");
		if(b){
			friendRank();
		}
	}

	@Override
	public void notFoundPlayer() {
		// TODO Auto-generated method stub
		Prompt.showWarning(null, "�����ڸ���һ�Է�������");
	}
	
}

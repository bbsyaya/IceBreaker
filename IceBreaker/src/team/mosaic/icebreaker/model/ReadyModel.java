package team.mosaic.icebreaker.model;

import java.util.ArrayList;

import team.mosaic.icebreaker.modelservice.ReadyModelService;
import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.viewservice.GameViewService;
import team.mosaic.icebreaker.viewservice.ReadyViewService;

/**
 * 游戏准备阶段模型类
 * @author acer
 *
 */
public class ReadyModel implements ReadyModelService{

	private ReadyViewService rvs;
	private GameViewService gvs;
	private static ArrayList<Integer> num;

	public ReadyModel(ReadyViewService rvs,GameViewService gvs){
		this.rvs = rvs;
		this.gvs = gvs;
		ClientModel.setReadyModel(this);
	}
	public ReadyModel(){};

	public static void setTools(ArrayList<Integer> tools){
		num = tools;
	}
	@Override
	public void getFriends() {
		// TODO Auto-generated method stub
		//获得好友信息
		//		ArrayList<String> a = new ArrayList<String>();
		//		rvs.showFriends(a);
		NetService.getOnlinePal();
	}

	public ReadyViewService getReadyViewService(){
		return rvs;
	}

	/**
	 * 请求匹配好友协作
	 */
	@Override
	public void askCouple(String id) {
		// TODO Auto-generated method stub
		//向服务器请求匹配
		//		AccountVO p = new AccountVO("2","2");
		//		return p;
		NetService.cooperateWithPal(id);
		//		gvs.ToWaitForAgree();
	}

	/**
	 * 更改视图，显示在线可匹配好友
	 */
	@Override
	public void showFriends(ArrayList<String> friends) {
		// TODO Auto-generated method stub
		rvs.showFriends(friends);
	}

	/**
	 * 请求随机匹配协作
	 */
	@Override
	public void askCoupleRandom() {
		// TODO Auto-generated method stub
		NetService.cooperateRandom();
		//		gvs.ToWaitForAgree();
	}

	/**
	 * 请求匹配好友对战
	 */
	@Override
	public void PKWith(String id) {
		// TODO Auto-generated method stub
		NetService.pkWithPal(id);
	}

	/**
	 * 请求随机匹配对战
	 */
	@Override
	public void PKRandom() {
		// TODO Auto-generated method stub
		NetService.pkRandom();
		//		gvs.ToWaitForAgree();
	}

	/**
	 * 请求四人随机匹配协作
	 */
	@Override
	public void randomCoopFour() {
		// TODO Auto-generated method stub
		NetService.foursomeCoop();
	}

	/**
	 * 请求四人随机匹配对战
	 */
	@Override
	public void randomPkFour() {
		// TODO Auto-generated method stub
		NetService.foursomeComp();
	}

	/**
	 * 选择道具
	 */
	@Override
	public void SelectTool(int i){
		if(!OnlineStatus.isOnline()){
			//			JOptionPane.showMessageDialog(null, "未联网，无法使用道具");
		}
		else if(ToolModel.getTool){
			if(num.get(i)<=0){
				Prompt.showWarning(rvs.getFrame(), "此道具已用光，请购买");
				rvs.cancelSelect(i);
			}
		}
		else{
			NetService.getMyTools();
			while(true){
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(ToolModel.getTool)
					break;
			}
			if(num.get(i)<=0){
				Prompt.showWarning(rvs.getFrame(), "此道具已用光，请购买");
				rvs.cancelSelect(i);
			}
		}

	}

	/**
	 * 设置使用道具
	 */
	public void setTool(boolean[] tool ){
		for(int i = 0;i<tool.length;i++){
			if(tool[i]){
				String tool_id = null;
				switch(i){
				case 0:tool_id = "C";break;
				case 1:tool_id = "D";break;
				case 2:tool_id = "E";break;
				case 3:tool_id = "F";break;
				default:break;
				}
				NetService.useTool(tool_id);
			}
		}

	}
	/**************for net below*********************/
	/**
	 * 匹配玩家协作，更改视图，显示匹配结果
	 */
	@Override
	public void findCoopPair(String id) {
		// TODO Auto-generated method stub
		rvs.findCoopPair(id);
	}

	/**
	 * 匹配四个玩家协作，更改视图，显示匹配结果
	 */
	@Override
	public void findCoopFour(String[] id) {
		// TODO Auto-generated method stub
		rvs.findCoopFour(id);
	}

	/**
	 * 匹配四个玩家对战，更改视图，显示匹配结果
	 */
	@Override
	public void findPKFour(String[] id) {
		// TODO Auto-generated method stub
		rvs.findPKFour(id);
	}

	/**
	 * 更改视图，开始双人游戏
	 */
	@Override
	public void startTwosome() {
		// TODO Auto-generated method stub
		rvs.startTwosome();
	}

	/**
	 * 更改视图，开始四人游戏
	 */
	@Override
	public void startFoursome() {
		// TODO Auto-generated method stub
		rvs.startFoursome();
	}

	/**
	 * 更改视图，开始好友PK
	 */
	@Override
	public void startPk(String id) {
		// TODO Auto-generated method stub
		rvs.startPk(id);
	}

}

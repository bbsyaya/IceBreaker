package team.mosaic.icebreaker.model;

import java.util.ArrayList;

import team.mosaic.icebreaker.modelservice.ReadyModelService;
import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.viewservice.GameViewService;
import team.mosaic.icebreaker.viewservice.ReadyViewService;

/**
 * ��Ϸ׼���׶�ģ����
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
		//��ú�����Ϣ
		//		ArrayList<String> a = new ArrayList<String>();
		//		rvs.showFriends(a);
		NetService.getOnlinePal();
	}

	public ReadyViewService getReadyViewService(){
		return rvs;
	}

	/**
	 * ����ƥ�����Э��
	 */
	@Override
	public void askCouple(String id) {
		// TODO Auto-generated method stub
		//�����������ƥ��
		//		AccountVO p = new AccountVO("2","2");
		//		return p;
		NetService.cooperateWithPal(id);
		//		gvs.ToWaitForAgree();
	}

	/**
	 * ������ͼ����ʾ���߿�ƥ�����
	 */
	@Override
	public void showFriends(ArrayList<String> friends) {
		// TODO Auto-generated method stub
		rvs.showFriends(friends);
	}

	/**
	 * �������ƥ��Э��
	 */
	@Override
	public void askCoupleRandom() {
		// TODO Auto-generated method stub
		NetService.cooperateRandom();
		//		gvs.ToWaitForAgree();
	}

	/**
	 * ����ƥ����Ѷ�ս
	 */
	@Override
	public void PKWith(String id) {
		// TODO Auto-generated method stub
		NetService.pkWithPal(id);
	}

	/**
	 * �������ƥ���ս
	 */
	@Override
	public void PKRandom() {
		// TODO Auto-generated method stub
		NetService.pkRandom();
		//		gvs.ToWaitForAgree();
	}

	/**
	 * �����������ƥ��Э��
	 */
	@Override
	public void randomCoopFour() {
		// TODO Auto-generated method stub
		NetService.foursomeCoop();
	}

	/**
	 * �����������ƥ���ս
	 */
	@Override
	public void randomPkFour() {
		// TODO Auto-generated method stub
		NetService.foursomeComp();
	}

	/**
	 * ѡ�����
	 */
	@Override
	public void SelectTool(int i){
		if(!OnlineStatus.isOnline()){
			//			JOptionPane.showMessageDialog(null, "δ�������޷�ʹ�õ���");
		}
		else if(ToolModel.getTool){
			if(num.get(i)<=0){
				Prompt.showWarning(rvs.getFrame(), "�˵������ù⣬�빺��");
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
				Prompt.showWarning(rvs.getFrame(), "�˵������ù⣬�빺��");
				rvs.cancelSelect(i);
			}
		}

	}

	/**
	 * ����ʹ�õ���
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
	 * ƥ�����Э����������ͼ����ʾƥ����
	 */
	@Override
	public void findCoopPair(String id) {
		// TODO Auto-generated method stub
		rvs.findCoopPair(id);
	}

	/**
	 * ƥ���ĸ����Э����������ͼ����ʾƥ����
	 */
	@Override
	public void findCoopFour(String[] id) {
		// TODO Auto-generated method stub
		rvs.findCoopFour(id);
	}

	/**
	 * ƥ���ĸ���Ҷ�ս��������ͼ����ʾƥ����
	 */
	@Override
	public void findPKFour(String[] id) {
		// TODO Auto-generated method stub
		rvs.findPKFour(id);
	}

	/**
	 * ������ͼ����ʼ˫����Ϸ
	 */
	@Override
	public void startTwosome() {
		// TODO Auto-generated method stub
		rvs.startTwosome();
	}

	/**
	 * ������ͼ����ʼ������Ϸ
	 */
	@Override
	public void startFoursome() {
		// TODO Auto-generated method stub
		rvs.startFoursome();
	}

	/**
	 * ������ͼ����ʼ����PK
	 */
	@Override
	public void startPk(String id) {
		// TODO Auto-generated method stub
		rvs.startPk(id);
	}

}

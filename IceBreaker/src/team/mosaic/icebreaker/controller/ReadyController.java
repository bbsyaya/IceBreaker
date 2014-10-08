package team.mosaic.icebreaker.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import team.mosaic.icebreaker.file.FileIO;
import team.mosaic.icebreaker.model.OnlineStatus;
import team.mosaic.icebreaker.model.ReadyModel;
import team.mosaic.icebreaker.modelservice.ReadyModelService;
import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.sound.BGMusic;
import team.mosaic.icebreaker.utility.Loading;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.view.game.GamePanel;
import team.mosaic.icebreaker.view.main.BgPanel;
import team.mosaic.icebreaker.view.main.MainFrame;
import team.mosaic.icebreaker.view.main.ReadyPanel;

/*
 * 准备界面控制器
 */
public class ReadyController implements MouseListener,ItemListener{

	private ReadyPanel rp;
	private BgPanel bp;
	private ReadyModelService rms;
	private String player2 = null;
	private  boolean[] tool={false,false,false,false};
	private int selectedRow = -1;

	public int mode = 1;

	public ReadyController(ReadyPanel rp) {
		this.rp = rp;
		bp = rp.getParentPanel();
		rms = new ReadyModel(rp,bp.getParentFrame().getGamePanel());
	}

	public boolean[] getTools(){
		return this.tool;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

		if(arg0.getSource() == rp.singleButton){
			mode = 1;
			rp.showRole();				
			rp.setFocus(mode);
		}
		if(arg0.getSource() == rp.doubleButton){
			mode = 2;
			rms.getFriends();
			rp.setFocus(mode);
		}
		if(arg0.getSource() == rp.pkButton){
			mode = 3;
			rms.getFriends();
			rp.setFocus(mode);
		}

		//选择好友进行匹配
		if(arg0.getSource() == rp.scroll){
			selectedRow = rp.friends.getSelectedRow();//选择的为第几个人
		}

		//开始单人游戏
		if(arg0.getSource() == rp.startGame){
			Loading.addLoading();
			MainFrame frame = bp.getParentFrame();
			if(frame.onLine){
				rms.setTool(tool);
			}
			GamePanel gp = frame.getGamePanel();
			gp.initSingle(frame.getBgPanel().getInfoPanel().getCharacter(), tool);
			frame.modifyPanel(gp);
			frame.getGamePanel().bp.startTiming();
			BGMusic.stop();
			if(OnlineStatus.isOnline())
				NetService.startSingle();
			Loading.removeLoading();
		}
		
		//邀请好友
		if(arg0.getSource() == rp.invite){
//			MainFrame frame = bp.getParentFrame();
			selectedRow = rp.friends.getSelectedRow();
			if(selectedRow != -1){
				if(mode == 2)
					rms.askCouple(rp.friends.getValueAt(selectedRow, 0).toString());
				else if(mode == 3)
					rms.PKWith(rp.friends.getValueAt(selectedRow, 0).toString());
				Prompt.showMessage(bp.getParentFrame(), "已发送邀请");
			}
			else
				Prompt.showWarning(rp.getFrame(), "请选择匹配玩家");					    	
		}



		//随机匹配
		if(arg0.getSource() == rp.Random_Couple){
			//			Loading.addReady();
			Prompt.showWaiting(bp.getParentFrame(), "匹配中");
			if(mode == 2)
				rms.askCoupleRandom();
			else if(mode == 3)
				rms.PKRandom();
			player2 = rp.getPlayer();
		}
		//四人随机
		if(arg0.getSource() == rp.random_fousome){
			Prompt.showWaiting(bp.getParentFrame(), "匹配中");
			if(mode == 2)
				rms.randomCoopFour();
			else if(mode == 3)
				rms.randomPkFour();
		}
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public int getDirection(){
		String con = null;
		for(String s:FileIO.getConfig()){
			if(s.contains("direction")){
				con = s;
			}
		}
		String[] split = con.split(" ");
		return Integer.parseInt(split[1]);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getStateChange()==ItemEvent.SELECTED){
			if(arg0.getSource() == rp.t1){
				tool[0] = true;
				rms.SelectTool(0);
			}
			if(arg0.getSource() == rp.t2){
				tool[1] = true;
				rms.SelectTool(1);
			}
			if(arg0.getSource() == rp.t3){
				tool[2] = true;
				rms.SelectTool(2);
			}
			if(arg0.getSource() == rp.t4){
				tool[3] = true;
				rms.SelectTool(3);
			}
		}

		if(arg0.getStateChange()==ItemEvent.DESELECTED){
			if(arg0.getSource() == rp.t1){
				tool[0] = false;
			}
			if(arg0.getSource() == rp.t2){
				tool[1] = false;
			}
			if(arg0.getSource() == rp.t3){
				tool[2] = false;
			}
			if(arg0.getSource() == rp.t4){
				tool[3] = false;
			}

		}

	}
	
}

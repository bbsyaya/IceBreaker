package team.mosaic.icebreaker.utility;

import javax.swing.JDialog;
import javax.swing.JFrame;

import team.mosaic.icebreaker.components.FriendDialog;
import team.mosaic.icebreaker.components.GainDialog;
import team.mosaic.icebreaker.components.MessageDialog;
import team.mosaic.icebreaker.components.OptionDialog;
import team.mosaic.icebreaker.components.WarningDialog;
import team.mosaic.icebreaker.view.main.MainFrame;

public class Prompt {
	
//	public static final int YES = 1;
//	public static final int NO = 2;
	private static JDialog last;
	
	public static void showWarning(JFrame frame,String str){
		if(last != null)
			last.dispose();
		last = new WarningDialog(frame, str,"确定",0);
		shieldListener();
	}
	
	public static void showWaiting(JFrame frame,String str){
		if(last != null)
			last.dispose();
		last = new WarningDialog(frame, str, "取消",1);
		shieldListener();
	}
	
	public static void showMessage(JFrame frame,String str){
		if(last != null)
			last.dispose();
		last = new MessageDialog(frame, str);
		shieldListener();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public static void showBuyCharacter(JFrame frame,String string,int charIndex){
		if(last != null)
			last.dispose();
		OptionDialog od = new OptionDialog(1, frame, string);
		od.setCharIndex(charIndex);
		last = od;
		shieldListener();
	}
	
	public static void showCoopInvited(JFrame frame,String id){
		if(last != null)
			last.dispose();
		OptionDialog od = new OptionDialog(2, frame, "接收 "+id+" 的协作邀请？");
		od.setId(id);
		last = od;
		shieldListener();
	}
	
	public static void showPkInvited(JFrame frame,String id){
		if(last != null)
			last.dispose();
		OptionDialog od = new OptionDialog(3, frame, "接收 "+id+" 的对战邀请？");
		od.setId(id);
		last = od;
		shieldListener();
	}
	
	public static void showFriendDialog(MainFrame frame){
		if(last != null)
			last.dispose();
		last = new FriendDialog(frame);
		shieldListener();
	}
	
	public static void showCooperationDialog(){
		
	}
	
	public static void showPKDialog(){
		
	}
	
	public static void showGainDialog(MainFrame frameParent,boolean isPK,int score,int coin,int exp,boolean breakRecord,boolean levelUp,int win){
		if(last != null)
			last.dispose();
		last = new GainDialog(frameParent, isPK, score, coin, exp, breakRecord, levelUp, win);
		shieldListener();
	}
	
	public static void showFriendApp(MainFrame frame,String id){
		if(last != null)
			last.dispose();
		OptionDialog od = new OptionDialog(4, frame, "是否接收"+id+"的好友请求？");
		od.setId(id);
		last = od;
		shieldListener();
	}
	
	private static void shieldListener(){
		Loading.LOADING_GLASS_PANE.removeAll();
		Loading.addLoading();
	}
	
	public static void main(String[] args){
		showGainDialog(null, false, 17900, 110, 0, false, false, -1);
	}
}

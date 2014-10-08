package team.mosaic.icebreaker.controller;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import team.mosaic.icebreaker.model.ClientModel;
import team.mosaic.icebreaker.model.MenuModel;
import team.mosaic.icebreaker.model.OnlineStatus;
import team.mosaic.icebreaker.modelservice.MenuModelService;
import team.mosaic.icebreaker.sound.BGMusic;
import team.mosaic.icebreaker.utility.Fade;
import team.mosaic.icebreaker.utility.Loading;
import team.mosaic.icebreaker.utility.PanelChanger;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.view.login.LoginFrame;
import team.mosaic.icebreaker.view.main.BgPanel;
import team.mosaic.icebreaker.view.main.FirstPanel;
import team.mosaic.icebreaker.view.main.MainFrame;

public class MenuController implements MouseListener,MouseMotionListener {

	private FirstPanel fp;
	private BgPanel bp;
	private int mx;
	private int my;
	private MenuModelService mms;
	
	
	public MenuController(FirstPanel p) {
		fp = p;
		bp = fp.getParentPanel();
		mms = new MenuModel(fp,fp.getOnline());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == fp.beginButton) {
			fp.addFriend.setVisible(false);
			fp.addFriend.setEnabled(false);
			int x = e.getX();
			int y = e.getY();
			if(x*x - 370*x + y*y - 370*y + 34225 <= 0){
				 if(!fp.getReady()){//第一次开始游戏
					 fp.add(bp.getReadyPanel());
					 fp.ready();
					// PanelChanger.slideLeft(fp, bp.getReadyPanel(), bp);
				 }
				 else{//正式开始游戏
					 fp.remove(bp.getReadyPanel());
					 fp.repaint();
					 fp.back();
					 fp.addFriend.setVisible(true);
					 fp.addFriend.setEnabled(true);
//					 MainFrame frame = bp.getParentFrame();
//					 frame.modifyPanel(frame.getGamePanel());
			 }
			}
		} else if (e.getSource() == fp.backButton) {
			fp.addFriend.setVisible(true);
			fp.addFriend.setEnabled(true);
			 fp.remove(bp.getReadyPanel());
			 fp.repaint();
			 fp.back();
		} else if (e.getSource() == fp.infoButton) {
			PanelChanger.slideLeft(fp, bp.getInfoPanel(), bp);
			bp.getInfoPanel().updateChar();
			bp.getInfoPanel().getInfo();
		} else if (e.getSource() == fp.toolButton) {
			PanelChanger.slideLeft(fp, bp.getToolPanel(), bp);
			bp.getToolPanel().getToolNumber();
		} else if (e.getSource() == fp.setButton) {
			Loading.addLoading();
			PanelChanger.slideLeft(fp, bp.getSetPanel(), bp);
			Loading.removeLoading();
		} else if (e.getSource() == fp.helpButton) {
			PanelChanger.slideLeft(fp, bp.getHelpPanel(), bp);
			bp.getHelpPanel().getHelpTips();
		} else if (e.getSource() == fp.logoutButton) {//注销
//			ClientSocket.getInstance().shutDown();
			MainFrame f = bp.getParentFrame();
			Loading.addLoading();
			Fade.fadeOut(f);
			BGMusic.stop();
			LoginFrame.showUp();
			if(fp.getOnline())
				ClientModel.getUserModel().signOut();
		}else if (e.getSource() == fp.friendRank){
			fp.showFriendRank();
			if(OnlineStatus.isOnline())
				mms.friendRank();
		}else if(e.getSource() == fp.worldRank){
			fp.showWorldRank();
			if(OnlineStatus.isOnline())
				mms.worldRank();
		}else if(e.getSource() == fp.addFriend){
//			String id = fp.friendID.getText();
//			if(id != null&&(!id.equals("")))
//				mms.addFriend(id);
//			else{
//				
//			}
			Prompt.showFriendDialog(bp.getParentFrame());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == fp.beginButton) {
			// Resize.setBigger(fp.beginButton,30);
			// double x = fp.beginButton.getMousePosition().getX();
			// double y = fp.beginButton.getMousePosition().getY();
			// System.out.println(x+","+y);
			// if(x*x-370*x+y*y-370*y+34225<=0)
//			fp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		else if(e.getSource() == fp.ob){
			fp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == fp.beginButton) {
			fp.setCursor(Cursor.getDefaultCursor());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == fp.beginButton){
			mx = arg0.getX();
			my = arg0.getY();
			if(mx*mx - 370*mx + my*my - 370*my + 34225 <= 0)
				fp.setCursor(new Cursor(Cursor.HAND_CURSOR));
			else {
				fp.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}

}

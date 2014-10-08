package team.mosaic.icebreaker.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import team.mosaic.icebreaker.model.ClientModel;
import team.mosaic.icebreaker.model.OnlineStatus;
import team.mosaic.icebreaker.model.SetModel;
import team.mosaic.icebreaker.modelservice.SetModelService;
import team.mosaic.icebreaker.view.game.GamePanel;
import team.mosaic.icebreaker.view.main.MainFrame;
import team.mosaic.icebreaker.view.main.ReadyPanel;
import team.mosaic.icebreaker.view.main.SetPanel;

/*
 * 游戏进行中的控制器
 */
public class GameController implements MouseListener {

	private GamePanel gp;
	private MainFrame mf;
	private SetPanel sp;
	private ReadyPanel rp;
	private int music;
	private SetModelService sms;

	public GameController(GamePanel gp) {
		this.gp = gp;
		mf = gp.getParentFrame();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == gp.outButton) {
//			gp.bp.controller.bms.
			gp.bp.controller.bms.stopGame();
			sp = mf.getBgPanel().getSetPanel();
			rp = mf.getBgPanel().getReadyPanel();
			rp.t1.setSelected(false);
			rp.t2.setSelected(false);
			rp.t3.setSelected(false);
			rp.t4.setSelected(false);
			music = sp.mus ? 1 : 0;
			sms = new SetModel();
			mf.modifyPanel(mf.getBgPanel());
			sms.setMusic(music);
			if(OnlineStatus.isOnline())
				ClientModel.getGameModel().quitGame();
		}
		
		else {
					}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

//	private int countGrid(int x) {
//		return x / BoardPanel.GRID_LENGTH + 1;
//	}

}

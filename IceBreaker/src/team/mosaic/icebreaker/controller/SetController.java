package team.mosaic.icebreaker.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

import team.mosaic.icebreaker.file.FileIO;
import team.mosaic.icebreaker.model.SetModel;
import team.mosaic.icebreaker.modelservice.SetModelService;
import team.mosaic.icebreaker.utility.PanelChanger;
import team.mosaic.icebreaker.view.main.BgPanel;
import team.mosaic.icebreaker.view.main.SetPanel;

/*
 * 设置界面控制器
 */
public class SetController implements MouseListener, ItemListener {

	private SetPanel sp;
	private BgPanel bp;
	private SetModelService sms;

	public  int direction;
	public  int sound;
	public  int music;

	public SetController(SetPanel sp) {
		this.sp = sp;
		bp = sp.getParentPanel();
		direction = sp.dir ? 0 : 1;
		sound = sp.sou ? 1 : 0;
		music = sp.mus ? 1 : 0;
		sms = new SetModel(music,sound,direction);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == sp.backButton) {
			FileIO.writeConfig("sound " + sound + "\r\n" + "music" + " "
					+ music + "\r\n" + "direction" + " " + direction);
			PanelChanger.slideRight(sp, bp.getFirstPanel(), bp);
		}
		
		if (arg0.getSource() == sp.switcher_1) {
			JLabel label = sp.switcher_1;
			sp.icon_1 = (sp.icon_1+1)%2;
			music = sp.icon_1;
			sms.setMusic(music);
			sp.mus = (music == 1);
			label.setIcon(sp.icons[sp.icon_1]);
			
		}
		
		if (arg0.getSource() == sp.switcher_2) {
			JLabel label = sp.switcher_2;
			sp.icon_2 = (sp.icon_2+1)%2;
			sound = sp.icon_2;
			sms.setSound(sound);
			sp.sou = (sound == 1);
			label.setIcon(sp.icons[sp.icon_2]);
			
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

	@Override
	// public void stateChanged(ChangeEvent arg0) {
	// // TODO Auto-generated method stub
	// sp.musicValue.setText(sp.musicSlider.getValue() + "");
	// sp.soundValue.setText(sp.soundSlider.getValue() + "");
	// }
	public void itemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			if (evt.getSource() == sp.down) {
				direction = 0;
				SetModel.direction = direction;
			}
			if (evt.getSource() == sp.left) {
				direction = 1;
				SetModel.direction = direction;
			}
			if (evt.getSource() == sp.open1) {
				sp.mus  = true;
				music = 1; // 1表示声音开
				sms.setMusic(music);
			}
			if (evt.getSource() == sp.close1) {
				sp.mus = false;
				music = 0;
				sms.setMusic(music);
			}
			if (evt.getSource() == sp.open2) {
				sp.sou = true;
				sound = 1;
				sms.setSound(sound);
			}
			if (evt.getSource() == sp.close2) {
				sp.mus = false;
				sound = 0;
				sms.setSound(sound);
			}
		}
	}

}

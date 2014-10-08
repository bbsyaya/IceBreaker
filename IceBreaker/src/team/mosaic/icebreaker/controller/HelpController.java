package team.mosaic.icebreaker.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import team.mosaic.icebreaker.model.HelpModel;
import team.mosaic.icebreaker.modelservice.HelpModelService;
import team.mosaic.icebreaker.utility.PanelChanger;
import team.mosaic.icebreaker.view.main.BgPanel;
import team.mosaic.icebreaker.view.main.HelpPanel;
/*
 * °ïÖú½çÃæ¿ØÖÆÆ÷
 */
public class HelpController implements MouseListener {

	private HelpPanel hp;
	private BgPanel bp;
	private HelpModelService hms;

	public HelpController(HelpPanel hp) {
		this.hp = hp;
		bp = hp.getParentPanel();
		hms = new HelpModel(hp);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == hp.backButton) {
			PanelChanger.slideRight(hp, bp.getFirstPanel(), bp);
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
	
	public void getHelpTips(){
		hms.fetchHelpTips();
	}
}

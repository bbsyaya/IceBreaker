package team.mosaic.icebreaker.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import team.mosaic.icebreaker.model.ToolModel;
import team.mosaic.icebreaker.modelservice.ToolModelService;
import team.mosaic.icebreaker.utility.PanelChanger;
import team.mosaic.icebreaker.view.main.BgPanel;
import team.mosaic.icebreaker.view.main.ToolPanel;

/*
 * 道具界面控制器
 */
public class ToolController implements MouseListener {

	private ToolPanel tp;
	private BgPanel bp;
	private ToolModelService tm;

	public ToolController(ToolPanel tp) {
		this.tp = tp;
		bp = tp.getParentPanel();
		tm = new ToolModel(tp);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == tp.backButton) {
			PanelChanger.slideRight(tp, bp.getFirstPanel(), bp);
		}
		if(e.getSource() == tp.a1){	
			tm.buyTool(1);
		}
		if(e.getSource() == tp.a2){	
			tm.buyTool(2);
		}
		if(e.getSource() == tp.a3){
			tm.buyTool(3);
		}
		if(e.getSource() == tp.a4){
			tm.buyTool(4);
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

	public void getToolNumber(){
		tm.showTools();		
	}



}

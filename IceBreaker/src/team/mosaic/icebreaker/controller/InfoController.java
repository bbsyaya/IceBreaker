package team.mosaic.icebreaker.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import team.mosaic.icebreaker.components.PlayerLabel;
import team.mosaic.icebreaker.model.InfoModel;
import team.mosaic.icebreaker.modelservice.InfoModelService;
import team.mosaic.icebreaker.utility.PanelChanger;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.view.main.BgPanel;
import team.mosaic.icebreaker.view.main.InfoPanel;

/*
 * 个人信息界面控制器
 */
public class InfoController implements MouseListener {

	private InfoPanel ip;
	private BgPanel bp;
	private InfoModelService ims;
	public int i = 0;
	public  int choose = 0;

	public InfoController(InfoPanel ip) {
		this.ip = ip;
		bp = ip.getParentPanel();
		ims = new InfoModel(ip);
	}

	public InfoController(){

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == ip.backButton) {
			PlayerLabel.setPortrait(choose);
			ims.writeCharacter(ip.idList.get(ip.idList.size()-1),choose);
			PanelChanger.slideRight(ip, bp.getFirstPanel(), bp);

		}

		if(e.getSource() == ip.left){
			if(i==0){
				i = 3;
			}
			else{
				i = i-1;
			}
			select(i);

		}

		if(e.getSource() == ip.right){
			if(i == 3){
				i = 0;
			}
			else{
				i = i+1;
			}

			select(i);

		}

		if(e.getSource() == ip.label){
			if(ip.get){
				choose = i;
				ip.setCharacter(i+4, true);
				bp.getReadyPanel().showRole();
			}
		}

		if(e.getSource() == ip.price){
			Prompt.showBuyCharacter(ip.getParentPanel().getParentFrame(), "是否购买？？", i);
		}

		if(e.getSource() == ip.avgLabel){
			
			ims.showAvg();

		}
		if(e.getSource() == ip.bestLabel){
			
			ims.showBest();

		}
		if(e.getSource() == ip.comboLabel){
			
			ims.showMaxCombo();

		}
		if(e.getSource() == ip.gameCount){
			
			ims.showGameCount();

		}
		if(e.getSource() == ip.everyGame){
			
			ims.showEveryGame();

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
		if(e.getSource() == ip.left){
			ip.left.setIcon(new ImageIcon(ip.ar.getHorImage(1)));
		}
		if(e.getSource() == ip.right){
			ip.right.setIcon(new ImageIcon(ip.ar.getHorImage(3)));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == ip.left){
			ip.left.setIcon(new ImageIcon(ip.ar.getHorImage(0)));
		}
		if(e.getSource() == ip.right){
			ip.right.setIcon(new ImageIcon(ip.ar.getHorImage(2)));
		}

	}

	public void getInfo(){
		ims.fetchInfo();
	}

	public void updateChar(){
		ims.updateChar();
	}

	public int charnumber(){
		return choose;
	}

	public void select(int i){
		if( i == choose){
			ip.setCharacter(i+4, true);
		}
		else{
			ims.ifBought(i);
		}
	}


}

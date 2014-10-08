package team.mosaic.icebreaker.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import team.mosaic.icebreaker.components.Grid;
import team.mosaic.icebreaker.model.BoardModel;
import team.mosaic.icebreaker.modelservice.BoardModelService;
import team.mosaic.icebreaker.view.game.BoardPanel;
import team.mosaic.icebreaker.vo.PositionVO;
/*
 * 色块控制器
 */
public class BoardController implements MouseListener {
	private int count = 0;
	private PositionVO pvo1;
	private PositionVO pvo2;
	private PositionVO pvo3;
	private PositionVO pvo4;
	private BoardPanel bp;
	private Grid[] focusGrids = new Grid[2];
	
	public BoardModelService bms;

	private int mode;
	private int direction;// 0从上到下；1从左到右
	private int character;

	public BoardController(BoardPanel bp) {
		this.bp = bp;
	}

	public BoardController(BoardPanel bp, int mode, int direction, int character) {
		this.bp = bp;
		this.mode = mode;
		this.direction = direction;
		this.character = character;
		bms = new BoardModel(bp, this.mode, this.direction, this.character, bp
				.getParent().getTools());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// bp.swapUnit2(new PositionVO(0, 0), new PositionVO(0, 2));
		PositionVO tmp = getPosition(e.getPoint());
		bp.getGrid(tmp).lighten();
//		if(e.getClickCount() == 2 && !bp.getLock() && !bp.getGrid(tmp).getMovement()){//双击
		if(e.getClickCount() == 2 && !bp.getLock() ){//双击
			bms.useToolGrid(tmp);
		}
		else{
			if (count == 0) {
				pvo1 = tmp;
				focusGrids[0] = bp.getGrid(tmp);
				count++;
				return;
			}
			if (count == 1) {
				pvo2 = tmp;
				focusGrids[1] = bp.getGrid(tmp);
				// if (pvo1.isValid(pvo2)) {
				// 调用model 以下直接调用panel里方法的 一些测试
				// bp.swapUnit(pvo1, pvo2);
	//			if (!bp.getGrid(pvo1).getMovement()
	//					&& !bp.getGrid(pvo2).getMovement())
//				if(bp.getStill())
//				if(!bp.getLock() && (!bp.getGrid(pvo1).getMovement()&& !bp.getGrid(pvo2).getMovement()))
				if(!bp.getLock())
					bms.trySwap(pvo1, pvo2);
				count = 0;
				focusGrids[0].normalize();
				focusGrids[1].normalize();
			} else {
				pvo1 = pvo2;
				// }
			}
		}
	}

	public void initBoard() {
		bp.initBoard(bms.grids());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		pvo3 = getPosition(e.getPoint());
		// System.out.println(pvo1.getX()+","+pvo1.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		pvo4 = getPosition(e.getPoint());
		// System.out.println(pvo2.getX()+","+pvo2.getY());
//		if (!bp.getGrid(pvo3).getMovement() && !bp.getGrid(pvo4).getMovement())
//		if(bp.getStill())
//		if(!bp.getLock() && (!bp.getGrid(pvo3).getMovement()&& !bp.getGrid(pvo4).getMovement()))
		if(!bp.getLock())
			bms.trySwap(pvo3, pvo4);
		// }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private PositionVO getPosition(Point point) {
		return new PositionVO(point.x / BoardPanel.GRID_LENGTH, point.y
				/ BoardPanel.GRID_LENGTH);
	}

}

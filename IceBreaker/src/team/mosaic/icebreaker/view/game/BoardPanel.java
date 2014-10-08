package team.mosaic.icebreaker.view.game;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import team.mosaic.icebreaker.components.Grid;
import team.mosaic.icebreaker.controller.BoardController;
import team.mosaic.icebreaker.sound.SoundEffect;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.viewservice.BoardViewService;
import team.mosaic.icebreaker.vo.MoveandCreateActionVO;
import team.mosaic.icebreaker.vo.PositionVO;

public class BoardPanel extends JPanel implements BoardViewService {

	private static final long serialVersionUID = 5305650758294353227L;

	public static final int GRID_LENGTH = 50;

	private static final int COL = 9;
	private static final int ROW = 9;

	private GamePanel parentPanel;
	public BoardController controller;
	public Grid[][] grids;
	public int[][] dots;

	private static final int dotGap = 10;
	private static final int step = 50;// 移动幅度
	private static final int GAP_TIME = 3;// 间隔时间
	private int direction;
	private JLabel tip1 = new JLabel(new ImageIcon("pic/tip.gif"));
	private JLabel tip2 = new JLabel(new ImageIcon("pic/tip.gif"));
	private ThreadQueue q;
	// private ThreadQueue dq;
	// private ThreadQueue mq;
	private SoundEffect[] se;
	private int soundIndex;
	private ImageIcon ex[] = new ImageIcon[4];
	private boolean actLock;
	private boolean threadLock;

	public BoardPanel(GamePanel gp) {
		// 设置模式
		controller = new BoardController(this);
		// bc=new
		// BoardController(this,gp.getMode(),gp.getDirection(),gp.getCharacter());
		parentPanel = gp;
		grids = new Grid[ROW][COL];
		dots = new int[ROW][COL];
		direction = gp.getDirection();

		se = new SoundEffect[3];
		for (int i = 0; i < 3; i++) {
			se[i] = new SoundEffect(SoundEffect.BREAK);
		}
		soundIndex = 0;
		ex[0] = new ImageIcon("pic/ex1.png");
		ex[1] = new ImageIcon("pic/ex2.png");
		ex[2] = new ImageIcon("pic/ex3.png");
		ex[3] = new ImageIcon("pic/ex4.png");
		init();
	}

	private void init() {
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(COL * GRID_LENGTH, ROW * GRID_LENGTH);
		this.addMouseListener(controller);

		tip1.setSize(GRID_LENGTH, GRID_LENGTH);
		tip2.setSize(GRID_LENGTH, GRID_LENGTH);

		// dq = new ThreadQueue();
		// mq = new ThreadQueue();
		q = new ThreadQueue();
		Thread queueThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// ThreadQueue q = dq;
				int i = 0;
				Thread last = new Thread();
				Thread current = null;
				while (true) {
					if (!last.isAlive()) {
						// if(!threadLock){
						if (current != null) {
							current.start();
							last = current;
							// i++;
							// if(i % 2 == 1)
							// q = mq;
							// else
							// q = dq;
						}
						current = q.dequeue();
					}
				}
			}
		});
		queueThread.start();
	}

	/**
	 * 初始化并绘制消除单元
	 * 
	 * @param
	 */
	@Override
	public void initBoard(int[][] newdot) {
		// TODO Auto-generated method stub
		System.out.println(newdot[0][0] + " " + newdot[1][0]);
		dots = newdot;
		for (int row = 0; row < ROW; row++) {
			for (int col = 0; col < COL; col++) {
				// 绘制不同样式 选中或接近时变化
				grids[row][col] = new Grid();
				grids[row][col].setLocation(row * GRID_LENGTH, col
						* GRID_LENGTH);
				// 设置图片 数组中为1-6
				grids[row][col].setIcon(dots[row][col]);
				this.add(grids[row][col]);
			}
		}
		this.repaint();
	}

	public void swapUnit2(PositionVO p1, PositionVO p2) {
		Thread thread = new Thread(new SwapTwiceRunner(p1, p2), "swap2");
		q.enqueue(thread);
	}

	@Override
	public void swapUnit(PositionVO p1, PositionVO p2) {
		Thread thread = new Thread(new SwapRunner(p1, p2), "swap");
		q.enqueue(thread);
	}

	// 0不变 1代表消除，2代表生成对应的A道具，3代表生成B道具
	@Override
	public void deleteUnits(final int[][] dbChange) {
		Thread thread = new Thread(new RemoveRunner(dbChange), "delete");
		q.enqueue(thread);
	}

	public void dealWithMoveAction(ArrayList<MoveandCreateActionVO> avoList) {
		Thread thread = new Thread(new MoveRunner(avoList), "move");
		q.enqueue(thread);
	}

	// // 0不变 1代表消除，2代表生成对应的A道具，3代表生成B道具
	// @Override
	// public void deleteUnits(final int[][] dbChange) {
	// }
	//
	// public void dealWithMoveAction(final ArrayList<MoveandCreateActionVO>
	// avoList)
	// throws Exception {
	// }

	public GamePanel getParent() {
		return parentPanel;
	}

	@Override
	public void setGrids(int[][] dots) {
		this.dots = dots;
	}

	public void startTiming() {
		controller.bms.startTimer();
	}

	public boolean getStill() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (grids[i][j].getMovement())
					return false;
			}
		}
		return true;
	}

	private class MoveRunner implements Runnable {

		private ArrayList<MoveandCreateActionVO> localList;

		public MoveRunner(ArrayList<MoveandCreateActionVO> list) {
			this.localList = list;
		}

		@Override
		public void run() {
			System.out.println("move start");
			threadLock = true;
			try {
				int gapx[] = new int[localList.size()];
				int gapy[] = new int[localList.size()];
				int x1[] = new int[localList.size()];
				int y1[] = new int[localList.size()];
				int x2[] = new int[localList.size()];
				int y2[] = new int[localList.size()];

				ArrayList<Grid> moveGrids = new ArrayList<>();

				// 计算出各个方块起始位置、目标位置、移动距离
				for (int i = 0; i < localList.size(); i++) {
					MoveandCreateActionVO tmp = localList.get(i);
					int direction = tmp.direction();// 掉落方向
					PositionVO p1 = tmp.gridOld();
					int steps = tmp.steps();
					// 初始位置
					x1[i] = GRID_LENGTH * p1.getX();
					y1[i] = GRID_LENGTH * p1.getY();
					// 目的位置
					x2[i] = 0;
					y2[i] = 0;
					if (direction == 0) {// 上方掉落
						x2[i] = x1[i];
						y2[i] = y1[i] + steps * GRID_LENGTH;
					} else {// 左侧掉落
						y2[i] = y1[i];
						x2[i] = x1[i] + steps * GRID_LENGTH;
					}
					gapx[i] = (x2[i] - x1[i]) / step;
					gapy[i] = (y2[i] - y1[i]) / step;

					Grid grid = null;
					if (tmp.type() == 0) {
						grid = grids[p1.getX()][p1.getY()];// 找不到对象？！第一次move在第二次delete之后？
						// delete move之间时间要缩短
						moveGrids.add(grid);
					} else {
						grid = new Grid();
						grid.setIcon(tmp.color());
						moveGrids.add(grid);
						BoardPanel.this.add(grid);
					}
					// 更新方块位置
					int x = x2[i] / GRID_LENGTH;
					int y = y2[i] / GRID_LENGTH;
					grids[x][y] = grid;
				}

				// 一起移动
				for (int i = 0; i < step; i++) {
					for (int j = 0; j < moveGrids.size(); j++) {
						Grid tmp = moveGrids.get(j);
						x1[j] += gapx[j];
						y1[j] += gapy[j];
						tmp.setLocation(x1[j], y1[j]);// 报错

//						if(i==step-1)
//							tmp.setMovement(false);
					}
					// BoardPanel.this.repaint();
					Thread.sleep(1);
				}
				// //更新各方块位置
				// for (int i = 0; i < moveGrids.size(); i++){
				// Grid tmp = moveGrids.get(i);
				// int x = x2[i] / GRID_LENGTH;
				// int y = y2[i] / GRID_LENGTH;
				// grids[x][y] = tmp;
				// }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BoardPanel.this.repaint();
			BoardPanel.this.validate();
			System.out.println("move end");
			threadLock = false;
			actLock = false;
		}
	}

	private class RemoveRunner implements Runnable {

		private int[][] localChange;

		public RemoveRunner(int[][] change) {
			this.localChange = change;
		}

		@Override
		public void run() {
			System.out.println("delete start");
			actLock = true;
			threadLock = true;
			soundIndex++;
			soundIndex %= 3;
			se[soundIndex].play();// 音效

			ArrayList<Grid> deleteList = new ArrayList<>();

			for (int i = 0; i < COL; i++) {
				for (int j = 0; j < ROW; j++) {
					int change = localChange[i][j];
					if (change == 0)
						continue;
					if (change == 1) {
						// 消除
//						 if(grids[i][j]!=null){
//						 BoardPanel.this.remove(grids[i][j]);
						deleteList.add(grids[i][j]);
						grids[i][j] = null;
						// }
//						if (direction == 0 && j > 0) {
//							for (int k = 0; k < j; k++) {
//								if (grids[i][k] != null)
//									grids[i][k].setMovement(true);
//							}
//						} else if (direction == 1 && i > 0) {
//							for (int k = 0; k < i; k++) {
//								if (grids[k][j] != null) {
//									grids[k][j].setMovement(true);
//								}
//							}
//						}
					} else if(change == 2){
						if (grids[i][j] != null)
							grids[i][j].setIcon(-Math.abs(dots[i][j]));
					}else if(change == 3){
						if (grids[i][j] != null)
							grids[i][j].setIcon(7);
					}
				}
			}
			//消除效果
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < deleteList.size(); j++) {
					deleteList.get(j).setIcon(ex[i % 4]);
					if (i == 7)
						BoardPanel.this.remove(deleteList.get(j));
				}
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			BoardPanel.this.repaint();
			BoardPanel.this.validate();
			System.out.println("delete end");
			threadLock = false;
		}
	}

	private class SwapRunner implements Runnable {

		private PositionVO p1;
		private PositionVO p2;

		public SwapRunner(PositionVO p1, PositionVO p2) {
			this.p1 = p1;
			this.p2 = p2;
		}

		@Override
		public void run() {
			actLock = true;
			threadLock = true;
			try {
				Grid b1 = grids[p1.getX()][p1.getY()];
				Grid b2 = grids[p2.getX()][p2.getY()];

				// 先交换grids中位置
				grids[p2.getX()][p2.getY()] = b1;
				grids[p1.getX()][p1.getY()] = b2;

				int x1 = GRID_LENGTH * p1.getX();
				int y1 = GRID_LENGTH * p1.getY();
				int x2 = GRID_LENGTH * p2.getX();
				int y2 = GRID_LENGTH * p2.getY();

				// 变换次数
				final int gapx = (x2 - x1) / step;
				final int gapy = (y2 - y1) / step;
				for (int i = 0; i <= step; i++, x1 += gapx, x2 -= gapx, y1 += gapy, y2 -= gapy) {
					b1.setLocation(x1, y1);
					b2.setLocation(x2, y2);
					Thread.sleep(GAP_TIME);
				}
				BoardPanel.this.repaint();
				BoardPanel.this.validate();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			threadLock = false;
//			actLock = false;
		}
	}

	private class SwapTwiceRunner implements Runnable {

		private PositionVO p1;
		private PositionVO p2;

		public SwapTwiceRunner(PositionVO p1, PositionVO p2) {
			this.p1 = p1;
			this.p2 = p2;
		}

		@Override
		public void run() {
			threadLock = true;
			try {
				Grid b1 = grids[p1.getX()][p1.getY()];
				Grid b2 = grids[p2.getX()][p2.getY()];
				int x1 = GRID_LENGTH * p1.getX();
				int x2 = GRID_LENGTH * p2.getX();
				int y1 = GRID_LENGTH * p1.getY();
				int y2 = GRID_LENGTH * p2.getY();
				// 变换次数
				int gapx = (x2 - x1) / step;
				int gapy = (y2 - y1) / step;
				for (int i = 0; i <= step; i++, x1 += gapx, x2 -= gapx, y1 += gapy, y2 -= gapy) {
					b1.setLocation(x1, y1);
					b2.setLocation(x2, y2);
					Thread.sleep(GAP_TIME);
				}
				BoardPanel.this.repaint();
				BoardPanel.this.validate();
				for (int i = 0; i <= step + 1; i++, x1 -= gapx, x2 += gapx, y1 -= gapy, y2 += gapy) {
					b1.setLocation(x1, y1);
					b2.setLocation(x2, y2);
					Thread.sleep(GAP_TIME);
				}
				// grids[p1.getX()][p1.getY()] = b1;
				// grids[p2.getX()][p2.getY()] = b2;
				BoardPanel.this.repaint();
				BoardPanel.this.validate();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			threadLock = false;
		}
	}

	@Override
	public void prompt(PositionVO[] pvo) {
		// TODO Auto-generated method stub
		tip1.setLocation(pvo[0].getX() * GRID_LENGTH, pvo[0].getY()
				* GRID_LENGTH);
		tip2.setLocation(pvo[1].getX() * GRID_LENGTH, pvo[1].getY()
				* GRID_LENGTH);
		this.add(tip1);
		this.add(tip2);
		this.repaint();
	}

	@Override
	public void noprompt() {
		// TODO Auto-generated method stub
		this.remove(tip1);
		this.remove(tip2);
		this.repaint();
	}

	@Override
	public void iceCombo() {
		// TODO Auto-generated method stub
		Prompt.showMessage(parentPanel.getParentFrame(), "冰雪连击");
	}

	public Grid getGrid(PositionVO pvo) {
		// TODO Auto-generated method stub
		return grids[pvo.getX()][pvo.getY()];
	}

	public boolean getLock() {
		return this.actLock;
	}

	@Override
	public void lock(int color) {
		// TODO Auto-generated method stub
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				if (Math.abs(dots[i][j]) == color) {
					grids[i][j].setEnabled(false);
				}
			}
		}
	}

	@Override
	public void unlock(int color) {
		// TODO Auto-generated method stub
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				if (Math.abs(dots[i][j]) == color) {
					grids[i][j].setEnabled(true);
				}
			}
		}
	}

}

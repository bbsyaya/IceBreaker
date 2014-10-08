package team.mosaic.icebreaker.playerstructure;

import java.io.IOException;

import team.mosaic.icebreaker.vo.MessageVO;
import team.mosaic.icebreaker.vo.SwapActionVO;

public class CooperationPair {

	private int mark;// 用于同步用户操作；

	private Player p1;// p1默认为主机
	private Player p2;
	private int endCount;
	private boolean end1;
	private boolean end2;

	/**
	 * 构造函数，建立协作关系（由第二玩家执行）
	 * 
	 * @param p1
	 *            玩家1
	 * @param p2
	 *            玩家2
	 */
	public CooperationPair(Player p1, Player p2) {
		mark = 0;
		endCount = 0;
		end1 = false;
		end2 = false;
		this.p1 = p1;
		this.p2 = p2;
		p1.setHost(true);
		link();
	}

	public CooperationPair(Player p1, Player p2, Player p3, Player p4) {
		mark = 0;
		this.p1 = p1;
		this.p2 = p2;
		p1.setHost(true);
		p1.setCooperationPair(this);
		try {
			System.out.println("main:" + p1.getID());
			System.out.println("sub:" + p2.getID());
			p1.sendMessage(new MessageVO("findpaircomp", new String[] {
					p1.getID(),p2.getID(), p3.getID(), p4.getID() }));
			p2.sendMessage(new MessageVO("findpaircomp", new String[] {
					p2.getID(),p1.getID(), p3.getID(), p4.getID() }));
			p1.setFree(false);
			p2.setFree(false);
			p1.sendMessage(new MessageVO("sethost", null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 与第一玩家建立联系
	 */
	private void link() {
		p1.setCooperationPair(this);
		try {
			System.out.println("main:" + p1.getID());
			System.out.println("sub:" + p2.getID());
			p1.sendMessage(new MessageVO("findpair", p2.getID()));
			p2.sendMessage(new MessageVO("findpair", p1.getID()));
			p1.setFree(false);
			p2.setFree(false);
			p1.sendMessage(new MessageVO("sethost", null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 接到主机的初始化盘面后发给另一个玩家
	 * 
	 * @param grids
	 */
	public void init(Object obj) {
		try {
			p2.sendMessage(new MessageVO("sendqueue", obj));
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(2000);
						p1.sendMessage(new MessageVO("startcoop", null));
						p2.sendMessage(new MessageVO("startcoop", null));
					} catch (InterruptedException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			thread.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 断开协作连接
	 */
	public void dismiss() {
		p1.setCooperationPair(null);
		p1.setHost(false);
		p1.setFree(true);
		p2.setCooperationPair(null);
		p2.setHost(false);
		p2.setFree(true);
	}

	/**
	 * 同步用户游戏操作
	 */
	public void synchronize(SwapActionVO svo) {
		if (svo.getMark() == this.mark) {// 同步成功
			try {
				p1.sendMessage(new MessageVO("act", svo));
				p2.sendMessage(new MessageVO("act", svo));
				mark++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void quit(Player player) {
		player.setFree(true);
		player.setCooperationPair(null);
		try {
			if (player.getID().equals(p1.getID())) {
				end1 = true;
				p1.setHost(false);
				p2.setHost(true);
				if (endCount == 0)
					p2.sendMessage(new MessageVO("quit", p1.getID()));
			} else if (player.getID().equals(p2.getID())) {
				end2 = true;
				if (endCount == 0)
					p1.sendMessage(new MessageVO("quit", p2.getID()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endCount++;
		if (endCount == 2)
			dismiss();
	}

	public void randomLock() {
		try {
			if (!end1)
				p1.sendMessage(new MessageVO("randomLock", null));
			if (!end2)
				p2.sendMessage(new MessageVO("randomLock", null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lockC() {
		try {
			if (!end1)
				p1.sendMessage(new MessageVO("lockc", null));
			if (!end2)
				p2.sendMessage(new MessageVO("lockc", null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

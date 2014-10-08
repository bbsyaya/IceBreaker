package team.mosaic.icebreaker.playerstructure;

import java.io.IOException;

import team.mosaic.icebreaker.vo.MessageVO;
import team.mosaic.icebreaker.vo.SwapActionVO;

public class CooperationPair {

	private int mark;// ����ͬ���û�������

	private Player p1;// p1Ĭ��Ϊ����
	private Player p2;
	private int endCount;
	private boolean end1;
	private boolean end2;

	/**
	 * ���캯��������Э����ϵ���ɵڶ����ִ�У�
	 * 
	 * @param p1
	 *            ���1
	 * @param p2
	 *            ���2
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
	 * ���һ��ҽ�����ϵ
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
	 * �ӵ������ĳ�ʼ������󷢸���һ�����
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
	 * �Ͽ�Э������
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
	 * ͬ���û���Ϸ����
	 */
	public void synchronize(SwapActionVO svo) {
		if (svo.getMark() == this.mark) {// ͬ���ɹ�
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

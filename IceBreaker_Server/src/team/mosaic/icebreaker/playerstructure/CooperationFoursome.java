package team.mosaic.icebreaker.playerstructure;

import java.io.IOException;

import team.mosaic.icebreaker.vo.MessageVO;
import team.mosaic.icebreaker.vo.SwapActionVO;

public class CooperationFoursome {

	private int mark;
	private int endCount;
	private Player p1;
	private Player p2;
	private Player p3;
	private Player p4;
	private boolean end1;
	private boolean end2;
	private boolean end3;
	private boolean end4;
	private MessageVO findMessage;
	
	public CooperationFoursome(Player p1,Player p2,Player p3,Player p4){
		mark = 0;
		endCount = 0;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		p1.setHost(true);
		findMessage = new MessageVO("findgroup",new String[]{p1.getID(), p2.getID(), p3.getID(), p4.getID()});
		link();
	}
	/**
	 * 四个玩家建立连接
	 */
	private void link(){
		p1.setCooperationFoursome(this);
		p2.setCooperationFoursome(this);
		p3.setCooperationFoursome(this);
		p4.setCooperationFoursome(this);
		try {
			p1.sendMessage(findMessage);
			p2.sendMessage(findMessage);
			p3.sendMessage(findMessage);
			p4.sendMessage(findMessage);
			p1.setFree(false);
			p2.setFree(false);
			p3.setFree(false);
			p4.setFree(false);
			p1.sendMessage(new MessageVO("sethost", null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 接到主机的初始化盘面后发给其他玩家
	 * @param grids
	 */
	public void init(Object obj){
		try {
			p2.sendMessage(new MessageVO("sendqueue", obj));
			p3.sendMessage(new MessageVO("sendqueue", obj));
			p4.sendMessage(new MessageVO("sendqueue", obj));
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(2000);
						p1.sendMessage(new MessageVO("startcoopfour", null));
						p2.sendMessage(new MessageVO("startcoopfour", null));
						p3.sendMessage(new MessageVO("startcoopfour", null));
						p4.sendMessage(new MessageVO("startcoopfour", null));
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
	 * 断开连接
	 */
	public void dismiss(){
		p1.setCooperationFoursome(null);
		p2.setCooperationFoursome(null);
		p3.setCooperationFoursome(null);
		p4.setCooperationFoursome(null);
		p1.setHost(false);
		p2.setHost(false);
		p3.setHost(false);
		p4.setHost(false);
		p1.setFree(true);
		p2.setFree(true);
		p3.setFree(true);
		p4.setFree(true);
	}
	/**
	 * 同步用户游戏操作
	 */
	public void synchronize(SwapActionVO svo){
		if(svo.getMark()==this.mark){//同步成功
			try {
				p1.sendMessage(new MessageVO("act", svo));
				p2.sendMessage(new MessageVO("act", svo));
				p3.sendMessage(new MessageVO("act", svo));
				p4.sendMessage(new MessageVO("act", svo));
				mark ++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void quit(Player player){
		player.setCooperationFoursome(null);
		player.setFree(true);
		changeHost(player);
		try {
			if(player.getID().equals(p1.getID())){
				end1 = true;
				if(endCount < 3)
					if(!end2)
						p2.sendMessage(new MessageVO("quit", p1.getID()));
					if(!end3)
						p3.sendMessage(new MessageVO("quit", p1.getID()));
					if(!end4)
						p4.sendMessage(new MessageVO("quit", p1.getID()));
			}
			else if(player.getID().equals(p2.getID())){
				end2 = true;
				if(endCount < 3)
					if(!end1)
						p1.sendMessage(new MessageVO("quit", p2.getID()));
					if(!end3)
						p3.sendMessage(new MessageVO("quit", p2.getID()));
					if(!end4)
						p4.sendMessage(new MessageVO("quit", p2.getID()));
			}
			else if(player.getID().equals(p3.getID())){
				end3 = true;
				if(endCount < 3)
					if(!end1)
						p1.sendMessage(new MessageVO("quit", p3.getID()));
					if(!end2)
						p2.sendMessage(new MessageVO("quit", p3.getID()));
					if(!end4)
						p4.sendMessage(new MessageVO("quit", p3.getID()));
			}
			else if(player.getID().equals(p4.getID())){
				end4 = true;
				if(endCount < 3)
					if(!end1)
						p1.sendMessage(new MessageVO("quit", p4.getID()));
					if(!end2)
						p2.sendMessage(new MessageVO("quit", p4.getID()));
					if(!end3)
						p3.sendMessage(new MessageVO("quit", p4.getID()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endCount ++;
		if(endCount == 4)
			dismiss();
	}
	
	private void changeHost(Player player){
		if(!player.getHost())
			return;
		if(endCount < 3){
			if(!end2 && !p2.getHost())
				p2.setHost(true);
			else if(!end3 && !p3.getHost())
				p3.setHost(true);
			else if(!end4 && !p4.getHost())
				p4.setHost(true);
		}
		player.setHost(false);
	}
	
}

package team.mosaic.icebreaker.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import team.mosaic.icebreaker.playerstructure.Player;
import team.mosaic.icebreaker.playerstructure.RandomQueue;

public class MyServerSocket {

	private ServerSocket ss;
	private Socket s;
	private ArrayList<Player> sockets;//在线玩家列表
	private int count;//在线人数
	private ServerPanel ui;
	private MyServerSocket self;
	private boolean isSetUp;//是否成功启动
	private RandomQueue coopQueue;//协作匹配队列
	private RandomQueue pkQueue;//pk匹配队列
	private RandomQueue foursomeQueue;//4人协作队列
	private RandomQueue compfourQueue;

	public MyServerSocket(ServerPanel ui) {
		this.ui = ui;
		self = this;
		coopQueue = new RandomQueue(0);
		pkQueue = new RandomQueue(1);
		foursomeQueue = new RandomQueue(2);
		compfourQueue = new RandomQueue(3);
		try {
			ss = new ServerSocket(8888);
			sockets = new ArrayList<>();
			listenClient();
			isSetUp = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 监听客户端连接请求
	 */
	private void listenClient() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						s = ss.accept();
						System.out.println("已连接" + s.getPort() + " " + count);
						Player p = new Player(self, s);
						p.start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}
	/**
	 * 寻找玩家
	 * @param id 玩家id
	 * @return 玩家对象
	 */
	public Player getPlayer(String id){
		for(Player p:sockets){
			if(p.getID().equals(id))
				return p;
		}
		return null;
	}
	/**
	 * 关闭服务器
	 */
	public void shut(){
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 得到启动状态
	 * @return 启动状态
	 */
	public boolean getState(){
		return isSetUp;
	}
	/**
	 * 在线人数增加
	 */
	private void increCount(){
		count ++;
		ui.updateCount(count);
	}
	/**
	 * 在线人数减少
	 */
	private void decreCount(){
		count --;
		ui.updateCount(count);
	}
	/**
	 * 增添在线玩家
	 * @param st 玩家
	 */
	public void addMember(Player st){
		increCount();
		sockets.add(st);
	}
	/**
	 * 移除在线玩家
	 * @param st 玩家
	 */
	public void removeMember(Player st){
		if(st.getID()!=null)
			decreCount();
		sockets.remove(st);
	}
	/**
	 * 得到当前在线人数
	 * @return 人数
	 */
	public int getCount(){
		return count;
	}
	
	public Player getPlayerByIndex(int index){
		return sockets.get(index);
	}
	
	public RandomQueue getCoopQueue(){
		return coopQueue;
	}
	
	public RandomQueue getPkQueue(){
		return pkQueue;
	}
	
	public RandomQueue getFoursomeQueue(){
		return foursomeQueue;
	}
	
	public RandomQueue getCompfourQueue(){
		return this.compfourQueue;
	}
	
}

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
	private ArrayList<Player> sockets;//��������б�
	private int count;//��������
	private ServerPanel ui;
	private MyServerSocket self;
	private boolean isSetUp;//�Ƿ�ɹ�����
	private RandomQueue coopQueue;//Э��ƥ�����
	private RandomQueue pkQueue;//pkƥ�����
	private RandomQueue foursomeQueue;//4��Э������
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
	 * �����ͻ�����������
	 */
	private void listenClient() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						s = ss.accept();
						System.out.println("������" + s.getPort() + " " + count);
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
	 * Ѱ�����
	 * @param id ���id
	 * @return ��Ҷ���
	 */
	public Player getPlayer(String id){
		for(Player p:sockets){
			if(p.getID().equals(id))
				return p;
		}
		return null;
	}
	/**
	 * �رշ�����
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
	 * �õ�����״̬
	 * @return ����״̬
	 */
	public boolean getState(){
		return isSetUp;
	}
	/**
	 * ������������
	 */
	private void increCount(){
		count ++;
		ui.updateCount(count);
	}
	/**
	 * ������������
	 */
	private void decreCount(){
		count --;
		ui.updateCount(count);
	}
	/**
	 * �����������
	 * @param st ���
	 */
	public void addMember(Player st){
		increCount();
		sockets.add(st);
	}
	/**
	 * �Ƴ��������
	 * @param st ���
	 */
	public void removeMember(Player st){
		if(st.getID()!=null)
			decreCount();
		sockets.remove(st);
	}
	/**
	 * �õ���ǰ��������
	 * @return ����
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

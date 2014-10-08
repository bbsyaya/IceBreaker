package team.mosaic.icebreaker.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import team.mosaic.icebreaker.file.FileIO;
import team.mosaic.icebreaker.modelservice.ClientService;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.vo.AccountVO;
import team.mosaic.icebreaker.vo.MessageVO;

public class ClientSocket {
	private static ClientSocket cs;// self
	private static Socket s;// socket

	private String host;// host
	private static int port = 8888;// port

	private ObjectOutputStream oos;// write object
	private static ClientThread ct;// listen thread
	// models
	private ClientService service;

	/**
	 * give the object
	 * 
	 * @return singleton
	 */
	public static ClientSocket getInstance() {
		if (cs == null)
			cs = new ClientSocket();
		return cs;
	}

	/**
	 * private constructor
	 */
	private ClientSocket() {
		try {
			getConfig();//set host & port;
			s = new Socket(host, port);
			oos = new ObjectOutputStream(s.getOutputStream());
			ct = new ClientThread(s, this);
			ct.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "无法连接到服务器！");
//			Prompt.showWarning(null, "无法连接到服务器！");
			System.exit(0);
			e.printStackTrace();
		}
	}

	/**
	 * stop socket
	 */
	@SuppressWarnings("deprecation")
	static void shutDown() {
		try {
			ct.stop();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void setClientModel(ClientService cs) {
		this.service = cs;
		ct.setClientModel(cs);
	}

	ClientService getClientModel() {
		return service;
	}
	/**
	 * 
	 */

	/**
	 * send message
	 * 
	 * @param mvo
	 *            message object
	 */
	public void sendMessage(MessageVO mvo) {
		try {
			oos.writeObject(mvo);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * read local file, set host & port
	 */
	private void getConfig() {
		ArrayList<String> configList = FileIO.read(FileIO.NET_FILE);
		String host = null;
		for (String s : configList) {
			if (s.contains("host"))
				host = s;
		}
		String[] split = host.split(":");
		this.host = split[1];
	}
	
	public static void main(String[] args){
		ClientSocket test = getInstance();
		test.sendMessage(new MessageVO("signin", new AccountVO("player1", "pwd")));
		test.sendMessage(new MessageVO("cooprandom",null));
		
		//test.sendMessage(new MessageVO("coopwithpal", "player2"));
		
	}

}

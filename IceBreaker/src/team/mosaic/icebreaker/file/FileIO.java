package team.mosaic.icebreaker.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/*
 * 读取文件类
 */
public class FileIO {

	private static final File CONFIG_FILE = new File("info/config.data");
	public static final File ACCOUNT_FILE = new File("info/account.data");
	private static final File AUTO_FILE=new File("info/auto.data");
	public static final File NET_FILE = new File("info/net.data");
	public static final File HELP_FILE = new File("info/help.data");
	public static final File RECORD_FILE = new File("info/record.data");


	/**
	 * 读取配置文件
	 * @return
	 */
	public static ArrayList<String> getConfig(){
		return read(CONFIG_FILE);
	}
	
	/**
	 * 读取网络环境
	 * @return
	 */
	public static ArrayList<String> getnet(){
		return read(NET_FILE);
	}

	/**
	 * 回写配置文件
	 * @param content
	 */
	public static void writeConfig(String content){
		ArrayList<String> list = new ArrayList<>();
		list.add(content);
		write(CONFIG_FILE,list);
	}


	/**
	 * 读取账号方法
	 * @return
	 */
	public static ArrayList<String> readAccount(){
		return read(ACCOUNT_FILE);		
	}

	public static ArrayList<String> readAuto(){
		return read(AUTO_FILE);		
	}	

	/**
	 * 写回文件方法
	 * @param f
	 * @param content
	 */
	public static void write(File f,ArrayList<String> content){
		BufferedWriter out = null;                                                   
		BufferedWriter fout = null;
		try {                                                                        
			out = new BufferedWriter(new FileWriter(f, true));
			fout = new BufferedWriter(new FileWriter(f, false));
			fout.write("");
			for(String line:content){
				out.write(line);
				out.write('\n');
			}
		} catch (Exception e) {                                                     
			e.printStackTrace();                                                    
		} finally {                                                                 
			try {                                                                    
				out.close();
				fout.close();
			} catch (IOException e) {                                               
				e.printStackTrace();                                                
			}                                                                       
		}                    		
	}

	/**
	 * 从文件中读取
	 * @param f
	 * @return
	 */
	public static ArrayList<String> read(File f){
		ArrayList<String> ret = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = null;
			while((line = br.readLine())!=null){
				ret.add(line);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
}

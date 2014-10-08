package team.mosaic.icebreaker.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/*
 * ��ȡ�ļ���
 */
public class FileIO {

	private static final File CONFIG_FILE = new File("info/config.data");
	public static final File ACCOUNT_FILE = new File("info/account.data");
	private static final File AUTO_FILE=new File("info/auto.data");
	public static final File NET_FILE = new File("info/net.data");
	public static final File HELP_FILE = new File("info/help.data");
	public static final File RECORD_FILE = new File("info/record.data");


	/**
	 * ��ȡ�����ļ�
	 * @return
	 */
	public static ArrayList<String> getConfig(){
		return read(CONFIG_FILE);
	}
	
	/**
	 * ��ȡ���绷��
	 * @return
	 */
	public static ArrayList<String> getnet(){
		return read(NET_FILE);
	}

	/**
	 * ��д�����ļ�
	 * @param content
	 */
	public static void writeConfig(String content){
		ArrayList<String> list = new ArrayList<>();
		list.add(content);
		write(CONFIG_FILE,list);
	}


	/**
	 * ��ȡ�˺ŷ���
	 * @return
	 */
	public static ArrayList<String> readAccount(){
		return read(ACCOUNT_FILE);		
	}

	public static ArrayList<String> readAuto(){
		return read(AUTO_FILE);		
	}	

	/**
	 * д���ļ�����
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
	 * ���ļ��ж�ȡ
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

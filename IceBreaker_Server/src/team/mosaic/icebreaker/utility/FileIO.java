package team.mosaic.icebreaker.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {

	public static final File TOOL_FILE = new File("info/tools_info");
	public static final File CHARACTER_FILE = new File("info/characters_info");
	private File file;
	private ArrayList<String> list;

	public FileIO(File file) {
		this.file = file;
		list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getValue(String index) {
		for (String l : list) {
			String[] split = l.split(":");
			if (split[0].equals(index))
				return split[1];
		}
		return null;
	}

	public ArrayList<String> getList() {
		return this.list;
	}

}

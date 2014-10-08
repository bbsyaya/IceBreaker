package team.mosaic.icebreaker.file;

import javax.swing.ImageIcon;

/**
 * 人物枚举类
 * @author acer
 *
 */
public class Portrait {

	public static final int ANNA = 0;
	public static final int KRISTOFF = 1;
	public static final int SVEN = 2;
	public static final int ELSA = 3;
	
	private static final ImageIcon Anna = new ImageIcon("pic/Anna.jpg");
	private static final ImageIcon Kristoff = new ImageIcon("pic/Kristoff.jpg");
	private static final ImageIcon Sven = new ImageIcon("pic/Sven.jpg");
	private static final ImageIcon Elsa = new ImageIcon("pic/Elsa.jpg");
	private static final ImageIcon Default = new ImageIcon("pic/Default.jpg");
	
	public static ImageIcon getHeadPortrait(int index){
		switch (index) {
		case 0:
			return Anna;
		case 1:
			return Kristoff;
		case 2:
			return Sven;
		case 3:
			return Elsa;
		default:
			return Anna;
		}
	}
	
	public static ImageIcon getDefault(){
		return Anna;
	}
	
}

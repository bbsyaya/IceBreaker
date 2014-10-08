package team.mosaic.icebreaker.components;

import java.awt.Color;
import java.util.HashMap;

public class ColorMap {
	public static final String MAGENTA = "magenta";//洋红
	public static final String PURPLE = "purple";//紫色
	public static final String TEAL = "teal";//青色
	public static final String LIME = "lime";//绿黄色
	public static final String BROWN = "brown";//灰色
	public static final String PINK= "pink";//粉红
	public static final String ORANGE = "orange";//橙色
	public static final String BLUE = "blue";//蓝色
	public static final String RED = "red";//红色
	public static final String MARINE = "marine";//海蓝色
	public static final String BG = "bg";
	/*HashMap存放：Key(键):String，   Value(值)：Object类型的数据（任意类型）的数据*/
	public static HashMap<String, Color> colors;
	
	private static void initial(){
		colors = new HashMap<String, Color>();
		colors.put("magenta", new Color(0xff0097));
		colors.put("purple", new Color(0x5859b9));
		colors.put("teal", new Color(0x00aba9));
		colors.put("lime", new Color(0xf96b232));
		colors.put("brown", new Color(0xa05000));
		colors.put("pink", new Color(0xe671bb));
		colors.put("orange", new Color(0xde9317));
		colors.put("blue", new Color(0x1ba1e2));
		colors.put("red", new Color(0xb51400));
		colors.put("green", new Color(0x339933));
		colors.put("marine", new Color(0x034888));
		colors.put("bg", new Color(0x111232));
	}
	
	public static Color getColor(String name){
		if (colors == null) initial();
		return colors.get(name);
	}
	
	public static Color lighter(Color color){//调高颜色亮度
		int r = color.getRed()+0x0a;
		int g = color.getGreen()+0x0a;
		int b = color.getBlue()+0x0a;
		
		if (r>0xff) r= 0xff;
		if (g>0xff) g= 0xff;
		if (b>0xff) b= 0xff;
		
		return new Color(r, g, b);
	}
	
	public static Color darker(Color color){//调低颜色亮度
		int r = color.getRed()-0x0A;
		int g = color.getGreen()-0x0A;
		int b = color.getBlue()-0x0A;
		
		if (r<0x00) r= 0x00;
		if (g<0x00) g= 0x00;
		if (b<0x00) b= 0x00;
		
		return new Color(r, g, b);
	}
}
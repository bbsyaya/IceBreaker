package team.mosaic.icebreaker.file;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 *�и��ȡͼƬ��
 */
public class SubImg {
	//URLs
	public static final String BALL = "pic/ball.png";
	public static final String BACKGROUND = "pic/bg.png";
	public static final String ADD_FRIEND = "pic/add_friend.png";
	public static final String BORDER = "pic/border.png";
	public static final String CHARACTER = "pic/characters.png";
	public static final String GAME_BACKGROUND = "pic/gamebg.png";
	public static final String GRID = "pic/grids.png";
	public static final String OPTION = "pic/options.png";
	public static final String SCORE = "pic/score.png";
	public static final String TOOL_A = "pic/toolA.png";
	public static final String TOOL_B = "pic/toolB.png";
	public static final String TOOL_C = "pic/toolC.png";
	public static final String TOOL_D = "pic/toolD.png";
	public static final String TOOL_E = "pic/toolE.png";
	public static final String TOOL_F = "pic/toolF.png";
	public static final String TOOLC_LARGE = "pic/toolC_large.png";
	public static final String TOOLD_LARGE = "pic/toolD_large.png";
	public static final String TOOLE_LARGE = "pic/toolE_large.png";
	public static final String TOOLF_LARGE = "pic/toolF_large.png";
	public static final String ADD_BUTTON = "pic/add.png";
	public static final String MODE_BUTTON = "pic/mode_buttons.png";
	public static final String ARROW = "pic/arrow.png";
	public static final String SWITCH = "pic/switch.png";
	public static final String CHARACTER_SMALL = "pic/characters_small.png";
	public static final String BUTTON_INFO = "pic/button_info.png";
	public static final String INFO_BG = "pic/info_bg.png";
	public static final String HELP = "pic/help.png";
	public static final String MUSICALNOTE = "pic/musicalnote.png";
	public static final String BACK = "pic/back.png";
	public static final String coin = "pic/coin.png";
	public static final String exp = "pic/exp.png";
	public static final String lv = "pic/lv.png";
	public static final String id = "pic/id.png";
	public static final String SHINY_GRID = "pic/gridslight.png";
	public static final String SHINY_TOOL_A = "pic/toolAlight.png";
	public static final String SHINY_B = "pic/toolBlight.png";
	public static final String DOLLAR = "pic/dollar.png";
	
	private BufferedImage bi;
	private int width;
	private int height;
	
	/*
	 * ��ȡͼƬ
	 */
	public SubImg(String url,int w,int h){
		try {
			bi = ImageIO.read(new File(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width = w;
		height = h;
	}
	
	/*
	 * ��ֱ��ȡͼƬ����
	 */
	public Image getImage(int i){
		return  bi.getSubimage(0, i*height, width, height);
	}
	
	/*
	 * ˮƽ��ȡͼƬ����
	 */
	public Image getHorImage(int i){
		return  bi.getSubimage(i*width, 0, width, height);
	}
	
}

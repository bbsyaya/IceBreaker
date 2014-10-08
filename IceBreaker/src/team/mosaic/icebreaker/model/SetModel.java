package team.mosaic.icebreaker.model;

import team.mosaic.icebreaker.modelservice.SetModelService;
import team.mosaic.icebreaker.sound.BGMusic;

/**
 * ��Ϸ����ģ����
 * @author acer
 *
 */
public class SetModel implements SetModelService {
	public static int direction;
	public static int music;
	public static int sound;
//	private AudioClip ac = BGMusic.getAudioClip();
	
	public SetModel(){
		
	}
	public SetModel(int music,int sound,int direction){
		this.music = music;
		this.sound = sound;
		this.direction = direction;
	}
	
	/**
	 * ������Ч
	 */
	@Override
	public void setSound(int i){
		this.sound = i;
	}

	/**
	 * ��������
	 */
	@Override
	public void setMusic(int music) {
		// TODO Auto-generated method stub
//		System.out.println(music);
		this.music = music;
		if(music>0)
			BGMusic.play(BGMusic.BGM1);
		else
			BGMusic.stop();
	}

}

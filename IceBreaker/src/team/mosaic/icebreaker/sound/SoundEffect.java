package team.mosaic.icebreaker.sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

import team.mosaic.icebreaker.model.SetModel;

/**
 * Ïû³ýÒôÐ§
 * @author acer
 *
 */
public class SoundEffect {

	public static final String BREAK = "sound/break.wav";
	private AudioClip ac;

	@SuppressWarnings("deprecation")
	public SoundEffect(String url){
		try {
			this.ac = Applet.newAudioClip(new File(url).toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play() {
		if(SetModel.sound > 0)
			ac.play();
	}

	public void stop() {
		if(ac != null)
			ac.stop();
	}
	
}

package team.mosaic.icebreaker.sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

import team.mosaic.icebreaker.model.SetModel;

/**
 * ÓÎÏ·ÒôÀÖ
 * @author acer
 *
 */
public class BGMusic {

	public static final String BGM1 = "sound/In Summer.wav";
	private static AudioClip ac;

	@SuppressWarnings("deprecation")
	public static void play(final String s) {
		try {
			ac = Applet.newAudioClip(new File(s).toURL());
			if (SetModel.music > 0) {
				ac.loop();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void stop() {
		if(ac != null)
			ac.stop();
	}

	public static AudioClip getAudioClip() {
		return ac;
	}

}

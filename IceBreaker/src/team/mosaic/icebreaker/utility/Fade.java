package team.mosaic.icebreaker.utility;

import java.awt.Window;

public class Fade {

	public static void fadeIn(final Window instance) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					float f = 0;
					for (int i = 0; i < 10; ++i) {
						f = 0.1f * i + 0.1f;
						instance.setOpacity(f);
						Thread.sleep(40);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	public static void fadeOut(final Window instance) {
		Loading.regainLoading();
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					float f = 0;
					for (int i = 0; i < 10; ++i) {
						f = 1.0f - 0.1f * i;
						instance.setOpacity(f);
						Thread.sleep(40);
					}
					instance.dispose();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
}

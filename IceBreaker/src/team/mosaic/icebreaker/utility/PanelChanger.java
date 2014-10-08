package team.mosaic.icebreaker.utility;

import java.awt.Container;

import javax.swing.JPanel;

public class PanelChanger {

	private static final int FRAME = 20;// panel移动次数
	private static final int GAP_TIME = 5;// 间隔时间

	public static void slideLeft(final JPanel p1, final JPanel p2,
			final Container container) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Loading.addLoading();
				try {
					int width = p1.getWidth();
					p2.setLocation(width, 0);
					container.add(p2);
					int x1 = 0;
					int x2 = width;
					int step = width / FRAME;
					for (int i = 0; i <= FRAME; i++, x1 -= step, x2 -= step) {
						p1.setLocation(x1, 0);
						p2.setLocation(x2, 0);
						Thread.sleep(GAP_TIME);
					}
					container.remove(p1);
					container.repaint();
					container.validate();

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Loading.removeLoading();
			}
		});
		thread.start();
	}

	public static void slideRight(final JPanel p1, final JPanel p2,
			final Container container) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Loading.addLoading();
				try {
					int width = p1.getWidth();
					p2.setLocation(-width, 0);
					container.add(p2);
					int x1 = 0;
					int x2 = -width;
					int step = width / FRAME;
					for (int i = 0; i <= FRAME; i++, x1 += step, x2 += step) {
						p1.setLocation(x1, 0);
						p2.setLocation(x2, 0);
						Thread.sleep(GAP_TIME);
					}
					container.remove(p1);
					container.repaint();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Loading.removeLoading();
			}
		});
		thread.start();
	}

}

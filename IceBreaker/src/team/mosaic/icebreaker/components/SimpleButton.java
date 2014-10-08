package team.mosaic.icebreaker.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class SimpleButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1829344050756884042L;
	private Color color;// 前景色
	@SuppressWarnings("unused")
	private Color backup;// 持有原来颜色副本
	private String text;// 按钮上文本

	public SimpleButton(Color c, String t, int w, int h) {
		this.backup = this.color = c; // 两者相同
		this.text = t;
		this.setSize(w, h);

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {// 当用户松开鼠标按钮时发生。
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {// 当用户按下鼠标按钮时恢复原来颜色
				// TODO Auto-generated method stub
//				color = backup;
//				repaint();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {// 当鼠标离开button时 恢复原来颜色
				// TODO Auto-generated method stub
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (int i = 0; i < 8; ++i) {
							try {
								Thread.sleep(30);
								color = ColorMap.darker(color);
								repaint();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
				thread.start();
//				color = backup;
//				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {// 当鼠标进入button时颜色变浅
				// TODO Auto-generated method stub
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (int i = 0; i < 8; ++i) {
							try {
								Thread.sleep(30);
								color = ColorMap.lighter(color);
								repaint();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
				thread.start();
//				color = ColorMap.lighter(color);
//				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 当用户按下并松开鼠标按钮时发生。用户在选择或双击图标的时候通常会点击鼠标按钮。
				// 用户如果在松开鼠标之前移动鼠标,点击不会导致鼠标相应事件出现。 　
				// TODO Auto-generated method stub

			}
		});
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void paintBorder(Graphics g) {
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);// 使用抗锯齿字体

		g2.setColor(color);// 填充背景颜色
		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setFont(new Font("Microsoft Yahei", Font.BOLD, 14));// 设置字体、字体粗体样式和字体大小
		g2.setColor(Color.white);// 设置字体颜色

		int w = getWidth() / 2 - (int) (15 * (text.length() / 2.0));// 设置文本的位置
		int h = getHeight() / 2 + 5;
		g2.drawString(text, w, h);// 以指定字体颜色画出按钮文本
	}
}

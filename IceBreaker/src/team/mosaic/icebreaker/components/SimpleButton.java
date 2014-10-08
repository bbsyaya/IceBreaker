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
	private Color color;// ǰ��ɫ
	@SuppressWarnings("unused")
	private Color backup;// ����ԭ����ɫ����
	private String text;// ��ť���ı�

	public SimpleButton(Color c, String t, int w, int h) {
		this.backup = this.color = c; // ������ͬ
		this.text = t;
		this.setSize(w, h);

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {// ���û��ɿ���갴ťʱ������
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {// ���û�������갴ťʱ�ָ�ԭ����ɫ
				// TODO Auto-generated method stub
//				color = backup;
//				repaint();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {// ������뿪buttonʱ �ָ�ԭ����ɫ
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
			public void mouseEntered(MouseEvent arg0) {// ��������buttonʱ��ɫ��ǳ
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
				// ���û����²��ɿ���갴ťʱ�������û���ѡ���˫��ͼ���ʱ��ͨ��������갴ť��
				// �û�������ɿ����֮ǰ�ƶ����,������ᵼ�������Ӧ�¼����֡� ��
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
				RenderingHints.VALUE_ANTIALIAS_ON);// ʹ�ÿ��������

		g2.setColor(color);// ��䱳����ɫ
		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setFont(new Font("Microsoft Yahei", Font.BOLD, 14));// �������塢���������ʽ�������С
		g2.setColor(Color.white);// ����������ɫ

		int w = getWidth() / 2 - (int) (15 * (text.length() / 2.0));// �����ı���λ��
		int h = getHeight() / 2 + 5;
		g2.drawString(text, w, h);// ��ָ��������ɫ������ť�ı�
	}
}

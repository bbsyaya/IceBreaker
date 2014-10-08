package team.mosaic.icebreaker.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import team.mosaic.icebreaker.file.Portrait;
import team.mosaic.icebreaker.model.OnlineStatus;

public class PlayerLabel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel portrait_back;
	private static JLabel portrait_front;
	private static JLabel id;
	private static String pre = "<html><font color='white'>";
	private static String post = "</font></html>";

	public PlayerLabel() {
		portrait_back = new JPanel();
		if (OnlineStatus.isOnline()) {
			portrait_front = new JLabel(Portrait.getHeadPortrait(OnlineStatus
					.getCharacter()));
			id = new JLabel(pre + OnlineStatus.getID() + post,JLabel.CENTER);
		} else {
			portrait_front = new JLabel(Portrait.getDefault());
			id = new JLabel(pre + "ÀëÏßÄ£Ê½" + post,JLabel.CENTER);
		}
		id.setFont(new Font("microsoft yahei", Font.BOLD, 15));
		init();
	}

	private void init() {
		this.setSize(212, 97);
		this.setLayout(null);
		this.setOpaque(false);
		this.setBackground(Color.GRAY);

		portrait_back.setBackground(Color.WHITE);
		portrait_back.setBounds(20, 5, 60, 60);
		this.add(portrait_back);

		portrait_back.setLayout(null);
		portrait_front.setBounds(1, 1, 58, 58);
		portrait_back.add(portrait_front);

		id.setBounds(85, 13, 110, 50);
		this.add(id);
	}

	public static void setPortrait(int index) {
		portrait_front.setIcon(Portrait.getHeadPortrait(index));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("pic/label.png").getImage(), 0, 0, 212, 97,
				this);

	}

}

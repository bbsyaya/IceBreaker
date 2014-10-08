package team.mosaic.icebreaker.view.game;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import team.mosaic.icebreaker.file.SubImg;
import team.mosaic.icebreaker.utility.Number;

public class ScorePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8508353157717297761L;
	private static final int DIGITS = 7;// 分数最高7位

	private GamePanel parentPanel;
	private JLabel[] score;

	public ScorePanel(GamePanel gp) {
		parentPanel = gp;
		score = new JLabel[DIGITS];
		init();
	}

	public void init() {
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(212, 97);

		// 初始化分数
		for (int i = DIGITS - 1; i >= 0; i--) {
			score[i] = new JLabel();
			score[i].setBounds(10 + Number.DIGIT_WIDTH * i, 38, Number.DIGIT_WIDTH, Number.DIGIT_HEIGHT);
			this.add(score[i]);
		}
		Number.setNumber(score, 0);
	}

	public void setScore(int s) {
		Number.setNumber(score, s);
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(
				new ImageIcon("pic/score_label.png").getImage(),
				0, 0, 212, 97, this);
	}

}

package team.mosaic.icebreaker.utility;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import team.mosaic.icebreaker.file.SubImg;

public class Number {

	public static final int DIGIT_WIDTH = 20;
	public static final int DIGIT_HEIGHT = 20;

	private static final SubImg DIGITS = new SubImg(SubImg.SCORE, DIGIT_WIDTH,
			DIGIT_HEIGHT);

	// private static final int PLACES = 7;// 数字最高7位

	public static void setNumber(JLabel[] label, int num) {
		int places = label.length;
		if (num == 0) {
			for (int i = 0; i < places - 1; i++)
				label[i].setIcon(null);
			label[places - 1].setIcon(new ImageIcon(DIGITS.getHorImage(0)));
			return;
		}

		int digit = 0;
		for (int i = 0; i < places; i++) {
			digit = getDigit(num, i, places);
			if (digit == -1)
				label[i].setIcon(null);
			else
				label[i].setIcon(new ImageIcon(DIGITS.getHorImage(digit)));
		}
	}

	// 取score第n位的数字
	private static int getDigit(int score, int n, int places) {
		int digits = 0;
		int tmp = score;
		while (tmp != 0) {
			digits++;
			tmp /= 10;
		}
		if (n + digits < places)
			return -1;

		int a = places - n;// 取个位时a为1
		int ret = score % (int) Math.pow(10, a);
		ret /= (int) Math.pow(10, a - 1);
		return ret;
	}

}

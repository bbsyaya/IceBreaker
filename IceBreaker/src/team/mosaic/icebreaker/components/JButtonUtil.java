package team.mosaic.icebreaker.components;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class JButtonUtil {

	public static JButton getBtnClose() {
		JButton btnClose = new JButton();
		btnClose.setRolloverIcon(new ImageIcon(
				"image/WindowsButton/btn_close_down.png"));
		btnClose.setPressedIcon(new ImageIcon(
				"image/WindowsButton/btn_close_highlight.png"));
		btnClose.setIcon(new ImageIcon(
				"image/WindowsButton/btn_close_normal.png"));
		btnClose.setToolTipText("关闭");
		btnClose.setBorder(null);
		btnClose.setFocusPainted(false);
		btnClose.setContentAreaFilled(false);

		return btnClose;
	}

	public static JButton getBtnMin() {
		JButton btnMin = new JButton();
		btnMin.setRolloverIcon(new ImageIcon(
				"image/WindowsButton/btn_mini_down.png"));
		btnMin.setPressedIcon(new ImageIcon(
				"image/WindowsButton/btn_mini_highlight.png"));
		btnMin.setIcon(new ImageIcon("image/WindowsButton/btn_mini_normal.png"));
		btnMin.setToolTipText("最小化");
		btnMin.setBorder(null);
		btnMin.setFocusPainted(false);
		btnMin.setContentAreaFilled(false);
		return btnMin;
	}

	public static JButton getBtnMax() {
		JButton btnMax = new JButton();
		btnMax.setRolloverIcon(new ImageIcon(
				"image/WindowsButton/btn_max_down.png"));
		btnMax.setPressedIcon(new ImageIcon(
				"image/WindowsButton/btn_max_highlight.png"));
		btnMax.setIcon(new ImageIcon("image/WindowsButton/btn_max_normal.png"));
		btnMax.setToolTipText("最大化");
		btnMax.setBorder(null);
		btnMax.setFocusPainted(false);
		btnMax.setContentAreaFilled(false);
		return btnMax;
	}

	public static JButton getBtnSet() {
		JButton btnSet = new JButton();
		btnSet.setRolloverIcon(new ImageIcon(
				"image/WindowsButton/btn_set_hover.png"));
		btnSet.setPressedIcon(new ImageIcon(
				"image/WindowsButton/btn_set_press.png"));
		btnSet.setIcon(new ImageIcon("image/WindowsButton/btn_set_normal.png"));
		btnSet.setToolTipText("设置");
		btnSet.setBorder(null);
		btnSet.setFocusPainted(false);
		btnSet.setContentAreaFilled(false);
		return btnSet;
	}

	public static JButton getTextButton(String text){
		JButton button = new JButton(text);
		button.setFont(new Font("微软雅黑",Font.PLAIN,12));
//		button.setBorder(null);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		return button;
	}
	
	public static JButton getIconButton(String iconPath) {
		JButton button = new JButton();
		button.setIcon(new ImageIcon(iconPath));
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		return button;
	}
	
	public static JButton getIconButton(String iconPath, String pressIconPath,
			String rolloverIconPath) {
		JButton button = new JButton();
		button.setIcon(new ImageIcon(iconPath));
		button.setPressedIcon(new ImageIcon(pressIconPath));
		button.setRolloverIcon(new ImageIcon(rolloverIconPath));
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		return button;
	}
	
	public static JCheckBox getIconCheckBox(String iconPath, String pressIconPath,
			String rolloverIconPath,String selectedIconPath) {
		JCheckBox checkBox = new JCheckBox();
		checkBox.setIcon(new ImageIcon(iconPath));
		checkBox.setPressedIcon(new ImageIcon(pressIconPath));
		checkBox.setRolloverIcon(new ImageIcon(rolloverIconPath));
		checkBox.setSelectedIcon(new ImageIcon(selectedIconPath));
		checkBox.setBorder(null);
		checkBox.setFocusPainted(false);
		checkBox.setContentAreaFilled(false);
		return checkBox;
	}

}

package team.mosaic.icebreaker.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import team.mosaic.icebreaker.file.SubImg;
import team.mosaic.icebreaker.model.ClientModel;
import team.mosaic.icebreaker.utility.Fade;
import team.mosaic.icebreaker.utility.Loading;

public class FriendDialog extends JDialog implements ComponentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel label;
	private JTextField field;
	private Color bgColor = Color.WHITE;// 背景色
	private JButton button1;
	private JButton button2;
	private JFrame pf;
	private int width;
	private int height;
	private static String pre = "<html><font color='black'>";
	private static String post = "</font></html>";

	public FriendDialog() {
		this(null);
	}

	public FriendDialog(JFrame frame) {
		super(frame);

		this.pf = frame;
		this.width = pf.getWidth();
		this.height = 220;
		setPanel();
		init();
		Fade.fadeIn(this);
	}

	private void init() {
		this.add(panel);
		this.setSize(width, height);
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false);
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setVisible(true);
		if (pf != null)
			pf.addComponentListener(this);
	}

	private void setPanel() {
		panel = new DialogPanel();
		this.panel.setLayout(null);
		this.panel.setBackground(bgColor);

		// 提醒字样
		label = new JLabel(pre + "请输入ID：" + post,JLabel.CENTER);
		label.setFont(new Font("microsoft yahei", Font.BOLD, 20));
		label.setSize(150, 30);
		label.setLocation(350, (this.height - label.getHeight()) / 2);
		this.panel.add(label);
		//
		field = new JTextField();
		field.setFont(new Font("microsoft yahei", Font.PLAIN, 18));
		field.setSize(160, 30);
		field.setLocation(label.getX() + label.getWidth(),
				(this.height - field.getHeight()) / 2);
		this.add(field);
		// 按钮
		button1 = new SimpleButton(ColorMap.getColor(ColorMap.BLUE), "添加", 60,
				25);
		button1.setLocation(width / 2 - 70, height - 32);
		this.add(button1);
		button2 = new SimpleButton(ColorMap.getColor(ColorMap.BLUE), "取消", 60,
				25);
		button2.setLocation(width / 2 + 10, height - 32);
		this.add(button2);

		// listener
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String id = field.getText();
				if (!id.equals("")) {
					ClientModel.getMenuModel().addFriend(id);
					Fade.fadeOut(FriendDialog.this);
				}
			}
		});

		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Fade.fadeOut(FriendDialog.this);
			}
		});
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		this.setLocation(pf.getX(),
				pf.getY() + (pf.getHeight() - this.getHeight()) / 2);
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

}

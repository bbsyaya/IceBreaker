package team.mosaic.icebreaker.components;

import java.awt.Color;
import java.awt.Font;
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

import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.utility.Fade;

public class WarningDialog extends JDialog implements ComponentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9186710722201888296L;
	private JPanel panel;
	private JLabel label;
	private String message;
	private Color bgColor = Color.WHITE;// 背景色
	private JButton button;
	private int mode;// 1表示匹配等待
	private JFrame pf;
	private int width;
	private int height;
	private static String pre = "<html><font color='black'>";
	private static String post = "</font></html>";

	public WarningDialog() {
		this(null);
	}

	public WarningDialog(String str) {
		this(null, str, "确定", 0);
	}

	public WarningDialog(JFrame frameParent, String str, String buttonLabel,
			int mode) {
		super(frameParent);

		this.pf = frameParent;
		this.message = str;
		button = new SimpleButton(ColorMap.getColor(ColorMap.BLUE),
				buttonLabel, 80, 25);
		this.mode = mode;
		this.width = 960;
		this.height = 220;
		setPanel();
		init();
		Fade.fadeIn(this);
		// while(true);
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

		// loading图
		if (mode == 1) {
			JLabel img = new JLabel(new ImageIcon("pic/loading2.gif"));
			img.setSize(66, 66);
			img.setLocation((this.width - img.getWidth()) / 2,
					(this.height - img.getHeight()) / 2);
			this.add(img);
		}

		// 提醒字样
		label = new JLabel(pre + message + post, JLabel.CENTER);
		label.setFont(new Font("microsoft yahei", Font.BOLD, 20));
		label.setSize(300, 50);
		label.setLocation((this.width - label.getWidth()) / 2,
				(this.height - label.getHeight()) / 2);
		this.panel.add(label);

		// 按钮
		button.setLocation((width - button.getWidth()) / 2, height - 32);
		this.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (mode == 1)
					NetService.quitRandom();
				Fade.fadeOut(WarningDialog.this);
			}
		});
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		this.setLocation(pf.getX(),
				pf.getY() + (pf.getHeight() - this.getHeight()) / 2);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

}

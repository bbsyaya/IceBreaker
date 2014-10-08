package team.mosaic.icebreaker.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import team.mosaic.icebreaker.model.ClientModel;
import team.mosaic.icebreaker.model.OnlineStatus;
import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.utility.Fade;
import team.mosaic.icebreaker.vo.ReplyVO;

public class OptionDialog extends JDialog implements ComponentListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3529065210052968352L;
	private JPanel panel;
	private JLabel label;
	private String message;
	private Color bgColor = Color.WHITE;// 背景色
	private JButton button1;
	private JButton button2;
	private String optionString1;
	private String optionString2;
	private JFrame pf;
	private int width;
	private int height;
	private static String pre = "<html><font color='black'>";
	private static String post = "</font></html>";
	
	private int mode;//1-购买人物,2-被邀请协作，3-被邀请对战
	private int charIndex;
	private String id;

	public OptionDialog(int mode,JFrame frame,String str){
		this(mode,frame, str, "是", "否");
	}

	public OptionDialog(int mode,JFrame frame, String str, String op1, String op2) {
		super(frame);
		
		this.mode = mode;
		this.pf = frame;
		this.message = str;
		this.optionString1 = op1;
		this.optionString2 = op2;
		this.width = pf.getWidth();
		this.height = 220;
		setPanel();
		init();
		Fade.fadeIn(this);
	}
	
	public void setCharIndex(int index){
		this.charIndex = index;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	private void init() {
		this.add(panel);

		this.setSize(width,height);
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false);
		this.setUndecorated(true);
		this.setBackground(new Color(0,0,0,0));
		this.setVisible(true);
		if(pf!=null)
			pf.addComponentListener(this);
	}

	private void setPanel() {
		panel = new DialogPanel();
		this.panel.setLayout(null);
		this.panel.setBackground(bgColor);

		// 提醒字样
		label = new JLabel(pre+message+post,JLabel.CENTER);
		label.setFont(new Font("microsoft yahei", Font.BOLD, 20));
		label.setSize(300, 30);
		label.setLocation((this.width-label.getWidth())/2, (this.height-label.getHeight())/2);
		this.panel.add(label);
		// 按钮
		button1 = new SimpleButton(ColorMap.getColor(ColorMap.BLUE),
				optionString1, 60, 25);
		button1.setLocation(width/2-70, height-32);
		this.add(button1);
		button2 = new SimpleButton(ColorMap.getColor(ColorMap.BLUE),
				optionString2, 60, 25);
		button2.setLocation(width/2+10, height-32);
		this.add(button2);

		// listener
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(mode == 1)
					ClientModel.getInfoModel().buyChar(charIndex);
				else if(mode == 2)
					NetService.replyCooperation(new ReplyVO(true,id));
				else if(mode == 3)
					NetService.replyPk(new ReplyVO(true,id));
				else if(mode == 4){
					NetService.replyFriend(new ReplyVO(true,id));
					NetService.getMyRank();
				}
				Fade.fadeOut(OptionDialog.this);
			}
		});

		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(mode == 2)
					NetService.replyCooperation(new ReplyVO(false,id));
				else if(mode == 3)
					NetService.replyPk(new ReplyVO(false,id));
				else if(mode == 4)
					NetService.replyFriend(new ReplyVO(false,id));
				Fade.fadeOut(OptionDialog.this);
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
		this.setLocation(pf.getX(), pf.getY()+(pf.getHeight()-this.getHeight())/2);
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

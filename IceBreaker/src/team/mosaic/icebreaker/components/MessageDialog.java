package team.mosaic.icebreaker.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import team.mosaic.icebreaker.utility.Fade;

public class MessageDialog extends JDialog implements ComponentListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5033377497704206134L;
	private JPanel backPanel;
	private JPanel panel;
	private JLabel label;
	private String message;
	private Color bgColor = Color.WHITE;//背景色
	private int showTime = 2000;//停留时间
	private int time = 0;
	private JFrame pf;
	private static String pre = "<html><font color='black'>";
	private static String post = "</font></html>";
	private int width;
	private int height;
	
	public MessageDialog(){
		this(null);
	}
	
	public MessageDialog(String message){
		this(null, message);
	}
	
	public MessageDialog(JFrame frameParent,String message){
		super(frameParent);
		
		this.pf = frameParent;
		this.message = message;
		this.width = pf.getWidth();
		this.height = 220;
		setPanel();
		init();
		Fade.fadeIn(this);
		setTime();
	}
	
	private void init(){
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
	
	private void setPanel(){
		panel = new DialogPanel();
		this.panel.setLayout(null);
		this.panel.setBackground(bgColor);
//		panel.setBounds(1, 1, 298, 98);
		
		label = new JLabel(pre+message+post,JLabel.CENTER);
		label.setFont(new Font("microsoft yahei",Font.BOLD,20));
		label.setSize(400, 30);
		label.setLocation((this.width-label.getWidth())/2, (this.height-label.getHeight())/2);
		this.panel.add(label);
		
//		backPanel = new JPanel();
//		backPanel.setLayout(null);
//		backPanel.setBackground(Color.BLACK);
//		backPanel.add(panel);
	}
	
	private void setTime(){//为什么线程不行？？！！
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					Thread.sleep(showTime);
//					Fade.fadeOut(instance);
//					instance.dispose();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//		thread.run();
		
		Timer timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				time+=1000;
				if(time==showTime){
					Fade.fadeOut(MessageDialog.this);
				}
			}
		});
		timer.start();
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

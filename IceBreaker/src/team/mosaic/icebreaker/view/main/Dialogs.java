package team.mosaic.icebreaker.view.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.viewservice.DialogsService;
import team.mosaic.icebreaker.vo.ReplyVO;

/**
 * 对话框重写类
 * @author acer
 *
 */
public class Dialogs implements DialogsService {
	
	public Dialogs() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void friendApply(final String id , Frame owner) {
		// TODO Auto-generated method stub
		final JDialog dialog = new JDialog(owner);
		
		
		int width = 400;
		int height = 200;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();      //得到屏幕的尺寸  
		dialog.setBounds( (screenSize.width-width)/2,(screenSize.height-height)/2, //宽度
		width,height );//高度
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(dialog.DO_NOTHING_ON_CLOSE);
		dialog.setLayout(null);
		dialog.setResizable(false);
		dialog.setTitle("好友申请");
		dialog.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				NetService.replyFriend(new ReplyVO(false,id));
				dialog.dispose();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}});
	
		JLabel content = new JLabel(id+" 请求添加你为好友，是否同意？");
//		content.setText("<html>hello <br> world!</html>"); 
		content.setFont(new Font("Dialog",1,20));
		int x = width/20, y = height/20;
		content.setBounds(x, y, width-2*x, height*2/3-y);
//		content.setBackground(Color.black);
		content.setOpaque(true);
		dialog.add(content);
		
		int Bwidth = width/5;
		int Bheight = height/3-4*y;
		
		int y2 = height*2/3+y;
		int x21 = width/2-Bwidth-width/20;
		int x22 = width/2+width/20;
		
		JButton agree = new JButton("同意");
		agree.setBounds(x21,y2,Bwidth,Bheight);
		agree.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NetService.replyFriend(new ReplyVO(true,id));
				NetService.getMyRank();
				dialog.dispose();
			}});
		dialog.add(agree);
		
		JButton disagree = new JButton("拒绝");
		disagree.setBounds(x22,y2,Bwidth,Bheight);
		disagree.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NetService.replyFriend(new ReplyVO(false,id));
				dialog.dispose();
			}});
		dialog.add(disagree);
		
		dialog.repaint();
	}

}

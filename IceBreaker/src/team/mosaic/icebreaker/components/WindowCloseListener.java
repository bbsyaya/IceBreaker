package team.mosaic.icebreaker.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class WindowCloseListener implements ActionListener{
	
	JFrame frame = null;

	public WindowCloseListener(JFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("exit")){
			System.exit(0);
		}else if(e.getActionCommand().equals("dispose")){
			frame.setVisible(false);
			frame.dispose();
		}
	}
	
	
	
}

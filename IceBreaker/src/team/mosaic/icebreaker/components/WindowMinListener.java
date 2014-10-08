package team.mosaic.icebreaker.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class WindowMinListener implements ActionListener{
	JFrame frame = null;

	public WindowMinListener(JFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.setExtendedState(JFrame.ICONIFIED);
		//frame.setVisible(false);
	}
	
	
}

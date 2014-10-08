package team.mosaic.icebreaker.components;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class LoadingGlassPane extends JComponent implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1632304322035840394L;
	private ImageIcon img;
	private JLabel label;
	
	public LoadingGlassPane(){
		img = new ImageIcon("pic/loading.gif");
//		img = new ImageIcon(filepath);

		label = new JLabel(img, JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		init();
	}
	
	public void normalize(){
		this.add(label);
	}
	
	public void setLabel(ImageIcon i){
		label.setIcon(i);
	}
	
	private void init(){
		this.setBounds(0, 0, 960, 600);
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.addMouseListener(this);
		this.add(label);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

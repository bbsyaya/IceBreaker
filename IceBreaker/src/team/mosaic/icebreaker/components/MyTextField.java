package team.mosaic.icebreaker.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class MyTextField extends JTextField{
	private MyTextField instance = this;
	private Color focusColor = Color.BLACK;
	private Color commonColor = Color.GRAY;
	private Font font = new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1978183935992339334L;
	
	public MyTextField(String tip){
		this.setToolTipText(tip);
		this.setFont(font);
		this.setBorder(new LineBorder(commonColor));
		addListener();
	}
	
	public MyTextField(){
		this(null);
	}
	
	private void addListener(){
		
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				instance.setBorder(new LineBorder(focusColor));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(!instance.isFocusOwner())
					instance.setBorder(new LineBorder(commonColor));
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				instance.setBorder(new LineBorder(focusColor));
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				instance.setBorder(new LineBorder(commonColor));
			}
			
		});
	}
	
}

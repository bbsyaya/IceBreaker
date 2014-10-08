package team.mosaic.icebreaker.view.game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int COMBOS = 4;// 最高四连击
	
	private GamePanel parentPanel;
	private JLabel label;
	
	private static ImageIcon combo0 = new ImageIcon("pic/combo0.png");
	private static ImageIcon combo1 = new ImageIcon("pic/combo1.png");
	private static ImageIcon combo2 = new ImageIcon("pic/combo2.png");
	private static ImageIcon combo3 = new ImageIcon("pic/combo3.png");
	private static ImageIcon combo4 = new ImageIcon("pic/combo4.png");
	
	public ComboPanel(GamePanel gp){
		this.parentPanel = gp;
		label = new JLabel();
		init();
	}
	
	private void init(){
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(500, 150);
		
		label.setBounds(0, 0, 500, 150);
		this.add(label);
		this.setCombo(0);
		
//		// 初始化连击
//		for (int j = 0; j < COMBOS; j++) {
//			combos[j] = new JLabel("N");
//			combos[j].setBounds(j * 20, 0, 20, 20);
//			this.add(combos[j]);
//		}
	}
	
	public void setCombo(int combo) {// combo from 0 to 4
		switch (combo) {
		case 0:
			label.setIcon(combo0);
			break;
		case 1:
			label.setIcon(combo1);
			break;
		case 2:
			label.setIcon(combo2);
			break;
		case 3:
			label.setIcon(combo3);
			break;
		case 4:
			label.setIcon(combo4);
			break;
		default:
			break;
		}
		this.repaint();
	}
	
}

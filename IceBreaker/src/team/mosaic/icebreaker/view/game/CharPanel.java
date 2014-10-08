package team.mosaic.icebreaker.view.game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CharPanel extends JPanel {
	
	
	private static final long serialVersionUID = 4540871536514172196L;
	private GamePanel parentPanel;
	private JLabel charaLabel;
	private ImageIcon[] icons=new ImageIcon[4];
	
	public CharPanel(GamePanel gp) {
		parentPanel=gp;
		charaLabel=new JLabel();
		init();
	}
	
	
	public void init(){
		this.setOpaque(false);
		this.setLayout(null);
		
//		for(int i=0;i<icons.length;i++){
//			icons[i]=new ImageIcon("image/character/"+i+".png");
//		}
		
		charaLabel.setBounds(10, 10, 200, 200);
		//character.setIcon()
		charaLabel.setText("ÈËÎï");
		this.add(charaLabel);
		
		this.setBounds(800, 200, 200, 200);
//		parentPanel.add(this);

		
	}
	
	public void setCharacter(int character){
		if(character<0||character>=4){
			System.out.println("Wrong!");
			return;
		}
		else
			this.charaLabel.setIcon(icons[character]);
	}
	
}

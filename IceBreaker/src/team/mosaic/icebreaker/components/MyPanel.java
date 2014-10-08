package team.mosaic.icebreaker.components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MyPanel extends JPanel{
	ArrayList<Integer> data ;// = {100,90,110,80,120};
	ArrayList<String> info ;
	int WIDTH_OF_DOT = 6;
	int HEIGHT_OF_DOT = 6;
	int DISTANCE_BETWEEN_TWO_DOT = 100;
	int ABSCISSA_OF_FIRST_DOT = 60;
	int widthOfLabel = 0;
	int heightOfLabel = 0;
	int HEIGHT_PER_HUNDRED_SCORES = 200;
	int HEITHT_OF_X_AXIS = 20;
	
	Color COLOR_OF_DOT = Color.red;
	Color COLOR_OF_LINE = Color.red;
	Color COLOR_OF_WORD = Color.red;
	Color COLOR_OF_X_AXIS = Color.red;
	
	public MyPanel(){
		super();
	}
	
	public MyPanel(ArrayList<Integer> data){
		super();
		this.setVisible(true);
		this.data = data;
//		widthOfLabel = DISTANCE_BETWEEN_TWO_DOT*(data.size()+1);
//		this.setBackground(Color.black);
//		this.setOpaque(false);
		}
	
	public void paint(Graphics g){
		super.paint(g);
		
		FontMetrics metrics = this.getFontMetrics(g.getFont()); 
		int textH = metrics.getHeight(); //字符串的高, 只和字体有关
		 //字符串的宽
		
		if(data != null){
			int count = data.size();
			
			g.setColor(COLOR_OF_DOT);
			for(int i = 0;i<count;i++){
				g.fillOval(DISTANCE_BETWEEN_TWO_DOT*i+ABSCISSA_OF_FIRST_DOT,
						this.getHeight()-HEITHT_OF_X_AXIS-data.get(i)*HEIGHT_PER_HUNDRED_SCORES/100, 
						WIDTH_OF_DOT, HEIGHT_OF_DOT);
			}
			
			g.setColor(COLOR_OF_LINE);
			for(int i = 0;i<count-1;i++){
				g.drawLine(DISTANCE_BETWEEN_TWO_DOT*i+ABSCISSA_OF_FIRST_DOT+WIDTH_OF_DOT/2,
						this.getHeight()-HEITHT_OF_X_AXIS-data.get(i)*HEIGHT_PER_HUNDRED_SCORES/100+HEIGHT_OF_DOT/2, 
						DISTANCE_BETWEEN_TWO_DOT*(i+1)+ABSCISSA_OF_FIRST_DOT+WIDTH_OF_DOT/2, 
						this.getHeight()-HEITHT_OF_X_AXIS-data.get(i+1)*HEIGHT_PER_HUNDRED_SCORES/100+HEIGHT_OF_DOT/2);
			}
			
			g.setColor(COLOR_OF_X_AXIS);
			g.drawLine(10,this.getHeight() - HEITHT_OF_X_AXIS, this.getWidth(), this.getHeight() - HEITHT_OF_X_AXIS);
			g.drawLine(10,this.getHeight() - HEITHT_OF_X_AXIS+1, this.getWidth(), this.getHeight() - HEITHT_OF_X_AXIS+1);
			g.drawLine(10,this.getHeight() - HEITHT_OF_X_AXIS+2, this.getWidth(), this.getHeight() - HEITHT_OF_X_AXIS+2);
			
			g.setColor(COLOR_OF_WORD);
			for(int i = 0;i<count;i++){
				String content = String.valueOf(data.get(i));
				int textW = metrics.stringWidth(content);
//				int size = g.getFont().getSize();
//				int len = content.length();
//				int length = size*len;
				int x = DISTANCE_BETWEEN_TWO_DOT*i+ABSCISSA_OF_FIRST_DOT+WIDTH_OF_DOT/2-textW/2;
				int y = this.getHeight()-HEITHT_OF_X_AXIS-data.get(i)*HEIGHT_PER_HUNDRED_SCORES/100 - (g.getFont().getSize())/2;
				g.drawString(content, x, y);
//			DISTANCE_BETWEEN_TWO_DOT*i+ABSCISSA_OF_FIRST_DOT+WIDTH_OF_DOT/2,
//			data[i]+HEIGHT_OF_DOT/2, 
				
			}
		}
		
		if(info !=null){
			int num = info.size();
			for(int i = 0;i < num;i++){
				String content = String.valueOf(info.get(i));
//				int size = g.getFont().getSize();
//				int len = content.length();
//				int length = size*len;
				int textW = metrics.stringWidth(content);
				int x = DISTANCE_BETWEEN_TWO_DOT*i+ABSCISSA_OF_FIRST_DOT - textW/2;
				g.drawString(content, x, this.getHeight() - HEITHT_OF_X_AXIS+16);
			}
		}
	}
	
//	public void setBounds(int x,int y,int width,int height){
//		if(width<widthOfLabel){
//			super.setBounds(x, y, widthOfLabel, height);
//		}
//		else{
//			super.setBounds(x, y, width, height);
//		}
//	}
	
	public void setData(ArrayList<Integer> data){
		this.data = data;
		int count = data.size();
		widthOfLabel = DISTANCE_BETWEEN_TWO_DOT*(count+1);
		int max = 0;
		for(int n:data){
			if(n>max)
				max = n;
		}
		
		if(max<5)
			HEIGHT_PER_HUNDRED_SCORES = 10000;
		else if(max>=5&&max<10)
			HEIGHT_PER_HUNDRED_SCORES = 10000/2;
		else if(max>=10&&max<50)
			HEIGHT_PER_HUNDRED_SCORES = 1000;
		else if(max>=50&&max<100)
			HEIGHT_PER_HUNDRED_SCORES = 1000/2;
		else if(max>=100&&max<500)
			HEIGHT_PER_HUNDRED_SCORES = 100;
		else if(max>=500&&max<1000)
			HEIGHT_PER_HUNDRED_SCORES = 100/2;
		else if(max>=1000&&max<5000)
			HEIGHT_PER_HUNDRED_SCORES= 10;
		else if(max>=5000&&max<10000)
			HEIGHT_PER_HUNDRED_SCORES = 2;
		else
			HEIGHT_PER_HUNDRED_SCORES = 1;
		
		heightOfLabel = HEITHT_OF_X_AXIS+(max+1)*HEIGHT_PER_HUNDRED_SCORES/100;
		this.setPreferredSize(new Dimension(widthOfLabel,heightOfLabel));
		
	}
	
	public void setInfo(ArrayList<String> info){
		this.info = info;
	}
	
	public void setXAxis(int h){
		HEITHT_OF_X_AXIS = h;
	}
	
	public void setHeightPerHundredScores(int h){
		HEIGHT_PER_HUNDRED_SCORES = h;
	}
	
	public void setXaxisColor(Color c){
		COLOR_OF_X_AXIS = c;
//		this.repaint();
	}
	
	public void setDotColor(Color c){
		COLOR_OF_DOT = c;
//		this.repaint();
	}
	
	public void setLineColor(Color c){
		COLOR_OF_LINE = c;
	}
	
	public void setWordColor(Color c){
		COLOR_OF_WORD = c;
	}

}

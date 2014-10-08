package team.mosaic.icebreaker.view.game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import team.mosaic.icebreaker.controller.GameController;
import team.mosaic.icebreaker.file.SubImg;
import team.mosaic.icebreaker.model.BoardModel;
import team.mosaic.icebreaker.model.ClientModel;
import team.mosaic.icebreaker.model.GameModel;
import team.mosaic.icebreaker.model.OnlineStatus;
import team.mosaic.icebreaker.model.SetModel;
import team.mosaic.icebreaker.modelservice.BoardModelService;
import team.mosaic.icebreaker.modelservice.GameModelService;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.view.main.MainFrame;
import team.mosaic.icebreaker.view.main.ReadyPanel;
import team.mosaic.icebreaker.viewservice.GameViewService;
import team.mosaic.icebreaker.vo.AccountVO;
import team.mosaic.icebreaker.vo.GainVO;

public class GamePanel extends JPanel implements GameViewService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8085015045559508982L;
	private MainFrame parentFrame;
	private GameController controller;
	private int mode;
	private int direction;
	private int character;
//	private String[] player2;
	private static final int tooNum=4;
	private boolean[] tools=new boolean[tooNum];
	private GameModelService gms;
	private ReadyPanel rp;
	private JLabel[] other = new JLabel[5];
	
	public BoardPanel bp;
	public TimePanel  tp;
	public CharPanel cp;
	public ScorePanel sp;
	public ComboPanel cbp;
	public ToolPanel toolPanel;
	public JLabel outButton;

	//character 为0,1,2,3
	// mode 为 1,2,3
	//direction为0（向下），1
	
	public GamePanel(MainFrame frame){
		this.parentFrame = frame;
		new GameModel(this);
		controller = new GameController(this);
		this.setLayout(null);
		init();
		//初始化boardmodel
	}
	@Override
	public void initSingle(int character,boolean[] tools) {
		this.mode = 1;
		this.character = character;
		this.tools = tools;
		refresh();
		toolPanel.setTool(tools);
		bp.controller.bms=new BoardModel(bp, mode, SetModel.direction,character,tools);
		bp.controller.initBoard();
		this.other[0].setText("单人模式");
		this.add(this.other[0]);
	}
	
	@Override
	public void initCoop(int character,String player2,boolean[] tools) {
		this.mode = 2;
		this.character = character;
		refresh();
		bp.controller.bms = new BoardModel(bp, mode, 0, tools);
		this.other[0].setText("双人协作模式");
		this.add(this.other[0]);
		this.other[1].setText("我："+OnlineStatus.getID());
		this.add(this.other[1]);
		this.other[2].setText("伙伴："+player2);
		this.add(this.other[2]);
	}
	@Override
	public void initPk(int character, String player2,boolean[] tools) {
		// TODO Auto-generated method stub
		this.mode = 3;
		this.character = character;
		refresh();
		bp.controller.bms = new BoardModel(bp, mode, SetModel.direction,tools);
		bp.controller.initBoard();
		this.other[0].setText("双人对战模式");
		this.add(this.other[0]);
		this.other[1].setText("我："+OnlineStatus.getID());
		this.add(this.other[1]);
		this.other[2].setText("对手："+player2);
		this.add(this.other[2]);
	}
	@Override
	public void initCoopFour(int character, String[] others,boolean[] tools) {
		// TODO Auto-generated method stub
		this.mode = 4;
		this.character = character;
		refresh();
		bp.controller.bms = new BoardModel(bp, mode, 0,tools);
		this.other[0].setText("四人协作模式");
		this.add(this.other[0]);
		this.other[1].setText("我："+others[0]);
		this.add(this.other[1]);
		this.other[2].setText("伙伴："+others[1]);
		this.add(this.other[2]);
		this.other[3].setText("伙伴："+others[2]);
		this.add(this.other[3]);
		this.other[4].setText("伙伴："+others[3]);
		this.add(this.other[4]);
	}
	@Override
	public void initPKFour(int character, String[] others,boolean[] tools) {
		// TODO Auto-generated method stub
		this.mode = 5;
		this.character = character;
		refresh();
		bp.controller.bms = new BoardModel(bp, mode, 0,tools);
		this.other[0].setText("四人对战模式");
		this.add(this.other[0]);
		this.other[1].setText("我："+others[0]);
		this.add(this.other[1]);
		this.other[2].setText("伙伴："+others[1]);
		this.add(this.other[2]);
		this.other[3].setText("对手："+others[2]);
		this.add(this.other[3]);
		this.other[4].setText("对手："+others[3]);
		this.add(this.other[4]);
	}
	
	private void refresh(){
		this.refreshCombo(0);
		this.refreshScore(0);
		bp.removeAll();
	}
	
	public void setMode(int mode){
		this.mode = mode;
	}
	
	public void setCharacter(int character){
		this.character = character;
	}
	
	public void setTools(boolean[] tools){
		this.tools = tools;
	}
	
	private void init(){
		outButton = new JLabel(new ImageIcon("pic/back.png"));
		outButton.setBounds(0, 550, 50, 50);
		outButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		outButton.addMouseListener(controller);
		this.add(outButton);
		
		bp = new BoardPanel(this);
		bp.setLocation(310, 50);
		this.add(bp);
		
		tp=new TimePanel(this);
		//location
		//listener
		
		sp=new ScorePanel(this);
		sp.setLocation(-5, 0);
		this.add(sp);
		
		cbp = new ComboPanel(this);
		cbp.setLocation(320, 480);
		this.add(cbp);
		
		cp=new CharPanel(this);
		
		toolPanel=new ToolPanel(this);
		toolPanel.setLocation(50, 500);
		this.add(toolPanel);
		
//		other[0] = new JLabel();
//		other[0].setBounds(80, 320, 200, 30);
//		other[0].setFont(new Font("microsoft yahei", Font.BOLD, 14));
//		other[0].setForeground(Color.WHITE);
//		other[1] = new JLabel();
//		other[1].setBounds(80, 350, 200, 30);
//		other[1].setFont(new Font("microsoft yahei", Font.BOLD, 14));
//		other[1].setForeground(Color.WHITE);
//		other[2] = new JLabel();
//		other[2].setBounds(80, 380, 200, 30);
//		other[2].setFont(new Font("microsoft yahei", Font.BOLD, 14));
//		other[2].setForeground(Color.WHITE);
//		other[3] = new JLabel();
//		other[3].setBounds(80, 410, 200, 30);
//		other[3].setFont(new Font("microsoft yahei", Font.BOLD, 14));
//		other[3].setForeground(Color.WHITE);
		for(int i = 0;i<5;i++){
			other[i] = new JLabel();
			other[i].setBounds(80, 320+i*30, 200, 30);
			other[i].setFont(new Font("microsoft yahei", Font.BOLD, 14));
			other[i].setForeground(Color.WHITE);
		}
	}
	
	public MainFrame getParentFrame(){
		return parentFrame;
	}
	
	public int getMode(){
		return mode;
	}
	public int getDirection(){
		return direction;
	}
	public int getCharacter(){
		return character;
	}
		
	public GameController getController(){
		return controller;
	}
	public boolean[] getTools(){
		return tools;
	}

	@Override
	public void showChar(int character) {
		this.cp.setCharacter(character);
	}

	@Override
	public void refreshTime(int time) {
		// TODO Auto-generated method stub
		this.tp.setTime(time);
	}

	@Override
	public void refreshCombo(int count) {
		// TODO Auto-generated method stub
		cbp.setCombo(count);
	}

	@Override
	public void refreshScore(int score) {
		// TODO Auto-generated method stub
		sp.setScore(score);
	}
	
	@Override
	public void end(GainVO gvo) {
		// TODO Auto-generated method stub
		this.remove(other[0]);
		this.remove(other[1]);
		this.remove(other[2]);
		this.remove(other[3]);
		this.remove(other[4]);
		rp = this.getParentFrame().getBgPanel().getReadyPanel();
		rp.t1.setSelected(false);
		rp.t2.setSelected(false);
		rp.t3.setSelected(false);
		rp.t4.setSelected(false);
		if(!MainFrame.onLine)
			Prompt.showGainDialog(parentFrame, false, gvo.getScore(), 0, 0, false, false, 0);
		ClientModel.getGameModel().setGainVO(gvo);
	}
	
	@Override
	public void someoneQuit(String id) {
		// TODO Auto-generated method stub
		for(int i = 0;i<4;i++){
			if(other[i].getText().contains(id)){
				other[i].setText(other[i].getText() + "  已离开游戏");
				break;
			}
		}
	}
	
	@Override
	public void lockToolC() {
		// TODO Auto-generated method stub
		toolPanel.lockC();
	}
	@Override
	public void unlockToolC() {
		// TODO Auto-generated method stub
		toolPanel.unlockC();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(
				new ImageIcon(SubImg.GAME_BACKGROUND).getImage(),
				0, 0, 960, 600, this);
	}
	
}
	

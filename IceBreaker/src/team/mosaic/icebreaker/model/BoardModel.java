package team.mosaic.icebreaker.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import team.mosaic.icebreaker.modelservice.BoardModelService;
import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.view.game.BoardPanel;
import team.mosaic.icebreaker.vo.ColorQueueVO;
import team.mosaic.icebreaker.vo.GainVO;
import team.mosaic.icebreaker.vo.MoveandCreateActionVO;
import team.mosaic.icebreaker.vo.PositionVO;
import team.mosaic.icebreaker.vo.RecordVO;
import team.mosaic.icebreaker.vo.SwapActionVO;

/*
 * 色块界面模型类
 */
public class BoardModel implements BoardModelService {
	private BoardPanel bp;
	
	//模式，1代表单人，2代表协作，3代表对战
	private int mode;
	//单人模式下的游戏人物，0为Anna，1为Kristoff，2为Sven，3为Elsa
	private int character;
	private boolean isMainPlayer;
	private int mark;
	private ColorQueueVO queue;
	
	//用整数表示方格的状态，0为空，1~6代表六种方块，-1~-6代表生成的对应的A道具，7代表B道具
	private int[][] grids = new int[9][9];
	//游戏中方块下落方向，0表示从上向下，1表示从左向右
	private int direction;
	
	private int timer;
	
	private int score;
	final private double score_up_rate = 1.1;
	private int exp;
	private int coin;
	
//	private boolean inDealing;
	
	private NewTimer t;
	private boolean combo;
	private int combo_count;
	private int combo_timer;
	private int combo_up;
	private int combo_power_start;
	private boolean combo_power;
	private int top_combo;
	
	private int tip_timer;
	
	private int[] color_lock_timer = new int[6];
	private int toolC_lock_timer;
	
	private boolean[] tools = new boolean[5];
	
	/**
	 * 单人模式初始化，模式编号为1
	 * @param bp 盘面界面panel
	 * @param mode 模式编号，1代表单人，2代表协作，3代表对战
	 * @param direction 下落方向，0为向下，1为向右
	 * @param character 人物，1为Anna，2为Kristoff，3为Sven，4为Elsa
	 * @param tools 道具，长度为4的数组
	 */
	public BoardModel(BoardPanel bp, int mode, int direction, int character, boolean[] tools){
		this.bp = bp;
		if(mode==1){
			
			combo = false;
			combo_count = 0;
			combo_timer = 700;
			combo_power_start = 700;
			combo_up=tools[0]?2:1;
			top_combo = 0;
			tip_timer = 600;
			this.mode = 1;
			this.direction = direction;
			this.character = character;
			this.tools = tools;
			for(int i = 0;i<9;i++){
				for(int j =0;j<9;j++){
					grids[i][j] = random();
				}
			}
			for(int i = 0;i<9;i++){
				for(int j =0;j<9;j++){
					PositionVO p = new PositionVO(i,j);
					while(canRemove(p)){
						grids[i][j]++;
						if(grids[i][j]==7){
							grids[i][j] = 1;
						}
					}
				}
			}
//			show(grids);
			
		}
	}
	
	/**
	 * 多人模式初始化，模式编号为2或3
	 * @param bp 盘面界面panel
	 * @param mode 模式编号，1代表单人，2代表协作，3代表对战
	 * @param direction 下落方向，0为向下，1为向右
	 * @param tools 道具
	 */
	public BoardModel(BoardPanel bp,int mode,int direction,boolean tools[]){
		this.bp = bp;
		ClientModel.setBoardModel(this);
		if((mode>=2)&&(mode<=5)){
			combo = false;
			combo_count = 0;
			combo_timer = 700;
			combo_power_start = 700;
			combo_up=tools[0]?2:1;
			top_combo = 0;
			tip_timer = 600;
			this.mode = mode;
			this.direction = direction;
			mark = 0;
			
			for(int i = 0;i<6;i++){
				color_lock_timer[i] = 700;
			}
			toolC_lock_timer = 700;
		}
		if(mode==3){
			for(int i = 0;i<9;i++){
				for(int j =0;j<9;j++){
					grids[i][j] = random();
				}
			}
			for(int i = 0;i<9;i++){
				for(int j =0;j<9;j++){
					PositionVO p = new PositionVO(i,j);
					while(canRemove(p)){
						grids[i][j]++;
						if(grids[i][j]==7){
							grids[i][j] = 1;
						}
					}
				}
			}
		}
	}
	
	/**
	 * 设置为主玩家
	 */
	public void setMainPlayer(){
		this.isMainPlayer = true;
		
		queue = new ColorQueueVO();
		NetService.setColorQueue(queue);
		
		
		for(int i = 0;i<9;i++){
			for(int j =0;j<9;j++){
				grids[i][j] = queue.next();
			}
		}
		for(int i = 0;i<9;i++){
			for(int j =0;j<9;j++){
				PositionVO p = new PositionVO(i,j);
				while(canRemove(p)){
					grids[i][j]++;
					if(grids[i][j]==7){
						grids[i][j] = 1;
					}
				}
			}
		}
	}
	
	/**
	 * 设置副玩家
	 * @param queue
	 */
	public void setSubPlayer(ColorQueueVO queue){
		this.isMainPlayer = false;
		this.queue = queue;
		for(int i = 0;i<9;i++){
			for(int j =0;j<9;j++){
				grids[i][j] = queue.next();
			}
		}
		for(int i = 0;i<9;i++){
			for(int j =0;j<9;j++){
				PositionVO p = new PositionVO(i,j);
				while(canRemove(p)){
					grids[i][j]++;
					if(grids[i][j]==7){
						grids[i][j] = 1;
					}
				}
			}
		}
	}
	
	public void startTimer(){
		if(mode!=1&&mode!=3){
			bp.initBoard(grids);
		}
		NewTimer nt = new NewTimer(this);
		t = nt;
		nt.start();
	}
	
	/**
	 * 计时器改变时间
	 * @param leftTime
	 */
	public void changeTimer(int leftTime){
		timer = leftTime;
		if(combo_timer - timer <= 10*((toolC_lock_timer-timer<=50)?1:combo_up)){
			combo = true;
		}else{
			if(combo_count!=0){
				bp.getParent().refreshCombo(0);
				combo_count=0;
			}
			combo = false;
		}
		combo_power = (combo_power_start - timer <= 50);
		
		if(timer%10==0){
			bp.getParent().refreshTime(timer/10);
		}
		
		if(tip_timer - timer == (tools[2]?20:30)&&timer>0){
			bp.prompt(tip());
		}
		
		for(int i=0;i<6;i++){
			if(color_lock_timer[i]-timer==20){
				bp.unlock(i+1);
			}
		}
		
		if(toolC_lock_timer-timer==50){
			bp.getParent().unlockToolC();
		}
		
		
		if(timer==10&&mode==1&&tools[3]){
			tools[3] = false;
			combo = false;
			combo_count = 0;
			combo_timer = 100;
			combo_power_start = 100;
			t.return5();
			return;
		}
		
		if(timer==0){
			bp.noprompt();
			if(mode==1&&tools[3]){
				tools[3] = false;
				combo = false;
				combo_count = 0;
				combo_timer = 100;
				combo_power_start = 100;
				t.return5();
				return;
			}
			t.stop();
			loop:for(int i = 0;i<9;i++){
				for(int j = 0;j<9;j++){
					if(grids[i][j]<0||grids[i][j]==7){
						bp.iceCombo();
						break loop;
					}
				}
			}
			Thread thread = new Thread(new Runnable(){
				public void run(){
					int i,j;
					boolean hasTool=true;
					int[][] bdChange = new int[9][9];
					while(hasTool){
						hasTool = false;
						for(i=0;i<9;i++){
							for(j=0;j<9;j++){
								bdChange[i][j] = 0;			
							}
						}
						loop:for(i=0;i<9;i++){
							for(j=0;j<9;j++){
								if(grids[i][j]<0||grids[i][j]==7){
									bdChange[i][j] = 1;
									hasTool = true;
									break loop;
								}
							}
						}
						delete(bdChange);
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					}
					
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(mode==1&&OnlineStatus.isOnline()){
						score = score + (character)*score/50;
					}
					GainVO gvo = new GainVO(score, exp, coin);
					bp.getParent().end(gvo);
					switch(mode){
					case 1:
						if(OnlineStatus.isOnline()){
							NetService.endSingle(gvo);
							NetService.updateRecord(new RecordVO(score,top_combo));
						}
						break;
					case 2:
						NetService.endCooperation(gvo);
						break;
					case 3:
						NetService.endPK(gvo);
						break;
					case 4:
						NetService.endCoopFour(gvo);
						break;
					case 5:
						NetService.endPkFour(gvo);
						break;
					default:
					}
				}
			});
			
			thread.start();
		}
	}
	
	/**
	 * 中途结束游戏
	 */
	public void stopGame(){
		t.stop();
	}
	
	/**
	 * 判断两个位置是否可以进行交换，如果可以就向服务器发送请求。只是给controller的接口，并不是真正改变盘面 。
	 */
	public boolean trySwap(PositionVO p1,PositionVO p2){
		if(timer<=0){
			return false;
		}
		if(!p1.adjacent(p2)){
			tip_timer = timer;
			return false;
		}
		if(mode==3||mode==5){
			int color1 = Math.abs(grids[p1.getX()][p1.getY()]);
			int color2 = Math.abs(grids[p2.getX()][p2.getY()]);
			if(color1>0&&color1<7){
				if(color_lock_timer[color1-1]-timer<=20)
					return false;
			}
			if(color2>0&&color2<7){
				if(color_lock_timer[color2-1]-timer<=20)
					return false;
			}
		}
		
		exp++;
		
		int tmp = grids[p1.getX()][p1.getY()];
		grids[p1.getX()][p1.getY()] = grids[p2.getX()][p2.getY()];
		grids[p2.getX()][p2.getY()] = tmp;

		if((!canRemove(p1))&&(!canRemove(p2))){
			grids[p2.getX()][p2.getY()] = grids[p1.getX()][p1.getY()];
			grids[p1.getX()][p1.getY()] = tmp;
			bp.swapUnit2(p1, p2);
			return false;
		}
		grids[p2.getX()][p2.getY()] = grids[p1.getX()][p1.getY()];
		grids[p1.getX()][p1.getY()] = tmp;
		if(mode!=1&&mode!=3){
			SwapActionVO savo = new SwapActionVO(p1,p2,mark);
			NetService.trySwap(savo);
		}else{
			try {
				swap(p1,p2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
		
	}

	/**
	 * 真正的对两个位置进行交换
	 */
	public void swap(final PositionVO p1,final PositionVO p2) throws Exception{
		bp.noprompt();
		if(combo){
			combo_count++;
			top_combo = Math.max(top_combo, combo_count);
		}else{
			combo_count = 1;
		}
		if(combo_count>=4){
			if(mode==3||mode==5)
				NetService.superTime();
			combo_power_start = timer;
		}
		bp.getParent().refreshCombo(Math.min(combo_count, 4));
		combo_timer = timer;

		if(!p1.adjacent(p2)){
			throw new Exception("非相邻位置");
		}else{
			int tmp = grids[p1.getX()][p1.getY()];
			grids[p1.getX()][p1.getY()] = grids[p2.getX()][p2.getY()];
			grids[p2.getX()][p2.getY()] = tmp;
			
			bp.swapUnit(p1,p2);
			Thread thread = new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					pre_delete(p1,p2);
				}
				
			});
			thread.start();
			
		}
		if(mode!=1&&mode!=3){
			mark++;
		}
	}
	
	/**
	 * 道具AB的单独消除操作
	 */
	public boolean useToolGrid(PositionVO p){
		bp.noprompt();
		int i = p.getX();
		int j = p.getY();
		int[][] bdChange = new int[9][9];
		if(grids[i][j]<0||grids[i][j]==7){
			exp++;
			if(mode!=1&&mode!=3){
				SwapActionVO savo = new SwapActionVO(p,p,mark);
				NetService.trySwap(savo);
			}else{
				bdChange[i][j] = 1;
				delete(bdChange);
			}
			return true;
		}else{
			return false;
		}
	}
	
	public void useToolGridwithNet(PositionVO p){
		bp.noprompt();
		int i = p.getX();
		int j = p.getY();
		int[][] bdChange = new int[9][9];
		bdChange[i][j] = 1;
		delete(bdChange);
		if(mode!=1&&mode!=3){
			mark++;
		}
	}
	
	/**
	 * 判断一个位置的方块是否是可消除的
	 * @param p
	 * @return
	 */
	private boolean canRemove(PositionVO p){
		int x = p.getX();
		int y = p.getY();
		if(x>1){
			if((grids[x-2][y]==grids[x][y]||grids[x-2][y]==-grids[x][y])&&(grids[x-1][y]==grids[x][y]||grids[x-1][y]==-grids[x][y])){
				return true;
			}
		}
		if(x<7){
			if((grids[x+2][y]==grids[x][y]||grids[x+2][y]==-grids[x][y])&&(grids[x+1][y]==grids[x][y]||grids[x+1][y]==-grids[x][y])){
				return true;
			}
		}
		if((x>0)&&(x<8)){
			if((grids[x-1][y]==grids[x][y]||grids[x-1][y]==-grids[x][y])&&(grids[x+1][y]==grids[x][y]||grids[x+1][y]==-grids[x][y])){
				return true;
			}
		}
		
		if(y>1){
			if((grids[x][y-2]==grids[x][y]||grids[x][y-2]==-grids[x][y])&&(grids[x][y-1]==grids[x][y]||grids[x][y-1]==-grids[x][y])){
				return true;
			}
		}
		if(y<7){
			if((grids[x][y+2]==grids[x][y]||grids[x][y+2]==-grids[x][y])&&(grids[x][y+1]==grids[x][y]||grids[x][y+1]==-grids[x][y])){
				return true;
			}
		}
		if((y>0)&&(y<8)){
			if((grids[x][y-1]==grids[x][y]||grids[x][y-1]==-grids[x][y])&&(grids[x][y+1]==grids[x][y]||grids[x][y+1]==-grids[x][y])){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 对盘面进行消除，参数为swap的两个位置，参数可以是null，代表此次消除是新方块产生后自动的消除
	 * @param p1
	 * @param p2
	 */
	private void pre_delete(PositionVO p1,PositionVO p2){
		int added_score = 0;
		
		int[][] bdChange = new int[9][9];
		int i,j,k,low,high;
		boolean tmp;
		for(i = 0;i<9;i++){
			for(j = 0;j<9;j++){
				bdChange[i][j] = 0;
			}
		}
		for(i = 0;i<9;i++){
			for(j = 0;j<7;j++){
				if(grids[i][j]!=0&&Math.abs(grids[i][j])==Math.abs(grids[i][j+1])&&Math.abs(grids[i][j])==Math.abs(grids[i][j+2])){
					if(j==6||Math.abs(grids[i][j])!=Math.abs(grids[i][j+3])){
						
						added_score +=100;
						
						for(k = j;k<j+3;k++){
							for(low = i;low>=0&&Math.abs(grids[i][k])==Math.abs(grids[low][k]);low--);
							low++;
							for(high = i;high<=8&&Math.abs(grids[i][k])==Math.abs(grids[high][k]);high++);
							high--;
							if(high-low<2){
								bdChange[i][k] = 1;
							}
							if(high-low==2||high-low==3){
								added_score +=100*(high-low-1);
								bdChange[i][k] = 2;
							}
							if(high-low>=4){
								added_score +=500;
								bdChange[i][k] = 3;
							}		
						}
						j+=3;
					}
					
					else if(j==5||Math.abs(grids[i][j])!=Math.abs(grids[i][j+4])){
						added_score +=200;
						tmp = true;
						for(k = j;k<j+4;k++){
							for(low = i;low>=0&&Math.abs(grids[i][k])==Math.abs(grids[low][k]);low--);
							low++;
							for(high = i;high<=8&&Math.abs(grids[i][k])==Math.abs(grids[high][k]);high++);
							high--;
							if(high-low<2){
								bdChange[i][k] = 1;
							}
							if(high-low==2){
								added_score +=100;
								bdChange[i][k] = 2;
								tmp = false;
							}
							if(high-low>=3){
								added_score +=(high-low==3)?200:500;
								bdChange[i][k] = 3;
								tmp = false;
							}		 
						}
						if(tmp){
							if(p1 != null){
								if(i==p1.getX()&&j<=p1.getY()&&p1.getY()<=j+3){
									bdChange[p1.getX()][p1.getY()] = 2;
								}else if(i==p2.getX()&&j<=p2.getY()&&p2.getY()<=j+3){
									bdChange[p2.getX()][p2.getY()] = 2;
								}else{
									if(mode==1||mode==3){
										bdChange[i][j+(new Random().nextBoolean()?1:2)] = 2;	
									}else{
										bdChange[i][j+queue.next()%2+1] = 2;
									}
								}
							}else{
								if(mode==1||mode==3){
									bdChange[i][j+(new Random().nextBoolean()?1:2)] = 2;	
								}else{
									bdChange[i][j+queue.next()%2+1] = 2;
								}
							}
						}
						j+=4;
					}
					
					else{
						added_score +=500;
						tmp = true;
						for(k = j;k<9&&Math.abs(grids[i][k])==Math.abs(grids[i][j]);k++){
							for(low = i;low>=0&&Math.abs(grids[i][k])==Math.abs(grids[low][k]);low--);
							low++;
							for(high = i;high<=8&&Math.abs(grids[i][k])==Math.abs(grids[high][k]);high++);
							high--;
							if(high-low<2){
								bdChange[i][k] = 1;
							}else{
								if(high-low==2){
									added_score +=100;
								}
								if(high-low==3){
									added_score +=200;
								}
								if(high-low>3){
									added_score +=500;
								}
								bdChange[i][k] = 3;
								tmp = false;
							}
						}
						if(tmp){
							bdChange[i][j+2] = 3;
						}
						j = k+1;
					}
				}
			}
		}
		for(j = 0;j<9;j++){
			for(i = 0;i<7;i++){
				if(grids[i][j]!=0){
					tmp = (bdChange[i][j]==0);
					low = i;
					for(high = i+1;high<=8&&Math.abs(grids[i][j])==Math.abs(grids[high][j]);high++){
						if(bdChange[high][j]!=0){
							tmp = false;
						}
					}
					high--;
					if(high-low>=2){
						for(k=low;k<=high;k++){
							if(bdChange[k][j]==0){
								bdChange[k][j]=1;
							}
						}
					}
					if(tmp){
						if(high-low==2){
							added_score +=100;
						}
						if(high-low==3){
							added_score +=200;
							
							if(p1 != null){
								if(low<=p1.getX()&&p1.getX()<=high&&j==p1.getY()){
									bdChange[p1.getX()][p1.getY()] = 2;
								}else if(low<=p2.getX()&&p2.getX()<=high&&j==p2.getY()){
									bdChange[p2.getX()][p2.getY()] = 2;
								}else{
									if(mode==1||mode==3){
										bdChange[low+(new Random().nextBoolean()?1:2)][j] = 2;
									}else{
										bdChange[low+queue.next()%2+1][j] = 2;
									}
								}
							}else{
								if(mode==1||mode==3){
									bdChange[low+(new Random().nextBoolean()?1:2)][j] = 2;
								}else{
									bdChange[low+queue.next()%2+1][j] = 2;
								}
							}
						}
						if(high-low>=4){
							added_score +=500;
							bdChange[low+2][j] = 3;
						}
					}
				}
			}
		}
		
		for(i = 0;i<9;i++){
			for(j = 0;j<9;j++){
				if(bdChange[i][j]!=0){
					if(grids[i][j]<0){
						if(i>0){
							if(j>0){
								if(bdChange[i-1][j-1]==0){
									added_score +=50;
									bdChange[i-1][j-1]=1;
								}
							}
							if(bdChange[i-1][j]==0){
								added_score +=50;
								bdChange[i-1][j]=1;
							}
							if(j<8){
								if(bdChange[i-1][j+1]==0){
									added_score +=50;
									bdChange[i-1][j+1]=1;
								}
							}
						}
						if(i<8){
							if(j>0){
								if(bdChange[i+1][j-1]==0){
									added_score +=50;
									bdChange[i+1][j-1]=1;
								}
							}
							if(bdChange[i+1][j]==0){
								added_score +=50;
								bdChange[i+1][j]=1;
							}
							if(j<8){
								if(bdChange[i+1][j+1]==0){
									added_score +=50;
									bdChange[i+1][j+1]=1;
								}
							}
						}
						if(j>0){
							if(bdChange[i][j-1]==0){
								added_score +=50;
								bdChange[i][j-1]=1;
							}
						}
						if(j<8){
							if(bdChange[i][j+1]==0){
								added_score +=50;
								bdChange[i][j+1]=1;
							}
						}
					}
					
					if(grids[i][j]==7){
						for(k=0;k<9;k++){
							if(bdChange[i][k]==0){
								added_score +=50;
								bdChange[i][k]=1;
							}
							if(bdChange[k][j]==0){
								added_score +=50;
								bdChange[k][j]=1;
							}
						}
					}
				}
			}
		}
		
		addScore(added_score);
		
		delete(bdChange);		
	}
	
	private void delete(final int[][] bdChange){
		exp++;
		coin+=1;
		tip_timer = timer;
		int i,j,k;
		int[][] nextChange = new int[9][9];
		boolean tmp = false;
		int[][] grids_copy = grids;
		bp.setGrids(grids_copy);
//		show(bdChange);
		bp.deleteUnits(bdChange);
		
		for(i = 0;i<9;i++){
			for(j = 0;j<9;j++){
				if(bdChange[i][j]!=0){
					if(grids[i][j]<0){
						tmp = true;
						if(i>0){
							if(j>0){
								if(bdChange[i-1][j-1]!=1){
									nextChange[i-1][j-1]=1;
								}
							}
							if(bdChange[i-1][j]!=1){
								nextChange[i-1][j]=1;
							}
							if(j<8){
								if(bdChange[i-1][j+1]!=1){
									nextChange[i-1][j+1]=1;
								}
							}
						}
						if(i<8){
							if(j>0){
								if(bdChange[i+1][j-1]!=1){
									nextChange[i+1][j-1]=1;
								}
							}
							if(bdChange[i+1][j]!=1){
								nextChange[i+1][j]=1;
							}
							if(j<8){
								if(bdChange[i+1][j+1]!=1){
									nextChange[i+1][j+1]=1;
								}
							}
						}
						if(j>0){
							if(bdChange[i][j-1]!=1){
								nextChange[i][j-1]=1;
							}
						}
						if(bdChange[i][j]!=1){
							nextChange[i][j-1]=1;
						}
						if(j<8){
							if(bdChange[i][j+1]!=1){
								nextChange[i][j+1]=1;
							}
						}
						
					}
					if(grids[i][j]==7){
						if(mode==3||mode==5)
							NetService.deleteB();
						tmp = true;
						for(k=0;k<9;k++){
							if(bdChange[i][k]!=1){
								nextChange[i][k]=1;
							}
							if(bdChange[k][j]!=1){
								nextChange[k][j]=1;
							}
						}
					}
				}
				switch(bdChange[i][j]){
				case 1:
					grids[i][j] = 0;
					break;
				case 2:
					grids[i][j] = -Math.abs(grids[i][j]);
					break;
				case 3:
					grids[i][j] = 7;
					break;
				}
			}
		}
		int added_score = 0;
		for(i=0;i<9;i++){
			for(j=0;j<9;j++){
				if(grids[i][j] == 0){
					nextChange[i][j] = 0;
				}
				if(nextChange[i][j]==1){
					added_score+=50;
				}
			}
		}
//		show(grids);
//		bp.deleteUnits(grids);
		if(tmp){
			addScore(added_score);
			delete(nextChange);
		}else{
			Thread thread = new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					falldown();
					
				}
				
			});
			thread.start();		
		}
	}
	
	/**
	 * 色块掉落方法
	 */
	private void falldown(){
		int i,j,k,l;
		int color;
		final ArrayList<MoveandCreateActionVO> list = new ArrayList<MoveandCreateActionVO>();
		if(direction==0){
			for(i=0;i<9;i++){
				for(j=8;j>=0;j--){
					if(grids[i][j]==0){
						for(k=j,l=-1;k>=0;k--){
							if(grids[i][k]!=0){
								l = k;
								break;
							}
						}
						if(l!=-1){
							grids[i][j] = grids[i][l];
							grids[i][l] = 0;
							try {
								list.add(new MoveandCreateActionVO(0,new PositionVO(i,l),0,j-l));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else{
							if(mode==1||mode==3){
								color = random();
							}else{
								color = queue.next();
							}
							grids[i][j] = color;
							try {
								list.add(new MoveandCreateActionVO(1,color,new PositionVO(i,-1),0,j+1));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}else{
			for(j=0;j<9;j++){
				for(i=8;i>=0;i--){
					if(grids[i][j]==0){
						for(k=i,l=-1;k>=0;k--){
							if(grids[k][j]!=0){
								l = k;
								break;
							}
						}
						if(l!=-1){
							grids[i][j] = grids[l][j];
							grids[l][j] = 0;
							try {
								list.add(new MoveandCreateActionVO(0,new PositionVO(l,j),1,i-l));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else{
							if(mode==1||mode==3){
								color = random();
							}else{
								color = queue.next();
							}
							grids[i][j] = color;
							try {
								list.add(new MoveandCreateActionVO(1,color,new PositionVO(-1,j),1,i+1));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		
//		show(grids);
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					//Thread.sleep(200);
					bp.dealWithMoveAction(list);
					Thread.sleep(120);
					loop:{
						for(int i=0;i<9;i++){
							for(int j=0;j<9;j++){
								if(grids[i][j]!=0&&canRemove(new PositionVO(i,j))){
									pre_delete(null,null);
									break loop;
								}
							}
						}
						
						judge();
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			
		});
		
		thread.start();
	}
	
	private void judge(){
		for(int i = 0;i<9;i++){
			for(int j = 0;j<9;j++){
				if(grids[i][j]<0&&grids[i][j]==7){
					return;
				}
			}
		}
		try{
			tip();
		}catch(IllegalArgumentException e){
			grids[4][4] = - grids[4][4];
		}
	}
	
	
	/**
	 * 提供一个返回一个长度为2的PositionVO数组，提供随机一对交换后能消除的位置
	 * @return
	 */
	private PositionVO[] tip(){
		ArrayList<PositionVO> listA = new ArrayList<PositionVO>();
		ArrayList<PositionVO> listB = new ArrayList<PositionVO>();
		PositionVO pA;
		PositionVO pB;
		int[][] copy = grids;
		int i,j;
		int tmp;
		for(i = 0;i<9;i++){
			for(j = 0;j<8;j++){
				tmp = copy[i][j];
				copy[i][j] = copy[i][j+1];
				copy[i][j+1] = tmp;
				pA = new PositionVO(i,j);
				pB = new PositionVO(i,j+1);
				if(canRemove(pA)||canRemove(pB)){
					listA.add(pA);
					listB.add(pB);
				}
				copy[i][j+1] = copy[i][j];
				copy[i][j] = tmp;
			}
		}
		for(j = 0;j<9;j++){
			for(i = 0;i<8;i++){
				tmp = copy[i][j];
				copy[i][j] = copy[i+1][j];
				copy[i+1][j] = tmp;
				pA = new PositionVO(i,j);
				pB = new PositionVO(i+1,j);
				if(canRemove(pA)||canRemove(pB)){
					listA.add(pA);
					listB.add(pB);
				}
				copy[i+1][j] = copy[i][j];
				copy[i][j] = tmp;
			}
		}
		int r = new Random().nextInt(listA.size());
		pA = listA.get(r);
		pB = listB.get(r);
		return new PositionVO[]{pA,pB};
	}
	
	public void lockColor(){
		int color = random();
		bp.lock(color);
		color_lock_timer[color-1] = timer;
	}
	
	public void lockToolC(){
		toolC_lock_timer = timer;
		bp.getParent().lockToolC();
	}
	
	/**
	 * 按照combo和得分up的加强来加分数
	 * @param addedScore
	 */
	private void addScore(int addedScore){
		score += addedScore*(tools[1]?score_up_rate:1)*(combo_power?2:1);
		bp.getParent().refreshScore(score);
	}
	
	/**
	 * 打印9x9数组，测试用，非必要算法
	 * @param list
	 */
//	private void show(int[][] list){
//		
//		for(int j = 0;j<9;j++){
//			for(int i = 0;i<9;i++){
//				System.out.print(list[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		System.out.println();
//		
//	}
	
	
	/**
	 * 生成随机颜色
	 * @return
	 */
	private int random(){
		return new Random().nextInt(6)+1;
	}
	
	public int[][] grids(){
		return grids;
	}
	
	public int direction(){
		return direction;
	}

	

}

class NewTimer {
	BoardModel bm;
	int timercount;
	Timer timer;
	
	NewTimer(BoardModel bm){
		this.bm = bm;
		timercount = 600;
		timer = new Timer();
	}
	public void start(){
		timer.schedule(new ScheduleRunTask(), 0, 100);
	}
	
	public void stop(){
		timer.cancel();
	}
	
	public void return5(){
		timercount = 60;
	}
	
	class ScheduleRunTask extends TimerTask {
        public void run() {
        	timercount--;
        	if(timercount%10==0){
        		bm.changeTimer(timercount);
        	}
        	
        }
    }
}


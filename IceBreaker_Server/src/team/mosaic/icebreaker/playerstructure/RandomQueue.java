package team.mosaic.icebreaker.playerstructure;

/**
 * 随即匹配排队队列
 * @author HJW
 *
 */
public class RandomQueue {

	private int mode;//0为双人协作，1为pk，2为四人协作,3为四人两组pk
	private WaitNode first;//队首结点
	private WaitNode last;//队尾结点
	private int count;//队列人数
	
	/**
	 * 构造函数
	 * @param mode 模式（0为协作，1为pk）
	 * @param num 协作人数
	 */
	public RandomQueue(int mode){
		this.mode = mode;
	}
	/**
	 * 返回一局游戏人数
	 * @return 人数 
	 */
	private int getNum(){
		if(mode == 2 || mode == 3)
			return 4;
		return 2;
	}
	/**
	 * 将玩家排入队列
	 * @param player 排队玩家
	 */
	public void queueUp(Player player){
		WaitNode node = new WaitNode(player);
		if(count == 0){//队列为空时，排队人直接成为队首(队尾)
			first = node;
			last = node;
		}
		else{//队列不为空，排到队尾
			last.setNext(node);
			last = node;
		}
		count ++;
		if(count >= getNum())//人数足够则开始游戏
			startGame();
	}
	/**
	 * 玩家放弃排队
	 * @param player 排队玩家
	 */
	public void quitQueue(Player player){
		WaitNode p = first;
		if (p.getPlayer().getID().equals(player.getID())) {//玩家在队首
			first = p.getNext();
			if(count == 1)
				last = null;
		}
		else{//玩家不在队首
			while(!p.getNext().getPlayer().getID().equals(player.getID())){
				p = p.getNext();
			}
			p.setNext(p.getNext().getNext());
		}
		count --;
	}
	
	public boolean has(Player player){
		WaitNode p = first;
		if(p == null)
			return false;
		do{
			if(p.getPlayer().getID().equals(player.getID()))
				return true;
			p = p.getNext();
		}while(p != null);
		
		return false;
	}
	/**
	 * 得到队首玩家
	 * @return 队首玩家
	 */
	private Player getTurn(){
		Player ret = null;
		if(count == 1){//队列只有一个人
			ret = first.getPlayer();
			first = null;
			last = null;
			count --;
		}
		else if(count > 1){//队列有至少两个人
			ret = first.getPlayer();
			first = first.getNext();
			count --;
		}
		return ret;
	}
	/**
	 * 开始游戏
	 */
	private void startGame(){
		Player p1 = getTurn();
		Player p2 = getTurn();
		switch(mode){
		case 0://双人协作
			p2.setCooperationPair(new CooperationPair(p1, p2));
			break;
		case 1://双人pk
			p2.setCompetitionPair(new CompetitionPair(p1, p2));
			break;
		case 2://四人协作
			Player p3 = getTurn();
			Player p4 = getTurn();
			p1.setCooperationFoursome(new CooperationFoursome(p1, p2, p3, p4));
			break;
		case 3://四人两组pk
			Player p33 = getTurn();
			Player p44 = getTurn();
			p1.setCompetitionFoursome(new CompetitionFoursome(p1,p2,p33,p44));
			break;
		}
	}
	
}

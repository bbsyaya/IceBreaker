package team.mosaic.icebreaker.playerstructure;
/**
 * 随即匹配队列的排队结点
 * @author HJW
 *
 */
public class WaitNode {

	private Player player;
	private WaitNode next;
	
	/**
	 * 构造函数
	 * @param player 玩家
	 * @param next 下一个玩家结点
	 */
	public WaitNode(Player player,WaitNode next){
		this.player = player;
		this.next = next;
	}
	/**
	 * 构造函数
	 * @param player 玩家
	 */
	public WaitNode(Player player){
		this(player, null);
	}
	/**
	 * 得到结点玩家
	 * @return 玩家
	 */
	public Player getPlayer(){
		return player;
	}
	/**
	 * 得到下一个结点
	 * @return 下一个结点
	 */
	public WaitNode getNext(){
		return next;
	}
	/**
	 * 设置下一个结点
	 * @param node 下一个结点
	 */
	public void setNext(WaitNode node){
		this.next = node;
	}
	
}

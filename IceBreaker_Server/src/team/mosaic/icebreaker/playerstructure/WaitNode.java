package team.mosaic.icebreaker.playerstructure;
/**
 * �漴ƥ����е��Ŷӽ��
 * @author HJW
 *
 */
public class WaitNode {

	private Player player;
	private WaitNode next;
	
	/**
	 * ���캯��
	 * @param player ���
	 * @param next ��һ����ҽ��
	 */
	public WaitNode(Player player,WaitNode next){
		this.player = player;
		this.next = next;
	}
	/**
	 * ���캯��
	 * @param player ���
	 */
	public WaitNode(Player player){
		this(player, null);
	}
	/**
	 * �õ�������
	 * @return ���
	 */
	public Player getPlayer(){
		return player;
	}
	/**
	 * �õ���һ�����
	 * @return ��һ�����
	 */
	public WaitNode getNext(){
		return next;
	}
	/**
	 * ������һ�����
	 * @param node ��һ�����
	 */
	public void setNext(WaitNode node){
		this.next = node;
	}
	
}

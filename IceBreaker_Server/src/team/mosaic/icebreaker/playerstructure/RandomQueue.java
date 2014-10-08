package team.mosaic.icebreaker.playerstructure;

/**
 * �漴ƥ���ŶӶ���
 * @author HJW
 *
 */
public class RandomQueue {

	private int mode;//0Ϊ˫��Э����1Ϊpk��2Ϊ����Э��,3Ϊ��������pk
	private WaitNode first;//���׽��
	private WaitNode last;//��β���
	private int count;//��������
	
	/**
	 * ���캯��
	 * @param mode ģʽ��0ΪЭ����1Ϊpk��
	 * @param num Э������
	 */
	public RandomQueue(int mode){
		this.mode = mode;
	}
	/**
	 * ����һ����Ϸ����
	 * @return ���� 
	 */
	private int getNum(){
		if(mode == 2 || mode == 3)
			return 4;
		return 2;
	}
	/**
	 * ������������
	 * @param player �Ŷ����
	 */
	public void queueUp(Player player){
		WaitNode node = new WaitNode(player);
		if(count == 0){//����Ϊ��ʱ���Ŷ���ֱ�ӳ�Ϊ����(��β)
			first = node;
			last = node;
		}
		else{//���в�Ϊ�գ��ŵ���β
			last.setNext(node);
			last = node;
		}
		count ++;
		if(count >= getNum())//�����㹻��ʼ��Ϸ
			startGame();
	}
	/**
	 * ��ҷ����Ŷ�
	 * @param player �Ŷ����
	 */
	public void quitQueue(Player player){
		WaitNode p = first;
		if (p.getPlayer().getID().equals(player.getID())) {//����ڶ���
			first = p.getNext();
			if(count == 1)
				last = null;
		}
		else{//��Ҳ��ڶ���
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
	 * �õ��������
	 * @return �������
	 */
	private Player getTurn(){
		Player ret = null;
		if(count == 1){//����ֻ��һ����
			ret = first.getPlayer();
			first = null;
			last = null;
			count --;
		}
		else if(count > 1){//����������������
			ret = first.getPlayer();
			first = first.getNext();
			count --;
		}
		return ret;
	}
	/**
	 * ��ʼ��Ϸ
	 */
	private void startGame(){
		Player p1 = getTurn();
		Player p2 = getTurn();
		switch(mode){
		case 0://˫��Э��
			p2.setCooperationPair(new CooperationPair(p1, p2));
			break;
		case 1://˫��pk
			p2.setCompetitionPair(new CompetitionPair(p1, p2));
			break;
		case 2://����Э��
			Player p3 = getTurn();
			Player p4 = getTurn();
			p1.setCooperationFoursome(new CooperationFoursome(p1, p2, p3, p4));
			break;
		case 3://��������pk
			Player p33 = getTurn();
			Player p44 = getTurn();
			p1.setCompetitionFoursome(new CompetitionFoursome(p1,p2,p33,p44));
			break;
		}
	}
	
}

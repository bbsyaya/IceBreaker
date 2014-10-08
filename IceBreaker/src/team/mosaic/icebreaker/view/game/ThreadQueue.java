package team.mosaic.icebreaker.view.game;

public class ThreadQueue {

	private ThreadNode head;
	private ThreadNode rear;
	
	public void enqueue(Thread thread){
		ThreadNode node = new ThreadNode(thread, null);
		if(head == null){
			head = node;
			rear = node;
		}
		else{
			rear.setNext(node);
			rear = node;
		}
	}
	
	public Thread dequeue(){
		Thread ret = null;
		if(head == null)
			return ret;
		if(head == rear){
			ret = head.getThread();
			head = null;
			rear = null;
		}else{
			ret = head.getThread();
			head = head.getNext();
		}
		return ret;
	}
	
	public static void main(String[] args){
		Thread thread = new Thread();
		System.out.println(thread.isAlive());
	}
	
}

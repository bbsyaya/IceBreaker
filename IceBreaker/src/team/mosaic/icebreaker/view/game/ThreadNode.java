package team.mosaic.icebreaker.view.game;

public class ThreadNode {

	private Thread thread;
	private ThreadNode next;
	
	public ThreadNode(Thread thread,ThreadNode next){
		this.thread = thread;
		this.next = next;
	}
	
	public Thread getThread(){
		return this.thread;
	}
	
	public ThreadNode getNext(){
		return this.next;
	}
	
	public void setNext(ThreadNode thread){
		this.next = thread;
	}
	
}

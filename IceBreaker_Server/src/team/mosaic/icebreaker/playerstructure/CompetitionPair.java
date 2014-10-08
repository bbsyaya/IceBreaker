package team.mosaic.icebreaker.playerstructure;

import java.io.IOException;

import team.mosaic.icebreaker.vo.MessageVO;
import team.mosaic.icebreaker.vo.ResultVO;

public class CompetitionPair {

	private Player p1;
	private Player p2;
	private boolean end1;
	private boolean end2;

	private int endCount;

	public CompetitionPair(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.end1 = false;
		this.end2 = false;
		endCount = 0;
		p1.setHost(true);
		link();
	}

	private void link() {
		p1.setCompetitionPair(this);
		try {
			p1.sendMessage(new MessageVO("startpk", p2.getID()));
			p2.sendMessage(new MessageVO("startpk", p1.getID()));
			p1.setFree(false);
			p2.setFree(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dismiss() {
		p1.setCompetitionPair(null);
		p2.setCompetitionPair(null);
		p1.setHost(false);
		p1.setFree(true);
		p2.setFree(true);
	}

	public void quit(Player player){
		player.setCompetitionPair(null);
		player.setFree(true);
		if(player.getID().equals(p1.getID())){
			end1 = true;
		}
		else{
			end2 = true;
		}
		end();
	}
	
	public void end() {
		endCount++;
		if (endCount == 2) {
			try {
				if(!end1)
					p1.sendMessage(new MessageVO("pkresult", new ResultVO(p1.getResult().getRecordBroken(), p1
						.getResult().getLevelUp(), end2?-1:p2.getScore())));
				if(!end2)
					p2.sendMessage(new MessageVO("pkresult", new ResultVO(p2.getResult().getRecordBroken(), p2
						.getResult().getLevelUp(), end1?-1:p1.getScore())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dismiss();
		}
	}

	public void deleteB(Player player) {
		try {
			if (p1.getID().equals(player.getID()) && !end2)
				p2.sendMessage(new MessageVO("randomlock", null));
			else if(p2.getID().equals(player.getID()) && !end1)
				p1.sendMessage(new MessageVO("randomlock", null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void superTime(Player player){
		try {
			if (p1.getID().equals(player.getID()) && !end2)
				p2.sendMessage(new MessageVO("lockc", null));
			else if(p2.getID().equals(player.getID()) && !end1)
				p1.sendMessage(new MessageVO("lockc", null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

package team.mosaic.icebreaker.playerstructure;

import java.io.IOException;

import team.mosaic.icebreaker.vo.MessageVO;
import team.mosaic.icebreaker.vo.ResultVO;

public class CompetitionFoursome {

	private Player p1;
	private Player p2;
	private Player p3;
	private Player p4;
	private CooperationPair cp1;
	private CooperationPair cp2;
	private boolean end1;
	private boolean end2;
	private boolean end3;
	private boolean end4;
	private int endCount;

	public CompetitionFoursome(Player p1, Player p2, Player p3, Player p4) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		endCount = 0;
		p2.setCompetitionFoursome(this);
		p3.setCompetitionFoursome(this);
		p4.setCompetitionFoursome(this);
		cp1 = new CooperationPair(p1, p4, p2, p3);
		cp2 = new CooperationPair(p2, p3, p1, p4);
		p1.setSubCooperationPair(cp1);
		p4.setSubCooperationPair(cp1);
		p2.setSubCooperationPair(cp2);
		p3.setSubCooperationPair(cp2);
	}

	public CooperationPair getPair(Player player) {
		if (player.getID().equals(p1.getID())
				|| player.getID().equals(p4.getID()))
			return cp1;
		else
			return cp2;
	}

	public void quit(Player player) {
		player.setFree(true);
		if (player.getID().equals(p1.getID())) {
			end1 = true;
			cp1.quit(player);
		} else if (player.getID().equals(p2.getID())) {
			end2 = true;
			cp2.quit(player);
		} else if (player.getID().equals(p3.getID())) {
			end3 = true;
			cp2.quit(player);
		} else if (player.getID().equals(p4.getID())) {
			end4 = true;
			cp1.quit(player);
		}
		if (end1 && end4) {
			end();
		}
		if (end2 && end3) {
			end();
		}
	}

	public void end() {
		endCount++;
		if (endCount == 2) {
			try {
				if (!end1)
					p1.sendMessage(new MessageVO("pkfourresult", new ResultVO(
							false, p1.getResult().getLevelUp(),
							end2 && end3 ? -1 : (end2 ? p3.getScore() : p2
									.getScore()))));
				if (!end2)
					p2.sendMessage(new MessageVO("pkfourresult", new ResultVO(
							false, p2.getResult().getLevelUp(),
							end1 && end4 ? -1 : (end1 ? p4.getScore() : p1
									.getScore()))));
				if (!end3)
					p3.sendMessage(new MessageVO("pkfourresult", new ResultVO(
							false, p1.getResult().getLevelUp(),
							end1 && end4 ? -1 : (end1 ? p4.getScore() : p1
									.getScore()))));
				if (!end4)
					p4.sendMessage(new MessageVO("pkfourresult", new ResultVO(
							false, p1.getResult().getLevelUp(),
							end2 && end3 ? -1 : (end2 ? p3.getScore() : p2
									.getScore()))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dismiss();
		}
	}

	public void dismiss() {
		p1.setCompetitionFoursome(null);
		p2.setCompetitionFoursome(null);
		p3.setCompetitionFoursome(null);
		p4.setCompetitionFoursome(null);
		p1.setSubCooperationPair(null);
		p2.setSubCooperationPair(null);
		p3.setSubCooperationPair(null);
		p4.setSubCooperationPair(null);
		p1.setFree(true);
		p2.setFree(true);
		p3.setFree(true);
		p4.setFree(true);
	}

	public void deleteB(Player player) {
		if (p1.getID().equals(player.getID())) {
			cp2.randomLock();
		} else if (p2.getID().equals(player.getID())) {
			cp1.randomLock();
		} else if (p3.getID().equals(player.getID())) {
			cp1.randomLock();
		} else if (p4.getID().equals(player.getID())) {
			cp2.randomLock();
		}
	}

	public void superTime(Player player) {
		if (p1.getID().equals(player.getID())) {
			cp2.lockC();
		} else if (p2.getID().equals(player.getID())) {
			cp1.lockC();
		} else if (p3.getID().equals(player.getID())) {
			cp1.lockC();
		} else if (p4.getID().equals(player.getID())) {
			cp2.lockC();
		}
	}

}

package team.mosaic.icebreaker.utility;

import java.util.Comparator;

import team.mosaic.icebreaker.vo.ScoreVO;

/**
 * 对排行榜的排序方法
 * 
 * @author HJW
 * 
 */
public class RankSort implements Comparator<ScoreVO> {

	@Override
	public int compare(ScoreVO o1, ScoreVO o2) {
		// TODO Auto-generated method stub
		if (o1.getScore() < o2.getScore())
			return 1;
		else if (o1.getScore() == o2.getScore())
			return o1.getID().compareTo(o2.getID());
		return -1;
	}

}

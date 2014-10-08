package team.mosaic.icebreaker.dto;

import team.mosaic.icebreaker.dao.InfoDAO;

public class InfoDTO {
	
	private static String LEVEL = "level";
	private static String EXP = "exp";
	private static String COIN = "coin";
	private static String SCORE = "score";
	
	
	private InfoDAO infoDAO = new InfoDAO();
	/*
	 * 在Info表中创建对象
	 * 注册时
	 */
	public void creatNewInfoObject(String id){		
		infoDAO.creat(id);
	}
	
	
	/*
	 * 得到用户等级
	 * @param id 账号
	 * @return int level 等级
	 */
	public int getLevel(String id){
		int level = 1;
		level = infoDAO.getcontent(id, LEVEL);
		return level;
	}
	
	
	/*
	 * 升级
	 * @param id 账号         level　修改的等级
	 * @return boolean
	 */
	
	public void voidchangeLevel(String id,int level){
		infoDAO.setcontent(id, LEVEL, level);
	}
	
	/*
	 * 得到经验
	 *  @param id 账号  
	 *  @return int  exp　经验       （需补一张表记录等级和经验的换算关系）
	 */
	public int getExp(String id){
		int exp = 0;
		exp = infoDAO.getcontent(id, EXP);
		return exp;
	}
	
	/*
	 * 增加经验
	 * @param id  账号    exp 修改的经验值
	 * @return boolean
	 */
	public void changeExp(String id,int exp){
        infoDAO.setcontent(id, EXP, exp);
	}

	/*
	 * 得到用户金币数量
	 * @param id 账号
	 * @return int coin  金币数
	 */
	public int getCoin(String id){
		int coin = 0;
		coin = infoDAO.getcontent(id, COIN);
		return coin;
	}
	
	
	/*
	 * 修改金币数量
	 * @param  id 账号       coin 改变后的金币数量
	 * @return  boolean
	 */
	public void changeCoin(String id,int coin){
        infoDAO.setcontent(id, COIN, coin);
	}
	/*
	 * 得到最高分
	 */
	public int getScore(String id){
		int score = 0;
		score = infoDAO.getcontent(id, SCORE);
		return score;
		
	}
	
	/*
	 * 修改最高分
	 */
	public void changeScore(String id,int score){
        infoDAO.setcontent(id, SCORE, score);
	}
}
	
	
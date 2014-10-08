package team.mosaic.icebreaker.dto;

import team.mosaic.icebreaker.dao.InfoDAO;

public class InfoDTO {
	
	private static String LEVEL = "level";
	private static String EXP = "exp";
	private static String COIN = "coin";
	private static String SCORE = "score";
	
	
	private InfoDAO infoDAO = new InfoDAO();
	/*
	 * ��Info���д�������
	 * ע��ʱ
	 */
	public void creatNewInfoObject(String id){		
		infoDAO.creat(id);
	}
	
	
	/*
	 * �õ��û��ȼ�
	 * @param id �˺�
	 * @return int level �ȼ�
	 */
	public int getLevel(String id){
		int level = 1;
		level = infoDAO.getcontent(id, LEVEL);
		return level;
	}
	
	
	/*
	 * ����
	 * @param id �˺�         level���޸ĵĵȼ�
	 * @return boolean
	 */
	
	public void voidchangeLevel(String id,int level){
		infoDAO.setcontent(id, LEVEL, level);
	}
	
	/*
	 * �õ�����
	 *  @param id �˺�  
	 *  @return int  exp������       ���貹һ�ű��¼�ȼ��;���Ļ����ϵ��
	 */
	public int getExp(String id){
		int exp = 0;
		exp = infoDAO.getcontent(id, EXP);
		return exp;
	}
	
	/*
	 * ���Ӿ���
	 * @param id  �˺�    exp �޸ĵľ���ֵ
	 * @return boolean
	 */
	public void changeExp(String id,int exp){
        infoDAO.setcontent(id, EXP, exp);
	}

	/*
	 * �õ��û��������
	 * @param id �˺�
	 * @return int coin  �����
	 */
	public int getCoin(String id){
		int coin = 0;
		coin = infoDAO.getcontent(id, COIN);
		return coin;
	}
	
	
	/*
	 * �޸Ľ������
	 * @param  id �˺�       coin �ı��Ľ������
	 * @return  boolean
	 */
	public void changeCoin(String id,int coin){
        infoDAO.setcontent(id, COIN, coin);
	}
	/*
	 * �õ���߷�
	 */
	public int getScore(String id){
		int score = 0;
		score = infoDAO.getcontent(id, SCORE);
		return score;
		
	}
	
	/*
	 * �޸���߷�
	 */
	public void changeScore(String id,int score){
        infoDAO.setcontent(id, SCORE, score);
	}
}
	
	
package team.mosaic.icebreaker.vo;

import java.io.Serializable;

public class MoveandCreateActionVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//0�������������еķ�������ƶ���1�������·�������
	private int type;
	
	//�����ƶ�����ʼλ�ã�����������·��飬λ���Ƕ���Ч���еĽ����λ��
	private PositionVO gridOld;
	
	//��ɫ���������������·���
	private int color;
	
	//�ƶ�����0Ϊ���£�1Ϊ����
	private int direction;
	
	//�ƶ��ĸ���
	private int steps;
	
	//moveaction�Ĺ���
	public MoveandCreateActionVO(int type,PositionVO gridOld,int direction,int steps) throws Exception{
		if(type!=0){
			throw new Exception("�����빹�캯����ƥ��");
		}
		this.type = 0;
		this.gridOld = gridOld;
		this.direction = direction;
		this.steps = steps;
		
	}
	
	//createaction�Ĺ���
	public MoveandCreateActionVO(int type,int color,PositionVO gridOld,int direction,int steps) throws Exception{
		if(type!=1){
			throw new Exception("�����빹�캯����ƥ��");
		}
		this.type = 1;
		this.color = color;
		this.gridOld = gridOld;
		this.direction = direction;
		this.steps = steps;
	}
	
	public int type(){
		return type;
	}
	
	public int color() throws Exception{
		if(type!=1){
			throw new Exception("�����뺯����ƥ��");
		}
		return color;
	}

	public PositionVO gridOld(){
		return gridOld;
	}
	
	public int direction(){
		return direction;
	}

	public int steps(){
		return steps;
	}
}

package team.mosaic.icebreaker.dto;

import java.util.ArrayList;

import team.mosaic.icebreaker.dao.RecordDAO;

public class RecordDTO {
	
	private RecordDAO recordDAO = new RecordDAO();
	
	public void  initialization(String id,String time,int conti,int score){
	     recordDAO.initialization(id, time, conti, score);
	}

	
	public ArrayList<String> getRecord(String id){
		return recordDAO.getRecord(id);
	}
}

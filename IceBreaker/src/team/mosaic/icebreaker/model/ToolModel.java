package team.mosaic.icebreaker.model;

import java.util.ArrayList;

import team.mosaic.icebreaker.modelservice.ToolModelService;
import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.viewservice.ToolViewService;

/**
 * ����ģ����
 * @author acer
 *
 */
public class ToolModel implements ToolModelService{
	
	private ToolViewService tvs ;
	private ArrayList<Integer> num;
	private boolean buy = true;
	private boolean enough = true;
	public static boolean getTool;
	
	public ToolModel(){};
		
	public ToolModel(ToolViewService tvs){
		this.tvs = tvs;
//		NetService.getMyTools();
		ClientModel.setToolModel(this);
	}
	
	public ArrayList<Integer> getNum(){
		return num;
	}
	
	/**
	 * �������
	 */
	@Override
	public void buyTool(int toolID) {
		// TODO Auto-generated method stub
		buy = true;
		String[] tools = {"C","D","E","F"};
		NetService.buyTool(tools[toolID-1]);
		
	}

	/**
	 * ��ȡ��������
	 */
	@Override
	public void toolNumber(){
		NetService.getMyTools();		
	}
	
	/**
	 * �������������������ͼ����ʾ��������
	 */
	@Override
	public void saveTools(ArrayList<Integer> num){
//		if(!buy)
//			enough = !num.equals(this.num);
//		buy = true;
		this.num = num;
		getTool = true;
		ReadyModel.setTools(num);
//		if(!enough)
//			tvs.updateToolNumbers(num, enough);
//		else
			tvs.updateToolNumbers(num);
	}

	@Override
	public void showTools() {
		// TODO Auto-generated method stub
		toolNumber();
	}

	/**
	 * ������ͼ����ʾ������߽��
	 */
	@Override
	public void buyToolResult(boolean b) {
		// TODO Auto-generated method stub
		buy = false;
		if(!b)
			tvs.updateToolNumbers(num, enough);
		else{
			toolNumber();
		}
	}
  
}
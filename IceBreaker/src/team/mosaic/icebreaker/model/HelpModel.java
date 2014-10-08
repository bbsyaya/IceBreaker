package team.mosaic.icebreaker.model;

import team.mosaic.icebreaker.file.FileIO;
import team.mosaic.icebreaker.modelservice.HelpModelService;
import team.mosaic.icebreaker.viewservice.HelpViewService;

/**
 * ∞Ô÷˙ƒ£–Õ¿‡
 * @author acer
 *
 */
public class HelpModel implements HelpModelService {
	
	private HelpViewService hvs;
	
	public HelpModel(HelpViewService hvs){
		this.hvs = hvs;
	}
	@Override
	public void fetchHelpTips() {
		FileIO fi = new FileIO();
		// TODO Auto-generated method stub
//		ArrayList<String> content = new ArrayList<String>();
//		ArrayList<String> tmp = fi.read(fi.HELP_FILE);
//		for(String s:tmp){
//			content.add();
//		}
//		hvs.showHelp(null);
	}

}

package team.mosaic.icebreaker.model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import team.mosaic.icebreaker.file.FileIO;
import team.mosaic.icebreaker.modelservice.InfoModelService;
import team.mosaic.icebreaker.netservice.NetService;
import team.mosaic.icebreaker.utility.Prompt;
import team.mosaic.icebreaker.viewservice.InfoViewService;
import team.mosaic.icebreaker.vo.InfoVO;
import team.mosaic.icebreaker.vo.RecordVO;

/**
 * 个人信息模型类
 * @author acer
 *
 */
public class InfoModel implements InfoModelService{
	private InfoViewService ivs;
	private InfoVO info;
	private ArrayList<Boolean> character = null;
	private int buyChar=-1;

	private ArrayList<String> everyGame;
	private ArrayList<String> gameCount;
	private ArrayList<String> avgScores;
	private ArrayList<String> maxScores;
	private ArrayList<String> maxCombos;
	private int totalGame = 0;
	private int totalScore = 0;
	private boolean recorded = false;

	public InfoModel(InfoViewService ivs){
		this.ivs = ivs;
		ClientModel.setInfoModel(this);
		//NetService.queryRecord();
	}

	public InfoModel(){};

	public InfoVO getInfo(){
		return info;
	}
	@Override
	public void fetchInfo() {
		// TODO Auto-generated method stub
		NetService.queryRecord();
		NetService.getInfo();
		//		NetService.getCharacter();
	}

	/**
	 * 更改视图，显示人物是否解锁
	 */
	@Override
	public void ifBought(int i) {
		// TODO Auto-generated method stub
		//have 判断此人物是否已经被解锁
		ivs.setCharacter(i, character.get(i));
	}

	/**
	 *更改视图，显示个人信息
	 */
	@Override
	public void showInfo(InfoVO ivo) {
		// TODO Auto-generated method stub
		info = ivo;
		ivs.showInfo(ivo);
//		readRecord();
		showTotalRecord();
		if(recorded){
			ivs.showAvg(avgScores);		
		}
	}

	/**
	 * 购买人物
	 */
	@Override
	public void buyChar(int i) {
		// TODO Auto-generated method stub
		String[] chars = {"Anna","Kristoff","Sven","Elsa"};
		buyChar = i;
		NetService.buyCharacter(chars[i]);
	}

	/*
	 * 更新人物
	 */
	@Override
	public void updateChar(){
		NetService.getCharacter();	
	}

	/**
	 * 购买人物结果
	 */
	@Override
	public void buyCharacterResult(boolean b) {
		// TODO Auto-generated method stub
		ivs.getChar(buyChar, b);
		character.set(buyChar, b);
	}

	@Override
	public void setChar(ArrayList<Boolean> list) {
		// TODO Auto-generated method stub
		this.character = list;
		
	}

	/**
	 * 读取个人信息记录
	 */
	private void readRecord(){
		gameCount = new ArrayList<String>();
		avgScores = new ArrayList<String>();
		maxScores = new ArrayList<String>();
		maxCombos = new ArrayList<String>();
		everyGame = new ArrayList<String>();
		totalGame = 0;
		totalScore = 0;
		
		ArrayList<String> record = new ArrayList<String>();
		String file = "info/records/"+OnlineStatus.getID()+".data";
		File recordFile = new File(file);
		if(!recordFile.exists()){
			recorded = false;
			Prompt.showWarning(null,"你在本机上还没有详细记录");
		}
		else{
			recorded = true;
			try {
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(recordFile));
				String tmp = null;
				while((tmp=br.readLine())!=null){
					record.add(tmp);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int oneDayGame = 0;
			int oneDayScore = 0;
			int oneDayMaxCombo = 0;
			int oneDayMaxScore = 0;

			String date1 = null;
			int counter = 0;
			for(String tmp:record){
				counter++;
				System.out.println(counter);
				String[] split = tmp.split("\t");
				
				String date = split[0];
				int score = Integer.valueOf(split[1]);
				int maxCombo = Integer.valueOf(split[2]);

				totalGame = totalGame + 1;
				totalScore = totalScore + score;

				everyGame.add(date+"\t"+score);
				
				if(date1 == null){
					date1 = date;
				}

				if(date1.equals(date)){
					oneDayGame = oneDayGame + 1;
					oneDayScore = oneDayScore + score;
					if(oneDayMaxScore<score){
						oneDayMaxScore = score;
					}
					if(oneDayMaxCombo<maxCombo){
						oneDayMaxCombo = maxCombo;
					}
				}
				else{
					//					String[] temp =  {new String(date1),String.valueOf(oneDayGame),String.valueOf(oneDayScore/oneDayGame),
					//							String.valueOf(oneDayMaxScore),String.valueOf(oneDayMaxCombo)};

					gameCount.add(date1+"\t"+oneDayGame);
					avgScores.add(date1+"\t"+oneDayScore/oneDayGame);
					maxScores.add(date1+"\t"+oneDayMaxScore);
					maxCombos.add(date1+"\t"+oneDayMaxCombo);

					//					result.add(temp);

					date1 = date;
					oneDayGame = 1;
					oneDayScore = score;
					oneDayMaxCombo = maxCombo;
					oneDayMaxScore = score;
				}
			}
			
			gameCount.add(date1+"\t"+oneDayGame);
			avgScores.add(date1+"\t"+oneDayScore/oneDayGame);
			maxScores.add(date1+"\t"+oneDayMaxScore);
			maxCombos.add(date1+"\t"+oneDayMaxCombo);


		}
	}

	/**
	 * 保存个人信息记录
	 */
	@Override
	public void saveRecord(RecordVO rvo) {
		// TODO Auto-generated method stub
		//NetServie.updateRecord(rvo);
		String file = "info/records/"+OnlineStatus.getID()+".data";
		File recordFile = new File(file);
		if(!recordFile.exists()){
			try {
				recordFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(recordFile, true));
			bw.write(rvo.getDate()+"\t"+rvo.getScore()+"\t"+rvo.getMaxCombo()+"\r\n");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void showTotalRecord() {
		// TODO Auto-generated method stub
		if(totalGame == 0){
			ivs.showTotalRecord(totalGame, 0);
		}
		else
			ivs.showTotalRecord(totalGame, totalScore/totalGame);
	}

	/**
	 * 更改视图，显示个人信息
	 */
	
	
	@Override
	public void showAvg() {
		// TODO Auto-generated method stub
		if(recorded)
			ivs.showAvg(avgScores);
		else
			Prompt.showWarning(null,"你还没有记录");
	}

	@Override
	public void showBest() {
		// TODO Auto-generated method stub
		if(recorded)
			ivs.showBest(maxScores);
		else
			Prompt.showWarning(null,"你还没有记录");
	}

	@Override
	public void showGameCount() {
		// TODO Auto-generated method stub
		if(recorded)
			ivs.showGameCount(gameCount);
		else
			Prompt.showWarning(null,"你还没有记录");
	}

	@Override
	public void showMaxCombo() {
		// TODO Auto-generated method stub
		if(recorded)
			ivs.showMaxCombo(maxCombos);
		else
			Prompt.showWarning(null,"你还没有记录");
	}
	@Override
	public void showEveryGame() {
		// TODO Auto-generated method stub
		if(recorded)
			ivs.showEveryGame(everyGame);
		else
			Prompt.showWarning(null,"你还没有记录");
	}

	/**
	 * 保存选择的人物记录
	 */
	@Override
	public void writeCharacter(String id, int index) {
		// TODO Auto-generated method stub
		ArrayList<String> accountList=FileIO.read(FileIO.ACCOUNT_FILE);
		int n = 0;
		String newItem = null;
		for(String l:accountList){
			String[] split = l.split(" ");
			if(split[1].equals(id)){
				if(split.length == 3)
					newItem = index+" "+id+" "+split[2];
				else if(split.length == 2)
					newItem = index+" "+id;
				break;
			}
			n ++;
		}
		accountList.remove(n);
		accountList.add(newItem);
		FileIO.write(FileIO.ACCOUNT_FILE, accountList);
	}

	/**
	 * 对个人信息记录处理
	 */
	@Override
	public void dealRecords(ArrayList<String> records) {
		// TODO Auto-generated method stub
		System.out.println("deal records");
		recorded = true;
		gameCount = new ArrayList<String>();
		avgScores = new ArrayList<String>();
		maxScores = new ArrayList<String>();
		maxCombos = new ArrayList<String>();
		everyGame = new ArrayList<String>();
		totalGame = 0;
		totalScore = 0;
		
		if(records.size()==0){
			recorded = false;
			Prompt.showWarning(null,"你还没有记录");
			return;
		}
		int oneDayGame = 0;
		int oneDayScore = 0;
		int oneDayMaxCombo = 0;
		int oneDayMaxScore = 0;

		String date1 = null;
		int counter = 0;
		for(String tmp:records){
			counter++;
			System.out.println(counter);
			String[] split = tmp.split("\t");
			
			String date = split[0];
			int score = Integer.valueOf(split[1]);
			int maxCombo = Integer.valueOf(split[2]);

			totalGame = totalGame + 1;
			totalScore = totalScore + score;

			everyGame.add(date+"\t"+score);
			
			if(date1 == null){
				date1 = date;
			}

			if(date1.equals(date)){
				oneDayGame = oneDayGame + 1;
				oneDayScore = oneDayScore + score;
				if(oneDayMaxScore<score){
					oneDayMaxScore = score;
				}
				if(oneDayMaxCombo<maxCombo){
					oneDayMaxCombo = maxCombo;
				}
			}
			else{
				//					String[] temp =  {new String(date1),String.valueOf(oneDayGame),String.valueOf(oneDayScore/oneDayGame),
				//							String.valueOf(oneDayMaxScore),String.valueOf(oneDayMaxCombo)};

				gameCount.add(date1+"\t"+oneDayGame);
				avgScores.add(date1+"\t"+oneDayScore/oneDayGame);
				maxScores.add(date1+"\t"+oneDayMaxScore);
				maxCombos.add(date1+"\t"+oneDayMaxCombo);

				//					result.add(temp);

				date1 = date;
				oneDayGame = 1;
				oneDayScore = score;
				oneDayMaxCombo = maxCombo;
				oneDayMaxScore = score;
			}
		}
		//			String[] temp =  {String.valueOf(oneDayGame),String.valueOf(oneDayScore/oneDayGame),
		//					String.valueOf(oneDayMaxScore),String.valueOf(oneDayMaxCombo)};
		//			result.add(temp);
		gameCount.add(date1+"\t"+oneDayGame);
		avgScores.add(date1+"\t"+oneDayScore/oneDayGame);
		maxScores.add(date1+"\t"+oneDayMaxScore);
		maxCombos.add(date1+"\t"+oneDayMaxCombo);


	}
	


}

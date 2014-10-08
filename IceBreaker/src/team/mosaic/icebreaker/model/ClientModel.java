package team.mosaic.icebreaker.model;

import java.util.ArrayList;

import team.mosaic.icebreaker.modelservice.BoardModelService;
import team.mosaic.icebreaker.modelservice.ClientService;
import team.mosaic.icebreaker.modelservice.GameModelService;
import team.mosaic.icebreaker.modelservice.InfoModelService;
import team.mosaic.icebreaker.modelservice.MenuModelService;
import team.mosaic.icebreaker.modelservice.ReadyModelService;
import team.mosaic.icebreaker.modelservice.RegisterModelService;
import team.mosaic.icebreaker.modelservice.ToolModelService;
import team.mosaic.icebreaker.modelservice.UserModelService;
import team.mosaic.icebreaker.net.ClientSocket;
import team.mosaic.icebreaker.vo.ColorQueueVO;
import team.mosaic.icebreaker.vo.InfoVO;
import team.mosaic.icebreaker.vo.PositionVO;
import team.mosaic.icebreaker.vo.RecordVO;
import team.mosaic.icebreaker.vo.ReplyVO;
import team.mosaic.icebreaker.vo.ResultVO;
import team.mosaic.icebreaker.vo.ScoreVO;
import team.mosaic.icebreaker.vo.SwapActionVO;
import team.mosaic.icebreaker.vo.ToolVO;

public class ClientModel implements ClientService {
	
	private static InfoModelService ims;
	private static UserModelService ums;
	private static RegisterModelService rgms;
	private static ToolModelService tms;
	private static MenuModelService mms;
	private static ReadyModelService rdms;
	private static GameModelService gms;
	private static BoardModelService bms;
	
	public ClientModel(){
		ClientSocket.getInstance().setClientModel(this);
	}
	
	/******************************************************/
	
	public static void setInfoModel(InfoModelService i){
		ims = i;
	}
	
	public static InfoModelService getInfoModel(){
		return ims;
	}
	
	public static void setUserModel(UserModelService u){
		ums = u;
	}
	
	public static UserModelService getUserModel(){
		return ums;
	}
	
	public static void setRegisterModel(RegisterModelService rg){
		rgms = rg;
	}
	
	public static RegisterModelService getRegisterModel(){
		return rgms;
	}
	
	public static void setToolModel(ToolModelService t){
		tms = t;
	}
	
	public static ToolModelService getToolModel(){
		return tms;
	}
	
	public static void setMenuModel(MenuModelService m){
		mms = m;
	}
	
	public static MenuModelService getMenuModel(){
		return mms;
	}
	
	public static void setReadyModel(ReadyModelService rd){
		rdms = rd;
	}
	
	public static ReadyModelService getReadyModel(){
		return rdms;
	}
	
	public static void setGameModel(GameModelService g){
		gms = g;
	}
	
	public static GameModelService getGameModel(){
		return gms;
	}
	
	public static void setBoardModel(BoardModelService b){
		bms = b;
	}
	
	public static BoardModelService getBoardModel(){
		return bms;
	}
	
	/***********************************************************/
	
	
	@Override
	public void login(boolean isVerified) {
		// TODO Auto-generated method stub
		ums.responseLogin(isVerified);
	}

	@Override
	public void register(boolean b) {
		// TODO Auto-generated method stub
		rgms.showResult(b);
	}

	@Override
	public void myInfo(InfoVO ivo) {
		// TODO Auto-generated method stub
		ims.showInfo(ivo);
	}

	@Override
	public void myTools(ArrayList<ToolVO> tools) {
		// TODO Auto-generated method stub
		ArrayList<Integer> num = new ArrayList<Integer>();
		ToolVO tmp = null;
		for(int i = 0;i<tools.size();i++){
			tmp = tools.get(i);
//			for(int j = 0;j<tmp.getNum();j++){
//				
//			}
			num.add(new Integer(tmp.getNum()));
		}
		tms.saveTools(num);
	}

	@Override
	public void firendRank(ArrayList<ScoreVO> rank) {
		// TODO Auto-generated method stub
		mms.showFriendRank(rank);
	}

	@Override
	public void worldRank(ArrayList<ScoreVO> rank) {
		// TODO Auto-generated method stub
		mms.showWorldRank(rank);
	}

	@Override
	public void onlineFriends(ArrayList<String> friends) {
		// TODO Auto-generated method stub
		rdms.showFriends(friends);
	}

	@Override
	public void coopInvited(String id) {
		// TODO Auto-generated method stub
		gms.invitedCoop(id);
	}

	@Override
	public void pkInvited(String id) {
		// TODO Auto-generated method stub
		gms.invitedPK(id);
	}

	@Override
	public void invitationResult(ReplyVO rvo) {
		// TODO Auto-generated method stub
		gms.inviteResult(rvo);
	}

	@Override
	public void act(SwapActionVO svo) {
		// TODO Auto-generated method stub
		PositionVO p1 = svo.getPosition1();
		PositionVO p2 = svo.getPosition2();
		if(p1==p2)
			bms.useToolGridwithNet(p1);
		else
			try {
				bms.swap(p1, p2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void myCharacters(boolean[] c) {
		// TODO Auto-generated method stub
		ArrayList<Boolean> b = new ArrayList<Boolean>();
		for(int i = 0;i<c.length;i++)
			b.add(c[i]);
		ims.setChar(b);
	}

	@Override
	public void setHost() {
		// TODO Auto-generated method stub
//		gms.setHost();
		bms.setMainPlayer();
	}

	@Override
	public void friendsApply(String id) {
		// TODO Auto-generated method stub
		mms.friendApply(id);
	}

	@Override
	public void sendQueue(ColorQueueVO qvo) {
		// TODO Auto-generated method stub
		bms.setSubPlayer(qvo);
	}

	@Override
	public void buyToolResult(boolean b) {
		// TODO Auto-generated method stub
		tms.buyToolResult(b);
	}

	@Override
	public void buyCharacterResult(boolean b) {
		// TODO Auto-generated method stub
		ims.buyCharacterResult(b);
	}

	@Override
	public void resultOffriendsApply(ReplyVO rvo) {
		// TODO Auto-generated method stub
		mms.resultOfFriendApply(rvo.getPalID(), rvo.getAgreement());
	}

	@Override
	public void showResult(ResultVO rvo) {
		// TODO Auto-generated method stub
		gms.showResult(rvo);
	}

	@Override
	public void randomLock() {
		// TODO Auto-generated method stub
		bms.lockColor();
	}

	@Override
	public void lockC() {
		// TODO Auto-generated method stub
		bms.lockToolC();
	}

	@Override
	public void someoneQuit(String id) {
		// TODO Auto-generated method stub
		gms.someoneQuit(id);
	}

	@Override
	public void findCoopPair(String id) {
		// TODO Auto-generated method stub
		rdms.findCoopPair(id);
	}

	@Override
	public void findCoopFour(String[] id) {
		// TODO Auto-generated method stub
		rdms.findCoopFour(id);
	}

	@Override
	public void findPKFour(String[] id) {
		// TODO Auto-generated method stub
		rdms.findPKFour(id);
	}

	@Override
	public void startTwosome() {
		// TODO Auto-generated method stub
		rdms.startTwosome();
	}

	@Override
	public void startFoursome() {
		// TODO Auto-generated method stub
		rdms.startFoursome();
	}

	@Override
	public void startPk(String id) {
		// TODO Auto-generated method stub
		rdms.startPk(id);
	}

	@Override
	public void sendRecords(ArrayList<RecordVO> records) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		for(RecordVO rvo:records){
			list.add(rvo.getDate()+"\t"+rvo.getScore()+"\t"+rvo.getMaxCombo());
		}
		ims.dealRecords(list);
	}
	
	public void notFoundPlayer(){
		mms.notFoundPlayer();
	}
	
}

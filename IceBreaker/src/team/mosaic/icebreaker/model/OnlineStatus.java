package team.mosaic.icebreaker.model;

public class OnlineStatus {
	private static boolean online;
	private static String id;
	private static int character;
	
	public static void setID(String id){
		OnlineStatus.id = id;
	}
	public static void setOnline(boolean online){
		OnlineStatus.online = online;
	}
	
	public static void setCharacter(int index){
		OnlineStatus.character = index;
	}
	
	public static boolean isOnline(){
		return online;
	}
	
	public static String getID(){
		return id;
	}
	
	public static int getCharacter(){
		return character;
	}
	
}

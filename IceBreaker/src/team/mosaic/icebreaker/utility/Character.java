package team.mosaic.icebreaker.utility;

public enum Character {

	R1("Anna"),R2("Kristoff"),R3("Sven"),R4("Elsa");
	
	public String getName(){
		return this.name;
	}

	public String getNameByIndex(int i){
		switch(i){
		case 0:
			return R1.name;
		case 1:
			return R2.name;
		case 2:
			return R3.name;
		case 3:
			return R4.name;
		default:
			return null;
		}
	}
	
	private Character(String name){
		this.name = name;
	}
	
	private String name;
	
}

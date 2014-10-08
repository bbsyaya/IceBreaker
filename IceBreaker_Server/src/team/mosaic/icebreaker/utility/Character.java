package team.mosaic.icebreaker.utility;

public enum Character {

	R1("Anna"),R2("Kristoff"),R3("Sven"),R4("Elsa");
	
	public String getName(){
		return this.name;
	}
	
	private Character(String name){
		this.name = name;
	}
	
	private String name;
	
}

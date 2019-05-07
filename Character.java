public class Character {

	public String name;
	public int health;
	public int constitution;
	public int dexterity;
	public int intellgience;
	public int job;
	
	public Character(String name, int health, int constitution, int dexterity, int intelligence) {
		this.name = name;
		this.health = health;
		this.constitution = constitution;
		this.dexterity = desterity;
		this.intelligence = intellgence;
		this.job = job;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	 

}
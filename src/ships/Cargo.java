package ships;

public class Cargo extends Ship {
	
	int flourCont;
	int sugarCount;
	int saltCont;
	
	public Cargo() {
		super(1,"CARGOShip");
		flourCont=20;
		sugarCount=12;
		saltCont=5;
	}
	
	public Cargo(int type, String name, int flour, int sugar, int salt) {
		super(type, name);
		flourCont = flour;
		sugarCount = sugar;
		saltCont = salt;
	}
	
	
	
}

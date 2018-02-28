package ships;

public class Cargo extends Ship {
	
	int flourCont;
	int sugarCount;
	int saltCont;
	
	public Cargo(int type, String name, int flour, int sugar, int salt) {
		super(type, name);
		flourCont = flour;
		sugarCount = sugar;
		saltCont = salt;
	}
	
	
	
}

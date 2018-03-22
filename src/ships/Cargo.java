package ships;


public class Cargo extends Ship {

	int flourCount;	//2
	int sugarCount; //1
	int saltCount;		//3

	public Cargo() {
		super(1,"CARGOShip");
		flourCount=20;
		sugarCount=12;
		saltCount=5;
	}

	public Cargo(int type, String name, int flour, int sugar, int salt) {
		super(type, name);
		flourCount = flour;
		sugarCount = sugar;
		saltCount = salt;
	}

	public void run(){
		while(sugarCount>0){
			Platform.getPlatform().depositProduct(1);
			sugarCount--;
		}
		while(flourCount>0){
			Platform.getPlatform().depositProduct(2);
			flourCount--;
		}
		while(saltCount>0){
			Platform.getPlatform().depositProduct(3);
			saltCount--;
		}
	}
}

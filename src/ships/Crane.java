package ships;

public class Crane implements Runnable {

	int type; // 1 = sugar // 2 = flour // 3 = salt //
	Cargo c;
	
	public Crane(int type, Cargo c) {
		this.type = type;
		this.c = c;
	}

	@Override
	public void run() {
		System.out.println("running crane " + type);
		Platform.getPlatform().getProduct(this.type,c);
	}

}

package ships;

public class Crane implements Runnable {

	int type; // 1 = sugar // 2 = flour // 3 = salt //

	public Crane(int type) {
		this.type = type;
	}

	@Override
	public void run() {
		System.out.println("runeando " + type);
		Platform.getPlatform().getProduct(this.type);
	}

}

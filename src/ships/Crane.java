package ships;

public class Crane implements Runnable {

	int type;	// 1 = sugar // 2 = flour // 3 = salt //

	public Crane(int type) {
		this.type = type;
	}



	@Override
	public void run() {
		Platform p = Platform.getPlatform();
		p.getProduct(this.type);
	}

}

package ships;

public class Main {
	public static void main(String args[]) {

		ChargeZone z = ChargeZone.getChargeZone();
		Platform p = Platform.getPlatform();

		// Thread sugar = new Thread(new Crane(1));
		// Thread flour = new Thread(new Crane(2));
		// Thread salt = new Thread(new Crane(3));
		// Thread cargo = new Thread(new Cargo());
		// sugar.start();
		// flour.start();
		// salt.start();
		// cargo.start();
		//
		// for (int i = 11; i < 21; i++) {
		// 	new Thread(new Ship(0, "SExit " + i)).start();
		// }
		// for (int i = 1; i < 11; i++) {
		// 	new Thread(new Ship(1, "SEnter " + i)).start();
		// }
		for (int i = 0; i < 5; i++) {
			new Thread(new OilShip(1, "OilShip " + i, i)).start();
		}
	}
}

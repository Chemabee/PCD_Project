package ships;

public class Main {
	public static void main(String args[]) {

		Control ctrl = Control.getControl();
		ChargeZone z = ChargeZone.getChargeZone();
		Platform p = Platform.getPlatform();
		Filler f = Filler.getFiller();

//		Cargo c = new Cargo();
//		Thread cargo = new Thread(c);
//		Thread sugar = new Thread(new Crane(1,c));
//		Thread flour = new Thread(new Crane(2,c));
//		Thread salt = new Thread(new Crane(3,c));
//		sugar.start();
//		flour.start();
//		salt.start();
//		cargo.start();
//
//		for (int i = 11; i < 21; i++) {
//			new Thread(new Ship(0, "SExit " + i)).start();
//		}
//		for (int i = 1; i < 11; i++) {
//			new Thread(new Ship(1, "SEnter " + i)).start();
//		}
		for (int i = 0; i < 5; i++) {
			new Thread(new OilShip(1, "OilShip " + i, i)).start();
		}
	}
}

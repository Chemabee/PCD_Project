package ships;

public class Main {
	public static void main(String args[]) {

		for (int i = 1; i < 11; i++) {
			new Thread(new Ship(0, "SExit " + i)).start();
		}
		for (int i = 1; i < 11; i++) {
			new Thread(new Ship(1, "SEnter " + i)).start();
		}

		ChargeZone z = ChargeZone.getChargeZone();
		System.out.println("chargeando");
		for (int i = 0; i < 5; i++) {
			new Thread(new OilShip(1, "OilShip " + i, i)).start();
		}

		System.out.println("empezando esto");

		Platform p = Platform.getPlatform();

		Thread cargo = new Thread(new Cargo());
		Thread sugar = new Thread(new Crane(1));
		Thread flour = new Thread(new Crane(2));
		Thread salt = new Thread(new Crane(3));
		System.out.println("runeando");
		cargo.start();
		System.out.println("runeando cranes");
		sugar.start();
		flour.start();
		salt.start();
	}
}

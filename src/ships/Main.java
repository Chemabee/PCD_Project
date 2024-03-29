package ships;

public class Main {
	
	public static int port;
	
	@SuppressWarnings("unused") //To avoid getting warnings by the singleton
	public static void main(String args[]) {

		/*	
		 * 		CAUTION!!!!!!!!!!!!!!!!!!
		 * 		1) LAUNCH RMI SERVER
		 * 		2) LAUNCH Counter.java AND PortManager.java
		 * 			as RMI Server BEFORE RUNNING THAT MAIN!!
		 * 		3) LAUNCH Main.java with args[0] == RMI Server's port
		 */
		
		if(args.length>0)
			port = Integer.parseInt(args[0]);
		else port = 1099;
		
		// Create instances of the Classes with Singleton Pattern
		Control ctrl = Control.getControl();
		ChargeZone z = ChargeZone.getChargeZone();
		Platform p = Platform.getPlatform();
		Filler f = Filler.getFiller();

		
		// Cargo Part
		Cargo c = new Cargo();
		Thread cargo = new Thread(c);
		Thread sugar = new Thread(new Crane(1, c));
		Thread flour = new Thread(new Crane(2, c));
		Thread salt = new Thread(new Crane(3, c));
		sugar.start();
		flour.start();
		salt.start();
		cargo.start();

		
		// Enter/Exit Part
		for (int i = 11; i < 21; i++) {
			new Thread(new Ship(0, "SExit " + i)).start();
		}
		for (int i = 1; i < 11; i++) {
			new Thread(new Ship(1, "SEnter " + i)).start();
		}

		
		// OilShip Part
		for (int i = 0; i < 5; i++) {
			new Thread(new OilShip(1, "OilShip " + i, i)).start();
		}
	}
}

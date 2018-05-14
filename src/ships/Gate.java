package ships;

public class Gate {

	private static Gate instance;

	private Gate() {

	}

	public Gate getGate() {
		if (instance == null)
			instance = new Gate();
		return instance;
	}

	public static void enter(Ship s) {
		for (int i = 0; i < 3; i++)
			System.out.println("#The ship " + s.name + " is entering.");
		s.type = 0;
	}

	public static void exit(Ship s) {
		for (int i = 0; i < 3; i++)
			System.out.println("@The ship " + s.name + " is exiting.");
		s.type = 1;
	}

}

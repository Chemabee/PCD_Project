package ships;

public class Gate {

	private static Gate instance;

	private Gate() {
	}

	/**
	 * Singleton pattern of the Gate
	 * @return instance of the Gate
	 */
	public Gate getGate() {
		if (instance == null)
			instance = new Gate();
		return instance;
	}

	/**
	 * Print that a Ship is entering, and notify to the ship that its next step with the door is try to exit.
	 * @param s Ship that is entering
	 */
	public static void enter(Ship s) {
		for (int i = 0; i < 3; i++)
			System.out.println("#The ship " + s.name + " is entering.");
		s.type = 0;
	}

	/**
	 * Print that a Ship is exiting, and notify to the ship that its next step with the door is try to enter.
	 * @param s Ship that is exiting
	 */
	public static void exit(Ship s) {
		for (int i = 0; i < 3; i++)
			System.out.println("@The ship " + s.name + " is exiting.");
		s.type = 1;
	}

}

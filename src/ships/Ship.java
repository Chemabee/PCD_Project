package ships;

public class Ship implements Runnable {
	protected int type; // 0=exit; 1=enter
	public final String name;

	public Ship(int type, String name) {
		this.type = type;
		this.name = name;
	}

	/**
	 * Try to enter or exit through the Gate depending on the type of the Ship
	 */
	public void action() {
		switch (this.type) {
		case (0):
			Control.getControl().exitPermission(this);
			Gate.exit(this);
			Control.getControl().exitNotification(this);
			break;
		case (1):
			Control.getControl().entryPermission(this);
			Gate.enter(this);
			Control.getControl().entryNotification(this);
			break;
		default:
			System.out.println("Ship not valid (only type 1 or 2)");
		}
	}

	@Override
	public void run() {
		this.action();
	}
}

package ships;

public class Ship implements Runnable {
	protected int type;	//0=exit; 1=enter
	protected final String name;
	
	
	
	public Ship(int type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public void action() {
		switch(this.type) {
		case(0):
			Gate.exit(this);
			break;
		case(1):
			Gate.enter(this);
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

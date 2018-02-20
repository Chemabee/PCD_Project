package ships;

public class Gate {

	//private int id;
	
	private static Gate instance;
	
	private void Gate() {
	}
//	private void Gate(int _id) {
//		id = _id;
//	}
//	
	public Gate getGate() {
		if(instance == null)
			instance = new Gate();
		return instance;
	}
	
	public static synchronized void enter(Ship s) {
		for(int i=0;i<3;i++)
			System.out.println("The ship " + s.name + " is entering.");
		s.type = 0;
	}
	
	public static synchronized void exit(Ship s) {
		for(int i=0;i<3;i++)
			System.out.println("The ship " + s.name + " is exiting.");
		s.type = 1;
	}
	
}

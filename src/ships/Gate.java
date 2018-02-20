package ships;

public class Gate {

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

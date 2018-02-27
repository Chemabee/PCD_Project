package ships;

public class Control {
	
	private int entering;
	private int exiting;
	
	private int waitExit;
	
	private static Control instance;
	
	private Control() {
		entering = 0;
		exiting = 0;
	}
	
	public static Control getControl() {
		if(instance == null)
			instance = new Control();
		return instance;
	}

	//Entry protocol
	public synchronized void entryPermission(Ship s) {
		while (exiting > 0 || waitExit > 0)
			try {
				System.out.println(s.name+" waiting for entering...");

				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		System.out.println(s.name+ " going to enter");
		entering++;
	}
	
	public synchronized void entryNotification(Ship s) {
		entering--;
		if(entering==0)
			notifyAll();
	}
	
	//Exit protocol
	public synchronized void exitPermission(Ship s) {
		while(entering>0)
			try {
				System.out.println(s.name+" waiting for exiting...");
				waitExit++;
				wait();
				waitExit--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		System.out.println(s.name + " going to exit");
		exiting++;
		
	}
	
	public synchronized void exitNotification(Ship s) {
		exiting--;
		if(exiting==0)
			notifyAll();
	}
}

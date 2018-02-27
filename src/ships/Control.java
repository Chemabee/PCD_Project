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
		while (exiting > 0 && waitExit > 0)
			try {
				System.out.println(s.name+" waiting for entering...");

				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
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
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		exiting++;
	}
	
	public synchronized void exitNotification(Ship s) {
		exiting--;
		waitExit--;
		if(exiting==0)
			notifyAll();
	}
}

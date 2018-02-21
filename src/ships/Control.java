package ships;

public class Control {
	
	private int entering;
	private int exiting;
	
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
		while (exiting > 0)
			try {
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
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		exiting++;
	}
	
	public synchronized void exitNotification(Ship s) {
		exiting--;
		if(exiting==0)
			notifyAll();
	}
}

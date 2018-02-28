package ships;

import java.util.LinkedList;

public class Control {
	
	private int entering;
	private int exiting;
	
	private int waitExit;
	
	private static Control instance;
	
	private LinkedList<Ship> EntryQueue = new LinkedList<Ship>();
	private LinkedList<Ship> ExitQueue = new LinkedList<Ship>();
	
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
		EntryQueue.add(s);
		while (exiting > 0 || waitExit > 0 || EntryQueue.peekFirst() != s)
			try {
				System.out.println("="+s.name+" waiting for entering...");	
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		EntryQueue.removeFirst();
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
		ExitQueue.add(s);
		while (entering > 0 || ExitQueue.peekFirst() != s)
			try {
				System.out.println("="+s.name+" waiting for exiting...");
				waitExit++;
				wait();
				waitExit--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		ExitQueue.removeFirst();
		System.out.println(s.name + " going to exit");
		exiting++;
		
	}
	
	public synchronized void exitNotification(Ship s) {
		exiting--;
		if(exiting==0)
			notifyAll();
	}
}

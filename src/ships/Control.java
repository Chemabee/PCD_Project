package ships;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;

import dist.ICounter;

public class Control {

	private int entering;
	private int exiting;
	private String myIp;

	private int waitExit;

	private static Control instance;

	private LinkedList<Ship> EntryQueue = new LinkedList<Ship>();
	private LinkedList<Ship> ExitQueue = new LinkedList<Ship>();
	
	ICounter stub;

	private Control() {
		entering = 0;
		exiting = 0;
		Registry registry;
		System.err.println("CONNECTING TO COUNTER...");
		try {
			registry = LocateRegistry.getRegistry("localhost");
			stub = (ICounter) registry.lookup("CounterServer");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static Control getControl() {
		if (instance == null)
			instance = new Control();
		return instance;
	}

	// Entry protocol
	public synchronized void entryPermission(Ship s) {
		EntryQueue.add(s);
		while (exiting > 0 || waitExit > 0 || EntryQueue.peekFirst() != s)
			try {
				System.out.println("=" + s.name + " waiting for entering...");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		EntryQueue.removeFirst();
		System.out.println(s.name + " going to enter");
		entering++;
		
		stub.entryPermission(s);
	}

	public synchronized void entryNotification(Ship s) {
		entering--;
		
		stub.entryNotification(s);
		
		if (entering == 0)
			notifyAll();
	}

	// Exit protocol
	public synchronized void exitPermission(Ship s) {
		ExitQueue.add(s);
		while (entering > 0 || ExitQueue.peekFirst() != s)
			try {
				System.out.println("=" + s.name + " waiting for exiting...");
				waitExit++;
				wait();
				waitExit--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		ExitQueue.removeFirst();
		System.out.println(s.name + " going to exit");
		exiting++;
		
		stub.exitPermission(s);
	}

	public synchronized void exitNotification(Ship s) {
		exiting--;
		
		stub.exitNotification(s);
		
		if (exiting == 0)
			notifyAll();
	}
}

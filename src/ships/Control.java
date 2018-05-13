package ships;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;

import dist.ICounter;

public class Control {

	//CONCURRENT
	private int entering;
	private int exiting;

	private int waitExit;

	private static Control instance;

	private LinkedList<Ship> EntryQueue = new LinkedList<Ship>();
	private LinkedList<Ship> ExitQueue = new LinkedList<Ship>();
	
	
	//DISTRIBUTED
//	private String myIp;
	ICounter stub;

	
	private Control() {
		entering = 0;
		exiting = 0;
		Registry registry;
		System.err.println("CONNECTING TO COUNTER...");
		try {
			registry = LocateRegistry.getRegistry("localhost", Main.port);
			stub = (ICounter) registry.lookup("CounterServer");
			System.err.println("CONNECTED!");
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
		
		try {
			stub.entryPermission();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void entryNotification(Ship s) {
		entering--;
		
		try {
			stub.entryNotification();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		try {
			stub.exitPermission();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void exitNotification(Ship s) {
		exiting--;
		
		try {
			stub.exitNotification();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (exiting == 0)
			notifyAll();
	}
}

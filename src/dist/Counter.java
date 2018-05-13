package dist;


import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

import pcd.util.Ventana;
import ships.Ship;

public class Counter implements ICounter {

	Ventana v = new Ventana("Counter of ships");
	
	private int entering = 0;
	private int exiting = 0;
	private static Counter instance=null;
	
	
	public static Counter getCounter(){
		if(instance == null)
			instance = new Counter();
		return instance;
	}
	
	public int getEntering(){
		return entering;
	}
	
	public int getExiting(){
		return exiting;
	}
	
	
	@Override
	public synchronized void entryPermission() {
		// TODO complete entry permission as desired :)
		entering++;
		v.addText("+Now Entering "+entering+" ships");
	}

	@Override
	public synchronized void exitPermission() {
		// TODO 
		exiting++;
		v.addText("+Now Exiting "+exiting+" ships");
	}

	@Override
	public synchronized void entryNotification() {
		// TODO
		entering--;
		v.addText("-Now Entering "+entering+" ships");
	}

	@Override
	public synchronized void exitNotification() {
		// TODO
		exiting--;
		v.addText("-Now Exiting "+exiting+" ships");
	}
	
	
	public static void main (String[] args){
		String identificador;
		
		identificador = "CounterServer";
		try {
			ICounter obj = (ICounter) getCounter();
			ICounter stub = (ICounter) UnicastRemoteObject.exportObject(obj, 0);
			Naming.rebind(identificador, stub);
			System.err.println("Counter Server ready");
			
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}

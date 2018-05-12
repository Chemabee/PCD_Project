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
	
	private Counter(){
		//TODO
	}
	
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
	public synchronized void entryPermission(Ship s) {
		// TODO complete entry permission as desired :)
		entering++;
		v.addText("+Now Entering "+entering+" ships");
	}

	@Override
	public synchronized void exitPermission(Ship s) {
		// TODO 
		exiting++;
		v.addText("+Now Exiting "+exiting+" ships");
	}

	@Override
	public synchronized void entryNotification(Ship s) {
		// TODO
		entering--;
		v.addText("-Now Entering "+entering+" ships");
	}

	@Override
	public synchronized void exitNotification(Ship s) {
		// TODO
		exiting--;
		v.addText("-Now Exiting "+exiting+" ships");
	}
	public static void main (String[] args){
		String identificador;
		
		identificador = "CounterServer";
		ICounter obj = (ICounter) getCounter();
		try {
			ICounter stub = (ICounter) UnicastRemoteObject.exportObject(obj, 0);
			Naming.rebind(identificador, stub);
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}	
	}
}

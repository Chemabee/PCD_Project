package dist;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import pcd.util.Ventana;

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
		entering++;
		v.addText("+Now Entering "+entering+" ships");
	}

	@Override
	public synchronized void exitPermission() {
		exiting++;
		v.addText("+Now Exiting "+exiting+" ships");
	}

	@Override
	public synchronized void entryNotification() {
		entering--;
		v.addText("-Now Entering "+entering+" ships");
	}

	@Override
	public synchronized void exitNotification() {
		exiting--;
		v.addText("-Now Exiting "+exiting+" ships");
	}
	
	
	public static void main (String[] args){
		String identificador;
		
		identificador = "CounterServer";
		try {
			if(args.length>0){ //Eric tiene un problemita y su ordenador no quiere trabajar en el puerto 1099
				ICounter obj = (ICounter) getCounter();
				ICounter stub = (ICounter) UnicastRemoteObject.exportObject(obj, 0);
				Registry r = LocateRegistry.getRegistry(1098);
				r.bind(identificador, stub);
				System.err.println("Counter Server ready");
			}
			else{
				ICounter obj = (ICounter) getCounter();
				ICounter stub = (ICounter) UnicastRemoteObject.exportObject(obj, 0);
				Naming.rebind(identificador, stub);
				System.err.println("Counter Server ready");
			}
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}

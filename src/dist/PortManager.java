package dist;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import pcd.util.Ventana;

public class PortManager implements IPortManager {
	
	Ventana v = new Ventana("Port Manager Server");
	
	private static PortManager instance=null;
	
	int salt = 0;
	int sugar = 0;
	int flour = 0;
	
	/**
	 * Singleton pattern of the PortManager
	 * @return instance of the PortManager
	 */
	public static PortManager getPortManager(){
		if(instance == null)
			instance = new PortManager();
		return instance;
	}
	
	/**
	 * Increments the number of salt containers downloaded during a day
	 */
	@Override
	public synchronized void incSalt()  throws RemoteException{
		salt++;
		v.addText("Salt now: "+salt);
	}
	
	/**
	 * Increments the number of sugar containers downloaded during a day
	 */
	@Override
	public synchronized void incSugar()  throws RemoteException{
		sugar++;
		v.addText("Sugar now: "+sugar);

	}

	/**
	 * Increments the number of flour containers downloaded during a day
	 */
	@Override
	public synchronized void incFlour()  throws RemoteException{
		flour++;
		v.addText("Flour now: "+flour);
	}
	
	/**
	 * Main program of the PortManager Server
	 * @param args args[0] must contain the port of the RMI Registry.
	 * If it is empty, it will use the default port (1099).
	 */
	public static void main (String[] args){
		String identificador = "PortManager";
		
		try {
			if(args.length>0){ //Eric tiene un problemita y su ordenador no quiere trabajar en el puerto 1099
				IPortManager obj = (IPortManager) getPortManager();
				IPortManager stub = (IPortManager) UnicastRemoteObject.exportObject(obj,0);
				Registry r = LocateRegistry.getRegistry(1098);
				r.bind(identificador, stub);
				System.err.println("PortManager Server ready");
			}
			else{
				IPortManager obj = (IPortManager) getPortManager();
				IPortManager stub = (IPortManager) UnicastRemoteObject.exportObject(obj,0);
				Naming.rebind(identificador, stub);
				System.err.println("PortManager Server ready");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

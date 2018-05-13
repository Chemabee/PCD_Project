package dist;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import pcd.util.Ventana;

public class PortManager implements IPortManager {
	
	Ventana v = new Ventana("PortMAnager Server");
	
	private static PortManager instance=null;
	
	int salt = 0;
	int sugar = 0;
	int flour = 0;
	
	
	public static PortManager getPortManager(){
		if(instance == null)
			instance = new PortManager();
		return instance;
	}
	
//No se si es necesario el synchronized
	@Override
	public synchronized void incSalt()  throws RemoteException{
		salt++;
		v.addText("Salt now: "+salt);
	}

	@Override
	public synchronized void incSugar()  throws RemoteException{
		sugar++;
		v.addText("Sugar now: "+sugar);

	}

	@Override
	public synchronized void incFlour()  throws RemoteException{
		flour++;
		v.addText("Flour now: "+flour);
	}
	public static void main (String[] args){
		String identificador = "PortManager";
		
		IPortManager obj = (IPortManager) getPortManager();
		IPortManager stub;
		try {
			stub = (IPortManager) UnicastRemoteObject.exportObject(obj,0);
			Naming.rebind(identificador, stub);
			System.err.println("PortManager Server ready");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
		
	}

}

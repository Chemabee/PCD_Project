package dist;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ships.Ship;

public interface ICounter extends Remote {
	
	void entryPermission(Ship s) throws RemoteException;	//A ship calls this method when he wants to enter, just to entering++;
	void exitPermission(Ship s) throws RemoteException; 	//The same, exiting++
	
	void entryNotification(Ship s) throws RemoteException; //entering--
	void exitNotification(Ship s) throws RemoteException; 	//exiting --
}

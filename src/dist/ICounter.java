package dist;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICounter extends Remote {
	
	void entryPermission() throws RemoteException;	//A ship calls this method when he wants to enter, just to entering++;
	void exitPermission() throws RemoteException; 	//The same, exiting++
	
	void entryNotification() throws RemoteException; //entering--
	void exitNotification() throws RemoteException; 	//exiting --
}

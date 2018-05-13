package dist;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPortManager extends Remote {
	void incSalt() throws RemoteException;
	void incSugar() throws RemoteException;
	void incFlour() throws RemoteException;
}

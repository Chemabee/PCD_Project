package dist;

import java.rmi.Remote;

import ships.Ship;

public interface ICounter extends Remote {
	
	void entryPermission(Ship s);	//A ship calls this method when he wants to enter, just to entering++;
	void exitPermission(Ship s); 	//The same, exiting++
	
	void entryNotification(Ship s); //entering--
	void exitNotification(Ship s); 	//exiting --
}

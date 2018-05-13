package ships;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.locks.*;
import javax.management.monitor.Monitor;

import dist.IPortManager;

public class Platform extends Monitor {

	public int type; // 0 = empty // 1 = sugar // 2 = flour // 3 = salt //
	private static Platform instance = null;
	Lock l = new ReentrantLock();

	Condition salt = l.newCondition();
	Condition sugar = l.newCondition();
	Condition flour = l.newCondition();
	Condition cargo = l.newCondition();
	
	IPortManager stub;

	private Platform() {
		type = 0;
		
		Registry registry;
		System.err.println("CONNECTING TO PORTMANAGER...");

			try {
				registry = LocateRegistry.getRegistry("localhost", Main.port);
				stub = (IPortManager) registry.lookup("PortManager");
				System.err.println("CONNECTED!");
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}

	}

	public static Platform getPlatform() {
		if (instance == null)
			instance = new Platform();
		return instance;
	}

	void getProduct(int craneType, Cargo c) throws RemoteException {
		int numContainers = 0;
		numContainers = c.getContNumber(craneType);
		while (numContainers>0) {
			l.lock();
			try {
				switch (craneType) {
				case 1:
					while (type != craneType) {
						sugar.await();
					}
						type = 0; // Remove the container from the platform
						
						stub.incSugar();
						
						System.out.println("*Crane 1 removed a sugar container from the platform.");
						cargo.signal();
					break;
				case 2:
					while (type != craneType) {
						flour.await();
					}
						type = 0; // Remove the container from the platform
						
						stub.incFlour();
						
						System.out.println("#Crane 2 removed a flour container from the platform.");
						cargo.signal();
					break;
				case 3:
					while (type != craneType) {
						salt.await();
					}
						type = 0; // Remove the container from the platform
						
						stub.incSalt();
						
						System.out.println("$Crane 3 removed a salt container from the platform.");
						cargo.signal();
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				l.unlock();
			}
			numContainers--;
		}
	}

	void depositProduct(int containerType) {
		l.lock();
		try {
			while (type != 0)// si hay algo en la plataforma->espera hasta que haya hueco
				cargo.await();
			// si hay hueco en la plataforma deja su contenedor ahi
			type = containerType; // Deposit the item in the Platform
			System.out.println("Cargo ship has deposited a container " + type + " in the platform.");
			switch (type) {
			case 1:
				sugar.signal();
				break;
			case 2:
				flour.signal();
				break;
			case 3:
				salt.signal();
				break;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			l.unlock();
		}
	}


	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}

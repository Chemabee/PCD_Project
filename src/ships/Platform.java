package ships;

import java.util.concurrent.locks.*;
import javax.management.monitor.Monitor;

public class Platform extends Monitor {

	public int type;	// 0 = empty // 1 = sugar // 2 = flour // 3 = salt //
	private static Platform instance=null;
	Lock l = new ReentrantLock();

	Condition salt = l.newCondition();
	Condition sugar = l.newCondition();
	Condition flour = l.newCondition();
	Condition cargo = l.newCondition();

	public Platform(){
		type = 0;
	}

	public static Platform getPlatform() {
		if(instance == null)
			instance = new Platform();
		return instance;
	}

	void getProduct(int craneType) {
		while(true) {
			l.lock();
			try {
				switch (craneType) {
				case 1:
					while (type != craneType)
						sugar.await();
					type = 0; // Remove the container from the platform
					System.out.println("*Crane 1 removed a sugar container from the platform.");
					cargo.signal();
					break;
				case 2:
					while (type != craneType)
						flour.await();
					type = 0; // Remove the container from the platform
					System.out.println("#Crane 2 removed a flour container from the platform.");
					cargo.signal();
					break;
				case 3:
					while (type != craneType)
						salt.await();
					type = 0; // Remove the container from the platform
					System.out.println("$Crane 3 removed a salt container from the platform.");
					cargo.signal();
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				l.unlock();
			}
		}
	}

	void depositProduct(int containerType){
		l.lock();
		try {
			while(type != 0){
				cargo.await();
				}
				type = containerType; //Deposit the item in the Platform
				System.out.println("Cargo ship has deposited a container "+type+" in the platform.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally{
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

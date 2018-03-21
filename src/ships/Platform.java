package ships;

import javax.management.monitor.Monitor;

public class Platform extends Monitor {
	
	int type;	// 0 = empty // 1 = sugar // 2 = flour // 3 = salt //

	void getProduct(int craneType) {
		while(true) {
			while(type != craneType)
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			type = 0; //Remove the container from the platform
			System.out.println("Crane "+craneType+" removed a container from the platform.");
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

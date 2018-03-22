package ships;

import javax.management.monitor.Monitor;

public class Platform extends Monitor {

	public int type;	// 0 = empty // 1 = sugar // 2 = flour // 3 = salt //
	private static Platform instance=null;
	
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

	void depositProduct(int craneType){
		while(type != 0){
			try{
				wait();
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		type = craneType; //Deposit the item in the Platform
		System.out.println("Cargo ship has deposited a container "+type+" in the platform.");
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

package dist;

import ships.Ship;

public class Counter implements ICounter {

	private int entering = 0;
	private int exiting = 0;
	private static Counter instance=null;
	
	private Counter(){
		
	}
	
	public static Counter getInstance(){
		if(instance == null)
			instance = new Counter();
		return instance;
	}
	
	public int getEntering(){
		return entering;
	}
	
	public int getExiting(){
		return exiting;
	}
	
	
	@Override
	public synchronized void entryPermission(Ship s) {
		// TODO complete entry permission as desired :)
		entering++;
	}

	@Override
	public synchronized void exitPermission(Ship s) {
		// TODO 
		exiting++;
	}

	@Override
	public synchronized void entryNotification(Ship s) {
		// TODO
		entering--;
	}

	@Override
	public synchronized void exitNotification(Ship s) {
		// TODO
		exiting--;
	}
}

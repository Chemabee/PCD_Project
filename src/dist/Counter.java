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
	
	@Override
	public void entryPermission(Ship s) {
		// TODO entering++

	}

	@Override
	public void exitPermission(Ship s) {
		// TODO exiting++

	}

	@Override
	public void entryNotification(Ship s) {
		// TODO entering--

	}

	@Override
	public void exitNotification(Ship s) {
		// TODO exiting--

	}

}

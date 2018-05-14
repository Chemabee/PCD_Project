package ships;

public class Task implements Runnable {
	
	private int type;//0 -> Oil; 1 ->Water
	
	private OilShip s;//ship doing the task
	
	/**
	 * Parameterized constructor for the Task of the OilShip
	 * @param _type Type of the task (0=Oil, 1=Water)
	 * @param _s The Oil Ship whose this Task belongs to
	 */
	public Task(int _type,OilShip _s) {
		type = _type;
		s = _s;
	}
	@Override
	public void run() {
		if (type == 0) {//Oil Task
			while (s.getOilCont() < 3000) {
				System.out.println("ship " + s.getId() + " GOING to GET OIL");
//				this.getOil();
				try {
					ChargeZone.getChargeZone().getOil(s);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(s.getId() + " oil: " + s.getOilCont());
			}
		}
		else {//Water Task
			while(s.getWaterCont() < 5000) {
				try {
					ChargeZone.getChargeZone().getWater(s);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(s.getId() + " water: " + s.getWaterCont());
			}
		}
	}

}

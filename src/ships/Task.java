package ships;

public class Task implements Runnable {
	
	private int type;//0 -> Oil; 1 ->Water

	private ChargeZone c;//Charge Zone
	
	private OilShip s;//ship doing the task
	

	public Task(int _type,ChargeZone _c,OilShip _s) {
		type = _type;
		c = _c;
		s = _s;
	}
	@Override
	public void run() {
		if (type == 0) {//Oil Task
			while (s.getOilCont() < 3000) {
				System.out.println("ship " + s.getId() + " GOING to GET OIL");
//				this.getOil();
				try {
					c.getOil(s);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(s.getId() + " oil: " + s.getOilCont());
			}
		}
		else {//Water Task
			while(s.getWaterCont() < 5000) {
				try {
					c.getWater(s);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(s.getId() + " water: " + s.getWaterCont());
			}
		}
	}

}

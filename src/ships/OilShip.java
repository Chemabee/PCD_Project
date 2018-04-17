package ships;

public class OilShip extends Ship {

	public int oilCont = 0;
	public int waterCont = 0;
	public int id;


	public OilShip(int type, String name, int id) {
		super(type, name);
		this.id = id;
	}

	@Override
	public void run() {
		this.action();
		this.charge();
		this.action();

	}
	public void charge() {
		while(oilCont < 3000){
			this.getOil();
		}

		while(waterCont < 5000){
			getWater();
		}
	}

	public void getOil() {
		ChargeZone z = ChargeZone.getChargeZone();
		z.getOil(this);
	}

	public void getWater(){
		ChargeZone z = ChargeZone.getChargeZone();
		z.getWater(this);
	}

}

package ships;

public class Cargo extends Ship {

	int flourCount; // 2
	int sugarCount; // 1
	int saltCount; // 3
	
	/**
 	*Default constructor
 	*/
	public Cargo() {
		super(1, "CARGOShip");
		flourCount = 20;
		sugarCount = 12;
		saltCount = 5;
	}
	
	/**
	 * Parametriced Constructor
	 * @param type :Direction of the cargo (entering/exiting)
	 * @param name :name of the ship
	 * @param flour :quantity of flour
	 * @param sugar :quantity of sugar
	 * @param salt :quantity of salt
	 */
	public Cargo(int type, String name, int flour, int sugar, int salt) {
		super(type, name);
		flourCount = flour;
		sugarCount = sugar;
		saltCount = salt;
	}
	
	/**
	 * Get the number of containers of the Cargo ship of an specified type
	 * @param type type of container
	 * @return	integer with the number of containers of the specified type remaining in the Cargo ship
	 */
	public int getContNumber(int type){
		switch(type){
			case 1:
				return sugarCount;
			case 2:
				return flourCount;
			case 3:
				return saltCount;
		}
		return 0;
	}

	/**
	 * Run Method where it puts sugar, flour and salt containers in the platform until the Cargo ship is empty
	 */
	public void run() {
		while (sugarCount > 0) {
			Platform.getPlatform().depositProduct(1);
			sugarCount--;
		}
		while (flourCount > 0) {
			Platform.getPlatform().depositProduct(2);
			flourCount--;
		}
		while (saltCount > 0) {
			Platform.getPlatform().depositProduct(3);
			saltCount--;
		}
	}
}

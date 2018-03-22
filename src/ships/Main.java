package ships;

public class Main {
	public static void main(String args[]) {

//		for(int i=1;i<11;i++){
//			new Thread(new Ship(0,"SExit "+i)).start();
//		}
//		for(int i=1;i<11;i++){
//			new Thread(new Ship(1,"SEnter "+i)).start();
//		}
		System.out.println("runeando cargo");
		Thread cargo = new Thread(new Cargo());
		cargo.run();
		
System.out.println("empezando esto");
		Thread sugar = new Thread(new Crane(1));
		Thread flour = new Thread(new Crane(2));
		Thread salt = new Thread(new Crane(3));
System.out.println("runeando cranes");
		sugar.run();
		flour.run();
		salt.run();


//		Thread t1 = new Thread(new Ship(0,  "SExit 1"));
//		Thread t2 = new Thread(new Ship(0,  "SExit 2"));
//		Thread t3 = new Thread(new Ship(0,  "SExit 3"));
//		Thread t4 = new Thread(new Ship(0,  "SExit 4"));
//		Thread t5 = new Thread(new Ship(0,  "SExit 5"));
//		Thread t6 = new Thread(new Ship(0,  "SExit 6"));
//		Thread t7 = new Thread(new Ship(0,  "SExit 7"));
//		Thread t8 = new Thread(new Ship(0,  "SExit 8"));
//		Thread t9 = new Thread(new Ship(0,  "SExit 9"));
//		Thread t10 = new Thread(new Ship(0,  "SExit 10"));
//
//		Thread t11 = new Thread(new Ship(1,  "SEnter 11"));
//		Thread t12 = new Thread(new Ship(1,  "SEnter 12"));
//		Thread t13 = new Thread(new Ship(1,  "SEnter 13"));
//		Thread t14 = new Thread(new Ship(1,  "SEnter 14"));
//		Thread t15 = new Thread(new Ship(1,  "SEnter 15"));
//		Thread t16 = new Thread(new Ship(1,  "SEnter 16"));
//		Thread t17 = new Thread(new Ship(1,  "SEnter 17"));
//		Thread t18 = new Thread(new Ship(1,  "SEnter 18"));
//		Thread t19 = new Thread(new Ship(1,  "SEnter 19"));
//		Thread t20 = new Thread(new Ship(1,  "SEnter 20"));



//		t1.start();
//		t2.start();
//		t3.start();
//		t4.start();
//		t5.start();
//		t6.start();
//		t7.start();
//		t8.start();
//		t9.start();
//		t10.start();
//		t11.start();
//		t12.start();
//		t13.start();
//		t14.start();
//		t15.start();
//		t16.start();
//		t17.start();
//		t18.start();
//		t19.start();
//		t20.start();

	}
}

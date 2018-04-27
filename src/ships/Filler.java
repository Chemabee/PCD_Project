package ships;

import java.util.concurrent.CountDownLatch;

public class Filler implements Runnable{
    private static Filler instance = null;
    private CountDownLatch cd;

    private Filler(){
    	cd = new CountDownLatch(5);
    }

    public static Filler getFiller(){
      if(instance == null)
        instance = new Filler();
      return instance;
    }
    public void countDown() {
    	cd.countDown();//Baja en uno el contador del latch
    }
    
    public void run(){
    	for (int i = 0; i < 5; i++) {//TODO poner una condicion mejor que detecte cuando esperar y cuando no, aunque dejarlo como true tampoco pasa nada
    		try {
    			cd.await();
    			cd = new CountDownLatch(5);//Recarga el latch a 5
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
		}
    	
    }
}

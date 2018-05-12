package ships;

import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;

public class Filler implements Runnable{
    private static Filler instance = null;
    private CountDownLatch cd;
    private int cont=0;

    private Filler(){
    	cd = new CountDownLatch(5);
    }

    public static Filler getFiller(){
      if(instance == null)
        instance = new Filler();
      return instance;
    }
    
	public void countDown() {
		cd.countDown();// Baja en uno el contador del latch
		SynchronousQueue<OilShip>[] bq = ChargeZone.getChargeZone().getBQ();
		@SuppressWarnings("unused")
		OilShip o;
		for (int i = 0; i < bq.length; i++)
			 try{
				 o = bq[i].element();
				 cont++;
			 } catch (NoSuchElementException e) {
				 ;
			 }
		if(cont==5)
			for (int i = 0; i < bq.length; i++) {
				try {
					bq[i].take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cont = 0;
			}
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

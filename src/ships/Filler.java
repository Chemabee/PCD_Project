package ships;

public class Filler implements Runnable{
    private static Filler instance = null;

    private Filler(){
    }

    public static Filler getFiller(){
      if(instance == null)
        instance = new Filler();
      return instance;
    }
    
    public void run(){

    }
}

package packt.hofprogj.anonclasses;

public class AnonThread {

    public static void main(String[] args) {
        Thread thread = new Thread(){
            //anonymous object  run() method implementation
            public void run(){
                System.out.println("hello from inside the anonymous thread");
            }
        };
        thread.start();

    }

}

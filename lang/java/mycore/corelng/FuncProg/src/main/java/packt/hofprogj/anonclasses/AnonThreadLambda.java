package packt.hofprogj.anonclasses;

public class AnonThreadLambda {

        public static void main(String[] args) {
            System.out.println("main method thread:" + Thread.currentThread().getName());

            new Thread(() -> {
                System.out.println("hello from lambda thread");
                System.out.println("lambda thread:" + Thread.currentThread().getName());
            })
            .start();  //a new thread is started
        }

}

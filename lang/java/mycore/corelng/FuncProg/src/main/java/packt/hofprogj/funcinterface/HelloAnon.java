package packt.hofprogj.funcinterface;

//classic intface definition
interface Hello {
    void sayhello();
}

public class HelloAnon {

    public static void main(String[] args) {

        Hello englishHello = () -> System.out.println("hello");
        Hello frenchHello = () -> System.out.println("ciao oklm");
        Hello russianHello = () -> System.out.println("привет, чуваки");

        //using it
        englishHello.sayhello();
        frenchHello.sayhello();
        russianHello.sayhello();
    }
}

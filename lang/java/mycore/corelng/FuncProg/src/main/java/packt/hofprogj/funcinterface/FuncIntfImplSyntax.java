package packt.hofprogj.funcinterface;


//only one abstract method in a functional interface
@FunctionalInterface
interface NoParameters{
    void print();
}
@FunctionalInterface
interface OneParameter{
    void print(String s);
}

@FunctionalInterface
interface MultipleParameters{
    void print(String par1, String par2);
}


public class FuncIntfImplSyntax {
    public static void main(String[] args) {
        NoParameters noParams =
                () -> System.out.println("helo with no params.");
        OneParameter oneParam =
                (param) -> System.out.println("hello with param: "+param);
        MultipleParameters multiParam =
                (par1,par2)-> System.out.println("hello with params 1:"
                                    +par1+" param 2:"+par2);

        //call it
        noParams.print();
        oneParam.print("PARAM1");
        multiParam.print("PARAM1","PARAM2");
    }
}

package packt.hofprogj.funcinterface;

interface Parameter{
    int operation(int a, int b);
}

public class ParamAnon {
    //pass an interface in a method signature
    private static int method(Parameter parameter, int a, int b, int y){
        return parameter.operation(a,b)*y;  // sum() is not defined defined here
    }
    public static void main(String[] args) {
        System.out.println( "result of method with lambda 1: "+ method( (a,b)->a+b,1,2,3 )); //expect 9
        System.out.println( "result of method with lambda 2: "+ method( (a,b)->a*b,1,2,3 )); //expect 6
    }
}

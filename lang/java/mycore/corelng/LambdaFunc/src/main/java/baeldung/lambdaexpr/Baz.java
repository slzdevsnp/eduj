package baeldung.lambdaexpr;

@FunctionalInterface
public interface Baz {

    String method(String string);

    default String defaultBaz() {
        return "Default String from Baz";
    }

    default String defaultCommon(){
        return "Default Common from Baz";
    }
}
package baeldung.lambdaexpr;

@FunctionalInterface
public interface Bar {
    String method(String string);
    default String defaultBar() {
        return "Default String from Bar";
    }

    default String defaultCommon() {
        return "Default Common from Bar";
    }
}

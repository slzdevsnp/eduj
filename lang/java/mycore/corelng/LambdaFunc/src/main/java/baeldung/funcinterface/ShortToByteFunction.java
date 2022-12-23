package baeldung.funcinterface;

@FunctionalInterface
public interface ShortToByteFunction {
    byte applyAsByte(short s);
}

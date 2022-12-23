package baeldung.lambdaexpr;

// function inerfaces can be extended
@FunctionalInterface
public interface FooExtended extends Baz, Bar {

    @Override
    default String defaultCommon() {return Bar.super.defaultCommon();}

}

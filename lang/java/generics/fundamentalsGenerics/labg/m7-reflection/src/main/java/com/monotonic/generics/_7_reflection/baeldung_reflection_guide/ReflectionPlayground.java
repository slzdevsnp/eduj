package com.monotonic.generics._7_reflection.baeldung_reflection_guide;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionPlayground {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        reflectPersonsFields();

        goatClassAfterInstantiation();
        instantiateGoatObjectFromString(); //NB! this is  important
        //verify  class modifier
        checkClassModifiers();
        //find super class
        checkSuperClass();
        //find implmented interfaces //todo
        //inspect constructs ,methods, fields
        inspectConstructors();
        //instatiate object using specific constructor
        useSpecificBirdConstructor(); //NB! this is important
        //setting field values at runtime
        modifyInstanceFieldAtRuntime(); //NB! this is important
        //ispect static field
        inspectStaticField();
        //invoke method at runtime
        invokeMethodAtRuntime(); //NB! this is important
    }

    static void reflectPersonsFields() {
        Object person = new Person();
        Field[] fields = person.getClass().getDeclaredFields();
        List<String> actualFieldNames = getFieldNames(fields);
        for (String e : actualFieldNames) {
            System.out.println("Person reflected field:" + e);
        }
    }

    private static List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields)
            fieldNames.add(field.getName());
        return fieldNames;
    }

    static void goatClassAfterInstantiation() {
        Object goat = new Goat("goat");
        Class<?> clazz = goat.getClass();
        System.out.println("goat simple class name: " + clazz.getSimpleName());
        System.out.println("goat class name: " + clazz.getName());
        System.out.println("goat class canonical name: " + clazz.getCanonicalName());
    }

    static void instantiateGoatObjectFromString() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.monotonic.generics._7_reflection.baeldung_reflection_guide.Goat");
        Constructor<?> constructor = clazz.getConstructor(String.class);
        Goat goat = (Goat) constructor.newInstance("bleble"); //select specific constructor
        System.out.println("instantiated goat name: " + goat.getName());
        System.out.printf("instantiated goat locomation: " + goat.getLocomotion());
    }

    static void checkClassModifiers() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.monotonic.generics._7_reflection.baeldung_reflection_guide.Goat");
        Class<?> animalClass = Class.forName("com.monotonic.generics._7_reflection.baeldung_reflection_guide.Animal");
        int goatMods = goatClass.getModifiers();
        int animalMods = animalClass.getModifiers();
        System.out.println("goat class modifier is public: " + Modifier.isPublic(goatMods));
        System.out.println("animal class modifier is abstract: " + Modifier.isPublic(animalMods));
    }

    static void checkSuperClass() {
        Goat goat = new Goat("goat");
        String str = "any string";

        Class<?> goatClass = goat.getClass();
        Class<?> goatSuperClass = goatClass.getSuperclass();
        System.out.println("superclass of Goat: " + goatClass.getSuperclass().getSimpleName());
        System.out.println("superclass of Animal: " + goatSuperClass.getSuperclass().getSimpleName());
        System.out.println("superclass of String class: " + str.getClass().getSuperclass().getSimpleName());
    }

    static void inspectConstructors() throws ClassNotFoundException {
        //Class<?> goatClass = Class.forName("com.baeldung.reflection.Goat"); //runtime err if class isn't in path
        Class<?> goatClass = Class.forName("com.monotonic.generics._7_reflection.baeldung_reflection_guide.Goat");
        Constructor<?>[] constructors = goatClass.getConstructors();
        System.out.println("goat class has how many constructors: " + constructors.length);
        System.out.println("goat first constructor name: " + constructors[0].getName());
    }

    static void useSpecificBirdConstructor() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> birdClass = Class.forName("com.monotonic.generics._7_reflection.baeldung_reflection_guide.Bird");
        Constructor<?> cons1 = birdClass.getConstructor();
        Constructor<?> cons2 = birdClass.getConstructor(String.class);
        Constructor<?> cons3 = birdClass.getConstructor(String.class, boolean.class);

        //instatiate Bird objects with different constructors
        Bird bird1 = (Bird) cons1.newInstance();
        Bird bird2 = (Bird) cons2.newInstance("Weaver bird");
        Bird bird3 = (Bird) cons3.newInstance("dove", true);

        System.out.println("bird1 name:" + bird1.getName());
        System.out.println("bird2 name: " + bird2.getName());
        System.out.println("if bird1 walks: " + bird1.walks());
        System.out.println("if bird3 walks: " + bird3.walks());
    }

    static void modifyInstanceFieldAtRuntime() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> birdClass = Class.forName("com.monotonic.generics._7_reflection.baeldung_reflection_guide.Bird");
        Bird bird = (Bird) birdClass.getConstructor().newInstance();
        //field of interest
        Field field = birdClass.getDeclaredField("walks");
        field.setAccessible(true);
        System.out.println("bird if walks before mod: " + bird.walks());
        //modify
        field.set(bird, true);
        System.out.println("bird if walks after mod: " + bird.walks());
    }

    static void inspectStaticField() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> birdClass = Class.forName("com.monotonic.generics._7_reflection.baeldung_reflection_guide.Bird");
        //field of interest
        Field field = birdClass.getField("CATEGORY");
        field.setAccessible(true);
        System.out.println("birds static category value: " + field.get(null));
    }

    static void invokeMethodAtRuntime() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> birdClass = Class.forName("com.monotonic.generics._7_reflection.baeldung_reflection_guide.Bird");
        Bird bird = (Bird) birdClass.getConstructor().newInstance();
        Method setWalksMethod = birdClass.getDeclaredMethod("setWalks", boolean.class);
        Method walksMethod = birdClass.getDeclaredMethod("walks");
        boolean walks = (boolean) walksMethod.invoke(bird); //called a get method
        System.out.println("value fetched from bird.walk(): " + walks);
        //call a set method at runtime
        setWalksMethod.invoke(bird, true);
        boolean walks2 = (boolean) walksMethod.invoke(bird);
        System.out.println("value fetched from bird.walk() after invoking bird.setwalks(boolean): " + walks2);

        //add a method with a generic param to se that reflection on Bird type continues to work
        bird.printFlightDistances(Arrays.asList(11.456,21.0));
    }


}

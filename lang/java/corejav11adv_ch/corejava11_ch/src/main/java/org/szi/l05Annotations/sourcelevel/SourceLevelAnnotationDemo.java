package org.szi.l05Annotations.sourcelevel;

/*
Run these commands:

javac  -cp target/classes/ src/main/java/com/horstmann/annotations/ToStringAnnotationProcessor.java
javac -cp target/classes/ -processor com.horstmann.annotations.ToStringAnnotationProcessor  src/main/java/org/szi/l05Annotations/sourcelevel/*.java
java -cp target/classes org.szi.l05Annotations.sourcelevel.SourceLevelAnnotationDemo

Intellij 2020  managed to compile by its own all classes

*/

public class SourceLevelAnnotationDemo {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(new Point(10, 10), 20, 30);
        System.out.println(com.horstmann.annotations.ToStrings.toString(rect));
    }
}

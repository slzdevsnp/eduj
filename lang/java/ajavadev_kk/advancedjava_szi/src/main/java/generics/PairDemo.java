package generics;

import static generics.Pair.swap;

public class PairDemo {
    public static void main(String[] args) {
        Pair<Integer> pair = new Pair<>(3, 4);
        System.out.println(pair);

        //non-static method
        pair = pair.swap();
        System.out.println(pair);

        Pair<Integer> pair1 = new Pair<>(5, 12);
        System.out.println(pair1);
        //call a static method
        Pair.swap(pair1);
        System.out.println(pair1);

        //full syntax
        Pair.<Integer>swap(pair1);  // Full syntax
        System.out.println(pair1);
    }
}

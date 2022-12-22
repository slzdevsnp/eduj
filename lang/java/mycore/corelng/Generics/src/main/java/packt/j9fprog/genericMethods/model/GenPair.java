package packt.j9fprog.genericMethods.model;

// unbounded K, V types
public class GenPair<K,V> {
    private final K left;
    private final V right;

    public GenPair(K left, V right) {
        this.left = left;
        this.right = right;
    }
    public K getLeft() {
        return this.left;
    }
    public V getRight() {
        return this.right;
    }
    @Override
    public String toString(){
        return ("Pair: left:"+this.left +" right:"+this.right);
    }
}

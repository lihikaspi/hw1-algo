/**
 * generic node of type RunnerID
 *
 * @param <T> extends RunnerID
 */
public abstract class Node<T extends RunnerID> {
    /* sentinels */
    static final int POSITIVE_INFINITY = 1;
    static final int NEGATIVE_INFINITY = -1;
    static final int NOT = 0;

    /* attributes */
    private Node<T> left;
    private Node<T> middle;
    private Node<T> right;
    private Node<T> p;
    protected T key;
    private int isSentinel = NOT;
    private final boolean isLeaf;
    protected int size;

    public Node(boolean leaf, T key) {
        isLeaf = leaf;
        this.key = key;
    }

    /**
     * check if given runner's key equals to this runner's key <br>
     * takes O(1) time
     *
     * @param other key of runner to compare
     * @return if the two runners' keys are equal
     */
    public boolean equals(RunnerID other) {
        if(key == null || other == null)
            return false;
        /* this key < other key AND this key > other key */
        return !key.isSmaller(other) && !other.isSmaller(key);
    }

    /**
     * set sentinels <br>
     * takes O(1) time
     *
     * @param positive true for positive infinite
     */
    public void setKeyInfinity(boolean positive) {
        /* set key to be null and sentinel type to be +/- */
        key = null;
        if (positive)
            isSentinel = POSITIVE_INFINITY;
        else isSentinel = NEGATIVE_INFINITY;
    }

    public void setKey(T r) {
        key = r;
        isSentinel = NOT;
    }

    public T getKey() {
        return key;
    }

    public void setP(Node<T> p) {
        this.p = p;
    }

    public Node<T> getP() {
        return p;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setMiddle(Node<T> middle) {
        this.middle = middle;
    }

    public Node<T> getMiddle() {
        return middle;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getRight() {
        return right;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public int getIsSentinel() {
        return isSentinel;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}

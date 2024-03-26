public abstract class Node<T extends RunnerID> {
    static final int POSITIVE_INFINITY = 1;
    static final int NEGATIVE_INFINITY = -1;
    static final int NOT = 0;
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
     * delete a node
     */
    public void delete(){
        left = null;
        middle = null;
        right = null;
        p = null;
        key = null;
    }

    public void setKey(T r) {
        if (r == null)
            throw new java.lang.UnsupportedOperationException("you are stupid");
        key = r;
    }

    public boolean equals(RunnerID k) {
        return !key.isSmaller(k) && !k.isSmaller(key);
    }

    /**
     * set sentinels
     * @param positive true fot -infinite
     */
    public void setKeyInfinity(boolean positive) {
        key = null;
        if (positive)
            isSentinel = POSITIVE_INFINITY;
        else isSentinel = NEGATIVE_INFINITY;
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

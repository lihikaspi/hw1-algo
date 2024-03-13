public abstract class Node<T> {
    static final int POSITIVE_INFINITY = 1;
    static final int NEGATIVE_INFINITY = -1;
    private Node<T> left;
    private Node<T> middle;
    private Node<T> right;
    private Node<T> p;
    protected T key;
    private int isSentinel = 0;
    private boolean isLeaf;

    public Node(boolean leaf) {
        isLeaf = leaf;
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
        key = r;
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

    public void setP(Node p) {
        this.p = p;
    }

    public Node getP() {
        return p;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getLeft() {
        return left;
    }

    public void setMiddle(Node middle) {
        this.middle = middle;
    }

    public Node getMiddle() {
        return middle;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getRight() {
        return right;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public int getIsSentinel() {
        return isSentinel;
    }

    public void setIsSentinel(int isSentinel) {
        this.isSentinel = isSentinel;
    }
}

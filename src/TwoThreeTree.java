public class TwoThreeTree<T extends RunnerID> {
    private Node root;

    /**
     * constructor implements 2_3_Init
     */
    public TwoThreeTree(){
        Node<T> x = new NodeRunnerID<>(false);
        Node<T> l = new NodeRunnerID<>(true);
        Node<T> m = new NodeRunnerID<>(true);
        l.setKeyInfinity(false);
        m.setKeyInfinity(true);
        l.setP(x);
        m.setP(x);
        x.setKeyInfinity(false);
        x.setLeft(l);
        x.setMiddle(m);
        root = x;
    }

    /**
     * search in the 2_3_tree rooted at x for a node whose key is k
     * implements 2_3_search
     *
     * @param x root
     * @param k key of node we're looking fot
     * @return node in the 2_3_tree rooted at x whose key is k
     */
    public Node search(Node x, RunnerID k){
        if (x == null || k == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        if (x.isLeaf()) {
            if (x.equals(k))
                return x;
            else return null;
        }
        if (!x.getLeft().getKey().isSmaller(k)) {
            return search(x.getLeft(), k);
        } else if (!x.getMiddle().getKey().isSmaller(k)) {
            return search(x.getMiddle(), k);
        } else return search(x.getRight(), k);
    }

    /**
     * update the key of x to the maximum key in its subtree
     * @param x node to update its key
     */
    private void updateKey(Node x) {
        if (x == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        x.setKey(x.getLeft().getKey());
        if (x.getMiddle() != null)
            x.setKey(x.getMiddle().getKey());
        if (x.getRight() != null)
            x.setKey(x.getRight().getKey());
    }

    /**
     * set l, m and r to be the left, middle and right children, respectively, of x
     * @param x parent
     * @param l left child to be
     * @param m middle child to be
     * @param r right child to be
     */
    private void setChildren(Node x, Node l, Node m , Node r) {
        if (x == null || l == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        x.setLeft(l);
        x.setMiddle(m);
        x.setRight(r);
        l.setP(x);
        if (m != null)
            m.setP(x);
        if (r != null)
            r.setP(x);
        updateKey(x);
    }

    /**
     * insert node z as a child of node x
     * @param x parent
     * @param z new child
     * @return split parent
     */
    private Node insertAndSplit (Node x, Node z) {
        if (x == null || z == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        Node l = x.getLeft();
        Node m = x.getMiddle();
        Node r = x.getRight();
        if (r == null) {
            if (z.getKey().isSmaller(l.getKey()))
                setChildren(x, z, l, m);
            else if (z.getKey().isSmaller(m.getKey()))
                setChildren(x, l, z, m);
            else setChildren(x, l , m, z);
            return null;
        }
        Node<T> y = new Node<T>(false);
        if (z.getKey().isSmaller(l.getKey())) {
            setChildren(x, z, l, null);
            setChildren(y, m, r, null);
        } else if (z.getKey().isSmaller(m.getKey())) {
            setChildren(x, l, z, null);
            setChildren(y, m, r, null);
        } else if (z.getKey().isSmaller(r.getKey())) {
            setChildren(x, l, m, null);
            setChildren(y, z, r, null);
        } else {
            setChildren(x, l, m, null);
            setChildren(y, r, z, null);
        }
        return y;
    }

    /**
     * insert the new Node z into T
     * implements 2_3_insert
     *
     * @param z new Node
     */
    public void insert(Node z) {
        if (z == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        Node y = root;
        while (!y.isLeaf()) {
            if (z.getKey().isSmaller(y.getLeft().getKey()))
                y = y.getLeft();
            else if (z.getKey().isSmaller(y.getMiddle().getKey()))
                y = y.getMiddle();
            else y = y.getRight();
        }
        Node x = y.getP();
        z = insertAndSplit(x, z);
        while (x != root) {
            x = x.getP();
            if (z != null)
                z = insertAndSplit(x, z);
            else updateKey(x);
        }
        if (z != null) {
            Node<T> w = new Node<T>(false);
            Node<T> w = new Node<T>(false);
            setChildren(w, x, z, null);
            root = w;
        }
    }

    /**
     * borrow a child from a sibling x of y or merge x and y
     *
     * @param y node
     * @return a pointer to the parent of y (and x)
     */
    private Node borrowOrMerge(Node y) {
        if (y == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        Node z = y.getP();
        if (y == z.getLeft()) {
            Node x = z.getMiddle();
            if (x.getRight() != null) {
                setChildren(y, y.getLeft(), x.getLeft(), null);
                setChildren(x, x.getMiddle(), x.getRight(), null);
            } else {
                setChildren(x, y.getLeft(), x.getLeft(), x.getMiddle());
                y.delete();
                setChildren(z, x, z.getRight(), null);
            }
            return z;
        }
        if (y == z.getMiddle()) {
            Node x = z.getLeft();
            if (x.getRight() != null) {
                setChildren(y, x.getRight(), y.getLeft(), null);
                setChildren(x, x.getLeft(), x.getMiddle(), null);
            } else {
                setChildren(x, x.getLeft(), x.getMiddle(), y.getLeft());
                y.delete();
                setChildren(z, x, z.getRight(), null);
            }
            return z;
        }
        Node x = z.getMiddle();
        if (x.getRight() != null) {
            setChildren(y, x.getRight(), y.getLeft(), null);
            setChildren(x, x.getLeft(), x.getMiddle(), null);
        } else {
            setChildren(x, x.getLeft(), x.getMiddle(), y.getLeft());
            y.delete();
            setChildren(z, z.getLeft(), x, null);
        }
        return z;
    }

    /**
     * delete Node x from T
     *
     * @param x node to delete
     */
    public void delete(Node x) {
        if (x == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        Node y = x.getP();
        if (x == y.getLeft())
            setChildren(y, y.getMiddle(), y.getRight(), null);
        else if (x == y.getMiddle())
            setChildren(y, y.getLeft(), y.getRight(), null);
        else setChildren(y, y.getLeft(), y.getMiddle(), null);
        x.delete();
        while (y != null) {
            if (y.getMiddle() != null) {
                updateKey(y);
                y = y.getP();
            } else {
                if (y != root)
                    y = borrowOrMerge(y);
                else {
                    root = y.getLeft();
                    y.getLeft().setP(null);
                    y.delete();
                    return;
                }
            }
        }
    }

    public Node getRoot() {
        return root;
    }
}

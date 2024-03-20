public abstract class TwoThreeTree<T extends RunnerID> {
    private Node<T> root;


    /**
     * initializes the minimum tree with sentinels
     * @param x root
     * @param l left leaf - infinite
     * @param m right leaf - negative infinite
     */
    public void initialize(Node<T> x, Node<T> l, Node<T> m){
        l.setKeyInfinity(true);
        m.setKeyInfinity(false);
        l.setP(x);
        m.setP(x);
        x.setKeyInfinity(false);
        x.setLeft(l);
        x.setMiddle(m);
        this.root = x;
    }

    /**
     * search in the 2_3_tree rooted at x for a node whose key is k
     * implements 2_3_search
     *
     * @param x root
     * @param k key of node we're looking fot
     * @return node in the 2_3_tree rooted at x whose key is k
     */
    public Node<T> search(Node<T> x, RunnerID k){
        if (x == null || k == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        if (x.isLeaf()) {
            if (x.equals(k))
                return x;
            else return null;
        }

        if (x.getLeft().getIsSentinel()==0 && !k.isSmaller(x.getLeft().getKey())) {
            return search(x.getLeft(), k);
        } else if (x.getMiddle().getIsSentinel()==-1 || x.getMiddle().getKey().isSmaller(k)) {
            return search(x.getMiddle(), k);
        } else return search(x.getRight(), k);
    }

    /**
     * update the key of x to the maximum key in its subtree
     * @param x node to update its key
     */
    private void updateKey(Node<T> x) {
        if (x == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        x.setKey(x.getLeft().getKey());
        if(x.getMiddle()!=null) {
            if (x.getMiddle().getIsSentinel() == Node.NEGATIVE_INFINITY)
                x.setKeyInfinity(false);
            else x.setKey(x.getMiddle().getKey());
        }
        if(x.getRight()!=null) {
            if (x.getLeft().getIsSentinel() == Node.NEGATIVE_INFINITY)
                x.setKeyInfinity(false);
            else x.setKey(x.getRight().getKey());
        }

    }

    /**
     * set l, m and r to be the left, middle and right children, respectively, of x
     * @param x parent
     * @param l left child to be
     * @param m middle child to be
     * @param r right child to be
     */
    private void setChildren(Node<T> x, Node<T> l, Node<T> m , Node<T> r) {
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

    protected abstract Node<T> insertAndSplit(Node<T> x, Node<T> z);

    /**
     * insert node z as a child of node x
     * @param x parent
     * @param z new child
     * @param y helper node to split to
     * @return split parent
     */
    protected Node<T> insertAndSplit (Node<T> x, Node<T> z, Node<T> y) {
        if (x == null || z == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        Node<T> l = x.getLeft();
        Node<T> m = x.getMiddle();
        Node<T> r = x.getRight();
        if (r == null) {
            if (l.getIsSentinel() != Node.POSITIVE_INFINITY && l.getKey().isSmaller(z.getKey()))
                setChildren(x, z, l, m);
            else if (m.getIsSentinel() == Node.NEGATIVE_INFINITY || m.getKey().isSmaller(z.getKey()))
                setChildren(x, l, z, m);
            else setChildren(x, l , m, z);
            return null;
        }
        if (l.getIsSentinel() != Node.POSITIVE_INFINITY && l.getKey().isSmaller(z.getKey())) {
            setChildren(x, z, l, null);
            setChildren(y, m, r, null);
        } else if (m.getIsSentinel() == Node.NEGATIVE_INFINITY || m.getKey().isSmaller(z.getKey())) {
            setChildren(x, l, z, null);
            setChildren(y, m, r, null);
        } else if (r.getIsSentinel() == Node.NEGATIVE_INFINITY || r.getKey().isSmaller(z.getKey())) {
            setChildren(x, l, m, null);
            setChildren(y, z, r, null);
        } else {
            setChildren(x, l, m, null);
            setChildren(y, r, z, null);
        }
        return y;
    }

    public abstract void insert(Node<T> z);

    /**
     * insert the new Node z into T
     * implements 2_3_insert
     *
     * @param z new Node
     */
    protected void insert(Node<T> z, Node<T> w) {
        if (z == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        Node<T> y = root;
        while (!y.isLeaf()) {
            if (y.getLeft().getIsSentinel() != Node.POSITIVE_INFINITY &&
                    y.getLeft().getKey().isSmaller(z.getKey()))
                y = y.getLeft();
            else if (y.getLeft().getIsSentinel() == Node.NEGATIVE_INFINITY ||
                    y.getMiddle().getKey().isSmaller(z.getKey()))
                y = y.getMiddle();
            else y = y.getRight();
        }
        Node<T> x = y.getP();
        z = insertAndSplit(x, z);
        while (x != root) {
            x = x.getP();
            if (z != null)
                z = insertAndSplit(x, z);
            else updateKey(x);
        }
        if (z != null) {
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
    private Node<T> borrowOrMerge(Node<T> y) {
        if (y == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        Node<T> z = y.getP();
        if (y == z.getLeft()) {
            Node<T> x = z.getMiddle();
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
            Node<T> x = z.getLeft();
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
        Node<T> x = z.getMiddle();
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
    public void delete(Node<T> x) {
        if (x == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        Node<T> y = x.getP();
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

    public Node<T> getRoot() {
        return root;
    }
}

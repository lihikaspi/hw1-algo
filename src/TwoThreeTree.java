/**
 * abstract two-three-tree class
 *
 * @param <T> extends RunnerID
 */
public abstract class TwoThreeTree<T extends RunnerID> {
    private Node<T> root;

    /**
     * initializes the minimum tree with sentinels <br>
     * based on pseudocode of 2_3_init from the lectures <br>
     * takes O(1) time
     *
     * @param x root
     * @param l left leaf - positive infinite
     * @param m right leaf - negative infinite
     */
    public void initialize(Node<T> x, Node<T> l, Node<T> m){
        /* define sentinels and root and set size to be 0 */
        // positive sentinel on the most left leaf
        l.setKeyInfinity(true);
        l.setSize(0);
        l.setP(x);
        // negative sentinel on the most right leaf
        m.setKeyInfinity(false);
        m.setSize(0);
        m.setP(x);
        // root
        x.setKeyInfinity(false);
        x.setLeft(l);
        x.setMiddle(m);
        this.root = x;
        this.root.setSize(0);
    }

    /**
     * search in the 2_3_tree rooted at x for a node whose key is k <br>
     * based on pseudocode of 2_3_search from the lectures <br>
     * takes O(log(n)) time
     *
     * @param x root
     * @param k key of node we're looking for
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

        if (x.getLeft().getIsSentinel() == Node.NOT && !k.isSmaller(x.getLeft().getKey())) {
            return search(x.getLeft(), k);
        } else if (x.getMiddle().getIsSentinel() == Node.NEGATIVE_INFINITY || x.getMiddle().getKey().isSmaller(k)) {
            return search(x.getMiddle(), k);
        } else return search(x.getRight(), k);
    }

    /**
     * update the key of x to the maximum key in its subtree <br>
     * based on pseudocode from the lectures <br>
     * takes O(1) time
     *
     * @param x node to update its key
     */
    private void updateKey(Node<T> x) {
        if (x == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        if (x.isLeaf())
            throw new java.lang.UnsupportedOperationException("why leaf? :(");;

        if (x.getLeft().getIsSentinel() == Node.POSITIVE_INFINITY)
            // TODO: somehow left = negative sentinel; how the fuck did that happen?????????
            // TODO: also sentinels ith keys that are not null????? how????
            x.setKeyInfinity(true);
        else x.setKey(x.getLeft().getKey());
        if(x.getMiddle() != null) {
            if (x.getMiddle().getIsSentinel() == Node.NEGATIVE_INFINITY)
                x.setKeyInfinity(false);
            else x.setKey(x.getMiddle().getKey());
        }
        if(x.getRight() != null) {
            if (x.getRight().getIsSentinel() == Node.NEGATIVE_INFINITY)
                x.setKeyInfinity(false);
            else x.setKey(x.getRight().getKey());
        }

    }

    /**
     * set l, m and r to be the left, middle and right children, respectively, of x <br>
     * based on pseudocode from the lectures <br>
     * takes O(1) time
     *
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

    /**
     * insert node z as a child of node x <br>
     * based on pseudocode from the lectures <br>
     * takes O(1) time
     *
     * @param x parent
     * @param z new child
     * @return split parent
     */
    protected abstract Node<T> insertAndSplit(Node<T> x, Node<T> z);

    /**
     * insert node z as a child of node x <br>
     * based on pseudocode from the lectures <br>
     * takes O(1) time
     *
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
            x.setSize(x.getSize() + 1);
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
        updateSize(x);
        updateSize(y);
        return y;
    }

    /**
     * update the size attribute of given node <br>
     * takes O(1) time
     *
     * @param x node to update its size
     */
    private void updateSize(Node<T> x) {
        // the size of each node is the sum size of its children
        int size = x.getLeft().getSize() + x.getMiddle().getSize();
        if (x.getRight() != null)
            size += x.getRight().getSize();
        x.setSize(size);
    }

    /**
     * insert the new node z into the tree <br>
     * based on pseudocode of 2_3_insert from the lectures <br
     * takes O(log(n))
     *
     * @param z new node
     */
    public abstract void insert(Node<T> z);

    /**
     * insert the new node z into the tree <br>
     * based on pseudocode of 2_3_insert from the lectures <br
     * takes O(log(n))
     *
     * @param z new node
     * @param w helper node
     */
    protected void insert(Node<T> z, Node<T> w) {
        if (z == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        z.setSize(1);
        Node<T> y = root;
        while (!y.isLeaf()) {
            if (y.getLeft().getIsSentinel() == Node.POSITIVE_INFINITY ||
                    y.getLeft().getKey().isSmaller(z.getKey()))
                y = y.getLeft();
            else if (y.getMiddle().getIsSentinel() != Node.NEGATIVE_INFINITY &&
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
            updateSize(root);
        }
    }

    /**
     * borrow a child from a sibling x of y or merge x and y <br>
     * based on pseudocode form the lectures <br>
     * takes O(1) time
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
            updateSize(y);
            updateSize(x);
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
            updateSize(y);
            updateSize(x);
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
        updateSize(y);
        updateSize(x);
        return z;
    }

    /**
     * delete Node x from the tree <br>
     * based on pseudocode of 2_3_delete from the lectures <br>
     * takes O(log(n)) time
     *
     * @param x node to delete
     */
    public void delete(Node<T> x) {
        if (x == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        Node<T> y = x.getP();
        y.setSize(y.getSize() - 1);
        if (x == y.getLeft())
            setChildren(y, y.getMiddle(), y.getRight(), null);
        else if (x == y.getMiddle())
            setChildren(y, y.getLeft(), y.getRight(), null);
        else setChildren(y, y.getLeft(), y.getMiddle(), null);
        x.delete();
        while (y != null) {
            if (y.getMiddle() != null) {
                updateKey(y);
                updateSize(y);
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

    /**
     * find rank of a given node based in its key
     * based on pseudocode from the tutorials<br>
     * takes O(log(n)) time
     *
     * @param x node to find its rank
     * @return the position of the key in the linear order of the elements in the tree
     */
    public int rank(Node<T> x) {
        int rank = 1;
        Node<T> y = x.getP();
        while(y != null) {
            if (x.equals(y.getMiddle()))
                rank += y.getLeft().getSize();
            else if (x.equals(y.getRight()))
                rank += y.getLeft().getSize() + y.getMiddle().getSize();
            x = y;
            y = y.getP();
        }
        return rank;
    }

    public Node<T> getRoot() {
        return root;
    }
}

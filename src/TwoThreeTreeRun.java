/**
 * 2-3-tree for the runs of a single runner <br>
 * extends TwoThreeTree
 *
 * @param <T> extends RunnerID
 */
public class TwoThreeTreeRun<T extends RunnerID> extends TwoThreeTree<T>{

    public TwoThreeTreeRun(){
        Node<T> x = new NodeRun<>(false, null);
        Node<T> l = new NodeRun<>(true, null);
        Node<T> m = new NodeRun<>(true, null);
        super.initialize(x,l,m);
    }

    @Override
    protected Node<T> insertAndSplit(Node<T> x, Node<T> z) {
        return super.insertAndSplit(x, z, new NodeRun<>(false, null));
    }

    @Override
    public void insert(Node<T> z){
        super.insert(z, new NodeRun<>(false, null));
    }
}

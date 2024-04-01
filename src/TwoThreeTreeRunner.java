/**
 * 2-3-tree for all the runners
 * extends TwoThreeTree
 *
 * @param <T> extends RunnerID
 */
public class TwoThreeTreeRunner<T extends RunnerID> extends TwoThreeTree<T>{

    public TwoThreeTreeRunner(){
        NodeRunner<T> x = new NodeRunner<>(false, null, null);
        NodeRunner<T> l = new NodeRunner<>(true, null, null);
        NodeRunner<T> m = new NodeRunner<>(true, null, null);
        super.initialize(x,l,m);
    }

    @Override
    protected Node<T> insertAndSplit(Node<T> x, Node<T> z) {
        return super.insertAndSplit(x, z, new NodeRunner<>(false, null, null));
    }
    @Override
    public void insert(Node<T> z){
        super.insert(z, new NodeRunner<>(false, null, null));
    }


}

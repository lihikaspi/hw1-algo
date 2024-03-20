public class TwoThreeTreeRun<T extends RunnerID> extends TwoThreeTree<T>{
    /**
     * constructor implements 2_3_Init
     */
    public TwoThreeTreeRun(){
        Node<T> x = new NodeRun<>(false);
        Node<T> l = new NodeRun<>(true);
        Node<T> m = new NodeRun<>(true);
        super.initialize(x,l,m);
    }

    @Override
    protected Node<T> insertAndSplit(Node<T> x, Node<T> z) {
        return super.insertAndSplit(x, z, new NodeRun<>(false));
    }
    @Override
    public void insert(Node<T> z){
        super.insert(z, new NodeRun<>(false));
    }
}

public class TwoThreeTreeRunner<T extends RunnerID> extends TwoThreeTree<T>{
    /**
     * constructor implements 2_3_Init
     */
    public TwoThreeTreeRunner(){
        NodeRunner<T> x = new NodeRunner<>(false, null);
        NodeRunner<T> l = new NodeRunner<>(true, null);
        NodeRunner<T> m = new NodeRunner<>(true, null);
        super.initialize(x,l,m);
    }

    @Override
    protected Node<T> insertAndSplit(Node<T> x, Node<T> z) {
        return super.insertAndSplit(x, z, new NodeRunner<>(false, null));
    }
    @Override
    public void insert(Node<T> z){
        super.insert(z, new NodeRunner<>(false, null));
    }
}

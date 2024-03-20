public class NodeRunner<T extends RunnerID> extends Node<T> {
    private Runner runner;

    public NodeRunner(boolean leaf, Runner r) {
        super(leaf);
        if(leaf)
            this.runner = r;
    }
}

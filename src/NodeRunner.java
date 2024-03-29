/**
 * nodes for the ID tree <br>
 * extends Node
 *
 * @param <T> extends RunnerID
 */
public class NodeRunner<T extends RunnerID> extends Node<T> {
    private Runner runner;

    public NodeRunner(boolean leaf, Runner r, T key) {
        super(leaf, key);
        if(leaf)
            this.runner = r;
    }

    public Runner getRunner() {
        return runner;
    }
}

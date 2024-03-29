/**
 * nodes for the run trees of each runner <br>
 * extends Node
 *
 * @param <T> extends RunnerID
 */
public class NodeRun<T extends RunnerID> extends Node<T>{
    public NodeRun(boolean leaf, T key) {
        super(leaf, key);
    }
}

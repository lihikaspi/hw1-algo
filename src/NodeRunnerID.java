public class NodeRunnerID<T extends RunnerID> extends Node<T> {

    public NodeRunnerID(boolean leaf){
        super(leaf);
    }


    /**
     * compares between current key and given key
     * @param k key to compare
     * @return if the keys are the same
     */
    public boolean equals(RunnerID k) {
        return !key.isSmaller(k) && !k.isSmaller(key);
    }
}

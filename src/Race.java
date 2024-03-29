/**
 * Race??
 */
public class Race {
    private TwoThreeTreeRunner<RunnerID> IDTree;
    private TwoThreeTreeRunner<MinRunnerID> minTree;
    private TwoThreeTreeRunner<AvgRunnerID> avgTree;

    public Race(){
        init();
    }

    /**
     * initialization <br>
     * takes O(1) time
     */
    public void init() // O(1)
    {
        // create the trees
        IDTree = new TwoThreeTreeRunner<>();
        minTree = new TwoThreeTreeRunner<>();
        avgTree = new TwoThreeTreeRunner<>();
    }

    /**
     * add runner given an unique ID <br>
     * takes O(log(n)) time
     *
     * @param id unique ID
     */
    public void addRunner(RunnerID id) // O(log(n))
    {
        if (id == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        /* create new Runner object and add the runner to each tree */
        Runner r = new Runner(id);
        NodeRunner<RunnerID> node = new NodeRunner<>(true, r, id);
        // create placeholders for the runner in the min and avg trees
        MinRunnerID minID = new MinRunnerID(Float.MAX_VALUE, id);
        AvgRunnerID avgID = new AvgRunnerID(Float.MAX_VALUE, id);
        NodeRunner<MinRunnerID> nodeMin = new NodeRunner<>(true, r, minID);
        NodeRunner<AvgRunnerID> nodeAvg = new NodeRunner<>(true, r, avgID);
        // connect the runner to its placeholders
        r.setMinLeaf(nodeMin);
        r.setAvgLeaf(nodeAvg);
        // add the runner
        IDTree.insert(node);
        minTree.insert(nodeMin);
        avgTree.insert(nodeAvg);
    }

    /**
     * remove runner including all of their runs <br>
     * takes O(log(n)) time
     *
     * @param id unique ID
     */
    public void removeRunner(RunnerID id) // O(log(n))
    {
        if (id == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        /* find the runner in the IDs tree and remove them from the min and avg trees */
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        // remove the runner
        IDTree.delete(node);
        minTree.delete(r.getMinLeaf());
        avgTree.delete(r.getAvgLeaf());
    }

    /**
     * add run of runner with ID <br>
     * takes O(log(n) + log(m_runner)) time
     *
     * @param id unique ID
     * @param time unique time
     */
    public void addRunToRunner(RunnerID id, float time) // O(log(n) + log(m))
    {
        if(id == null || time <= 0)
            throw new java.lang.UnsupportedOperationException("not implemented");

        /* find the runner in the IDs tree and add a run to their Runner object */
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        r.addRun(time);
        //update the Trees
        this.updateTrees(r);
    }

    /**
     * remove run of runner with ID <br>
     * takes O(log(n) + log(m_runner)) time
     *
     * @param id unique ID
     * @param time time of run to remove
     */
    public void removeRunFromRunner(RunnerID id, float time) // O(log(n) + log(m))
    {
        if (id == null || time <= 0)
            throw new java.lang.UnsupportedOperationException("not implemented");

        /* find the runner in the IDs tree and remove the run */
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        r.deleteRun(time);
        //update the Trees
        this.updateTrees(r);
    }

    /**
     * find the runner with the lowest average runtime <br>
     * takes O(1) time
     *
     * @return runner with the lowest average runtime
     */
    public RunnerID getFastestRunnerAvg() // O(1)
    {
        /* lowest runtime average = key of the root in the avg tree */
        return avgTree.getRoot().getKey();
    }

    /**
     * find the runner with the lowest minimal runtime <br>
     * takes O(1) time
     *
     * @return runner with the lowest minimal runtime
     */
    public RunnerID getFastestRunnerMin() // O(1)
    {
        /* lowest minimal runtime = key of the root in the min tree */
        return minTree.getRoot().getKey();
    }

    /**
     * calculate the minimal runtime for runner ID <br>
     * takes O(log(n)) time
     *
     * @param id unique iD
     * @return minimal runtime of runner ID
     */
    public float getMinRun(RunnerID id) // O(log(n))
    {
        if (id == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        /* find the runner in the IDs tree and get their minimal runtime */
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        return r.getMin();
    }

    /**
     * calculate the average run time for runner ID <br>
     * takes O(log(n)) time
     *
     * @param id unique ID
     * @return average run time of runner ID
     */
    public float getAvgRun(RunnerID id) // O(log(n))
    {
        if (id == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        /* find the runner in the IDs tree and get their avg runtime */
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        return r.getAvg();
    }

    /**
     * find rank of runner ID based of average run times of all runners <br>
     * takes O(log(n)) time
     *
     * @param id unique ID
     * @return rank of runner ID
     */
    public int getRankAvg(RunnerID id) // O(log(n))
    {
        if (id == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        /* find the runner in the IDs tree and find their rank in the avg tree */
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        return avgTree.rank(r.getAvgLeaf());
    }

    /**
     * find rank of runner ID based of minimal run times of all runners <br>
     * takes O(log(n)) time
     *
     * @param id unique ID
     * @return rank of runner ID
     */
    public int getRankMin(RunnerID id) // O(log(n))
    {
        if (id == null)
            throw new java.lang.UnsupportedOperationException("not implemented");

        /* find the runner in the IDs tree and find their rank in the min tree */
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        return minTree.rank(r.getMinLeaf());
    }

    /**
     * update trees when a runner's runs are changed (added or deleted) <br>
     * takes O(log(n)) time
     *
     * @param r runner
     */
    private void updateTrees(Runner r){
        // delete and reinsert to the min tree
        this.minTree.delete(r.getMinLeaf());
        this.minTree.insert(r.getMinLeaf());
        // delete and reinsert to the avg tree
        this.avgTree.delete(r.getAvgLeaf());
        this.avgTree.insert(r.getAvgLeaf());
    }
}

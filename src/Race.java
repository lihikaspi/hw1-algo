public class Race {

    private TwoThreeTreeRunner<RunnerID> IDTree;
    private TwoThreeTreeRunner<MinRunnerID> minTree;
    private TwoThreeTreeRunner<AvgRunnerID> avgTree;


    /**
     * initialization
     */
    public void init() // O(1)
    {
        IDTree = new TwoThreeTreeRunner<>();
        minTree = new TwoThreeTreeRunner<>();
        avgTree = new TwoThreeTreeRunner<>();
    }

    /**
     * add runner given an unique ID
     * @param id unique ID
     */
    public void addRunner(RunnerID id) // O(log(n)) //
    {
        if (id == null)
            throw new java.lang.UnsupportedOperationException("not implemented");
        Runner r = new Runner(id);
        NodeRunner<RunnerID> node = new NodeRunner<>(true, r, id);
        MinRunnerID minID = new MinRunnerID(Float.MAX_VALUE, id);
        AvgRunnerID avgID = new AvgRunnerID(Float.MAX_VALUE, id);
        NodeRunner<MinRunnerID> nodeMin = new NodeRunner<>(true, r, minID);
        NodeRunner<AvgRunnerID> nodeAvg = new NodeRunner<>(true, r, avgID);
        r.setMinLeaf(nodeMin);
        r.setAvgLeaf(nodeAvg);
        IDTree.insert(node);
        minTree.insert(nodeMin);
        avgTree.insert(nodeAvg);
    }

    /**
     * remove runner including all of their runs
     * @param id unique ID
     */
    public void removeRunner(RunnerID id) // O(log(n))
    {
        if (id == null)
            throw new java.lang.UnsupportedOperationException("not implemented");
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        IDTree.delete(node);
        minTree.delete(r.getMinLeaf());
        avgTree.delete(r.getAvgLeaf());
    }

    /**
     * add run of runner with ID
     * @param id unique ID
     * @param time unique time
     */
    public void addRunToRunner(RunnerID id, float time) // O(log(n) + log(m))
    {
        if(id == null || time <= 0)
            throw new java.lang.UnsupportedOperationException("not implemented");
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        r.addRun(time);

        //update the Trees
        this.updateTrees(r);
    }

    /**
     * remove run of runner with ID
     * @param id unique ID
     * @param time time of run to remove
     */
    public void removeRunFromRunner(RunnerID id, float time) // O(log(n) + log(m))
    {
        if (id == null || time <= 0)
            throw new java.lang.UnsupportedOperationException("not implemented");
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        r.deleteRun(time);

        //update the Trees
        this.updateTrees(r);
    }

    /**
     * find the runner with the lowest average run time
     * @return runner with the lowest average run time
     */
    public RunnerID getFastestRunnerAvg() // O(1)
    {
        return avgTree.getRoot().getKey();
    }

    /**
     * find the runner with the lowest minimal run time
     * @return runner with the lowest minimal run time
     */
    public RunnerID getFastestRunnerMin() // O(1)
    {
        return minTree.getRoot().getKey();
    }

    /**
     * calculate the minimal run time for runner ID
     * @param id unique iD
     * @return minimal run time of runner ID
     */
    public float getMinRun(RunnerID id) // O(log(n))
    {
        if (id == null)
            throw new java.lang.UnsupportedOperationException("not implemented");
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        return r.getMin();
    }

    /**
     * calculate the average run time for runner ID
     * @param id unique ID
     * @return average run time of runner ID
     */
    public float getAvgRun(RunnerID id) // O(log(n))
    {
        if (id == null)
            throw new java.lang.UnsupportedOperationException("not implemented");
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        return r.getAvg();
    }

    /**
     * find rank of runner ID based of average run times of all runners
     * @param id unique ID
     * @return rank of runner ID
     */
    public int getRankAvg(RunnerID id) // O(log(n))
    {
        if (id == null)
            throw new java.lang.UnsupportedOperationException("not implemented");
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        return avgTree.rank(r.getAvgLeaf());
    }

    /**
     * find rank of runner ID based of minimal run times of all runners
     * @param id unique ID
     * @return rank of runner ID
     */
    public int getRankMin(RunnerID id) // O(log(n))
    {
        if (id == null)
            throw new java.lang.UnsupportedOperationException("not implemented");
        NodeRunner<RunnerID> node = (NodeRunner<RunnerID>)IDTree.search(IDTree.getRoot(), id);
        Runner r = node.getRunner();
        return minTree.rank(r.getMinLeaf());
    }

    /**
     * update trees when a runner's runs are changed (added or deleted)
     * @param r runner
     */
    private void updateTrees(Runner r){
        //  -   delete and reinsert to the min tree
        this.minTree.delete(r.getMinLeaf());
        this.minTree.insert(r.getMinLeaf());
        //  -   delete and reinsert to avg tree
        this.avgTree.delete(r.getAvgLeaf());
        this.avgTree.insert(r.getAvgLeaf());
    }
}

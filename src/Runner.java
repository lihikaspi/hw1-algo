/**
 * a single runner
 */
public class Runner {
    private RunnerID id;
    private float min;
    private float avg;
    private int countRuns;
    private TwoThreeTree<RunID> runTree;
    private Node<MinRunnerID> minLeaf;
    private Node<AvgRunnerID> avgLeaf;

    public Runner(RunnerID id){
        // initialize min and avg as max values
        this.min = Float.MAX_VALUE;
        this.avg = Float.MAX_VALUE;
        this.countRuns = 0;
        // create a new run tree
        this.runTree = new TwoThreeTreeRun<>();
        this.id = id;
    }

    /**
     * add a new run to the run tree <br>
     * takes O(log(n)) time
     *
     * @param time unique time
     */
    public void addRun(float time){
        // insert Run to the tree
        RunID newRun = new RunID(time);
        Node<RunID> newLeaf = new NodeRun<>(true, newRun);
        newLeaf.setKey(newRun);
        this.runTree.insert(newLeaf);

        // update the attributes
        if (time < this.min) {
            this.min = time;
            this.minLeaf.getKey().setMinRunTime(min);
        }
        if (this.countRuns != 0) {
            this.avg = (this.avg * this.countRuns + time) / (this.countRuns + 1);
            this.avgLeaf.getKey().setAvgRunTime(avg);
        } else {
            this.avg = time;
            this.avgLeaf.getKey().setAvgRunTime(avg);
        }
        this.countRuns++;
    }

    /**
     * delete a run from the run tree <br>
     * takes O(log(n)) times
     *
     * @param time time of tun to delete
     */
    public void deleteRun(float time){
        RunID fakeRun = new RunID(time);
        this.runTree.delete(this.runTree.search(this.runTree.getRoot(), fakeRun));

        // update the attributes
        // this relies on the representation of the tree as min tree
        if(this.countRuns > 1) {
            this.avg = (this.avg * this.countRuns - time) / (this.countRuns - 1);
            this.min = this.runTree.minimum().getTime();
        } else {
            this.avg = Float.MAX_VALUE;
            this.min = Float.MAX_VALUE;
        }
        this.countRuns--;

        // update the values of the leaves in the min and avg trees
        this.minLeaf.getKey().setMinRunTime(min);
        this.avgLeaf.getKey().setAvgRunTime(avg);
    }

    /**
     * check if a run of given time exists in the run tree <br>s
     * takes O(log(n)) time
     *
     * @param time run time to check
     * @return if there is a run of this time
     */
    public boolean runExists(float time){
        RunID fakeRun = new RunID(time);
        return this.runTree.search(this.runTree.getRoot(), fakeRun) != null;
    }

    public void setMinLeaf(Node<MinRunnerID> minLeaf) {
        this.minLeaf = minLeaf;
    }

    public void setAvgLeaf(Node<AvgRunnerID> avgLeaf) {
        this.avgLeaf = avgLeaf;
    }

    public Node<MinRunnerID> getMinLeaf() {
        return minLeaf;
    }

    public Node<AvgRunnerID> getAvgLeaf() {
        return avgLeaf;
    }

    public float getMin() {
        return min;
    }

    public float getAvg() {
        return avg;
    }
}

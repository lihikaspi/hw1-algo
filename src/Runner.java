public class Runner {
    private RunnerID id;
    private float min;
    private float avg;
    private int countRuns;
    private TwoThreeTree<RunID> runTree;
    private Node<MinRunnerID> minLeaf;
    private Node<AvgRunnerID> avgLeaf;

    public Runner(RunnerID id){
        this.min = Float.MAX_VALUE;
        this.avg = Float.MAX_VALUE;
        this.countRuns = 0;
        this.runTree = new TwoThreeTreeRun<>();
        this.id = id;
    }

    public void addRun(float time){
        //Insert Run to the tree
        RunID newRun = new RunID(time);
        Node<RunID> newLeaf = new NodeRun<>(true, newRun);
        newLeaf.setKey(newRun);
        this.runTree.insert(newLeaf);

        //update the attributes
        if(time<this.min) {
            this.min = time;
            this.minLeaf.getKey().setMinRunTime(min);
        }

        if(this.countRuns!=0) {
            this.avg = (this.avg * this.countRuns + time) / (this.countRuns + 1);
            this.avgLeaf.getKey().setAvgRunTime(avg);
        }
        else {
            this.avg = time;
            this.avgLeaf.getKey().setAvgRunTime(avg);
        }
        this.countRuns++;
    }

    public void deleteRun(float time){
        RunID fakeRun = new RunID(time);
        this.runTree.delete(this.runTree.search(this.runTree.getRoot(), fakeRun));

        //update the attributes
        //this relies on the representation of the tree as min tree - we need to make sure that
        // we implement the min version!!!

        if(this.countRuns>1) {
            this.avg = (this.avg * this.countRuns - time) / (this.countRuns - 1);
            float new_min = ((RunID)this.runTree.getRoot().getKey()).getTime();
            this.min=new_min;
        }
        else {
            this.avg = Float.MAX_VALUE;
            this.min = Float.MAX_VALUE;
        }
        this.countRuns--;

        this.minLeaf.getKey().setMinRunTime(min);
        this.avgLeaf.getKey().setAvgRunTime(avg);
    }

    public void setMinLeaf(Node minLeaf) {
        this.minLeaf = minLeaf;
    }

    public void setAvgLeaf(Node avgLeaf) {
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

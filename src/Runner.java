public class Runner {
    private RunnerID id;
    private float min;
    private float avg;
    private int countRuns;
    private TwoThreeTreeRunnerID runTree;
    private Node minLeaf;
    private Node avgLeaf;

    public Runner(RunnerID id){
        this.min = Float.MAX_VALUE;
        this.avg = Float.MAX_VALUE;
        this.countRuns = 0;
        this.runTree = new TwoThreeTreeRunnerID();
        this.id = id;
    }

    public void addRun(float time)

    public void setMinLeaf(Node minLeaf) {
        this.minLeaf = minLeaf;
    }

    public void setAvgLeaf(Node avgLeaf) {
        this.avgLeaf = avgLeaf;
    }
}

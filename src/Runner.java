public class Runner {
    private RunnerID id;
    private float min;
    private float avg;
    private int countRuns;
    private TwoThreeTree<Run> runTree;
    private Node<Run> minLeaf;
    private Node<Run> avgLeaf;

    public Runner(RunnerID id){
        this.min = Float.MAX_VALUE;
        this.avg = Float.MAX_VALUE;
        this.countRuns = 0;
        this.runTree = new TwoThreeTreeRun<Run>();
        this.id = id;
    }

    public void addRun(float time){
        //Insert Run to the tree
        Run newRun = new Run(time);
        Node<Run> newLeaf = new NodeRun<>(true);
        newLeaf.setKey(newRun);
        this.runTree.insert(newLeaf);

        //update the attributes
        if(time<this.min)
            this.min=time;

        if(this.countRuns!=0)
            this.avg = (this.avg * this.countRuns+time)/(this.countRuns+1);
        else
            this.avg = time;
        this.countRuns++;

        //update the Nodes
        //  -   change nothing in main tree
        //  -   delete and reinsert to the min tree
        //  -   delete and reinsert to avg tree
        // maybe make a function for this?
    }

    public void deleteRun(float time){
        Run fakeRun = new Run(time);
        this.runTree.delete(this.runTree.search(this.runTree.getRoot(), fakeRun));

        //update the attributes
        //this relies on the representation of the tree as min tree - we need to make sure that
        // we implement the min version!!!

        if(this.countRuns>1) {
            this.avg = (this.avg * this.countRuns - time) / (this.countRuns - 1);
            float new_min = ((Run)this.runTree.getRoot().getKey()).getTime();
            this.min=new_min;
        }
        else {
            this.avg = Float.MAX_VALUE;
            this.min = Float.MAX_VALUE;
        }
        this.countRuns--;

        //update the Nodes
        //  -   change nothing in main tree
        //  -   delete and reinsert to the min tree
        //  -   delete and reinsert to avg tree
        // maybe make a function for this?
    }

    public void setMinLeaf(Node minLeaf) {
        this.minLeaf = minLeaf;
    }

    public void setAvgLeaf(Node avgLeaf) {
        this.avgLeaf = avgLeaf;
    }
}

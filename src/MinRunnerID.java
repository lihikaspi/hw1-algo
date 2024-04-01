/**
 * nodes for the min runtime tree <br>
 * extends RunnerID
 */
public class MinRunnerID extends RunnerID{
    private float minRunTime;
    private final RunnerID runner;

    public MinRunnerID(float min, RunnerID runner)
    {
        this.minRunTime = min;
        this.runner = runner;
    }

    public void setMinRunTime(float minRunTime) {
        this.minRunTime = minRunTime;
    }

    public float getMinRunTime() {
        return minRunTime;
    }

    public RunnerID getRunner() {
        return runner;
    }

    /**
     * check if this key is smaller than the another key <br>
     * compares avg runtimes and if equal compares their runners
     *
     * @param other minRunnerID to compare
     * @return if this key is smaller than other key
     */
    @Override
    public boolean isSmaller(RunnerID other) {
        if (!(other instanceof MinRunnerID))
            return false;
        return (this.minRunTime < ((MinRunnerID) other).minRunTime) ||
                ((this.minRunTime == ((MinRunnerID) other).minRunTime) &&
                        (this.runner.isSmaller(((MinRunnerID) other).runner)));
    }

    @Override
    public String toString() {
        return this.runner + "" + this.minRunTime;
    }

}

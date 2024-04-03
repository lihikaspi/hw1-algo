/**
 * nodes for the avg runtimes tree <br>
 * extends RunnerID
 */
public class AvgRunnerID extends RunnerID{
    private float avgRunTime;
    private final RunnerID runner;

    public AvgRunnerID(float avg, RunnerID runner)
    {
        this.avgRunTime = avg;
        this.runner = runner;
    }

    public void setAvgRunTime(float avgRunTime) {
        this.avgRunTime = avgRunTime;
    }

    public RunnerID getRunner() {
        return runner;
    }

    /**
     * check if this key is smaller than the another key <br>
     * compares avg runtimes and if equal compares their runners
     *
     * @param other avgRunnerID to compare
     * @return if this key is smaller than other key
     */
    @Override
    public boolean isSmaller(RunnerID other) {
        if (!(other instanceof AvgRunnerID))
            return false;

        return (this.avgRunTime < ((AvgRunnerID) other).avgRunTime) ||
                ((this.avgRunTime == ((AvgRunnerID) other).avgRunTime) &&
                        (this.runner.isSmaller(((AvgRunnerID) other).runner)));
    }

    @Override
    public String toString() {
        return this.runner + "" + this.avgRunTime;
    }
}
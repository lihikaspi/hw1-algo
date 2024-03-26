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

    public float getAvgRunTime() {
        return avgRunTime;
    }

    public RunnerID getRunner() {
        return runner;
    }

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
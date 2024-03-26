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

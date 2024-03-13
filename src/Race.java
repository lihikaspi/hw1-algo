public class Race {
    /**
     * initialization
     */
    public void init() // O(1)
    {
        throw new java.lang.UnsupportedOperationException("not implemented");
    }

    /**
     * add runner given an unique ID
     * @param id unique ID
     */
    public void addRunner(RunnerID id) // O(log(n)) //
    {
        throw new java.lang.UnsupportedOperationException("not implemented");
    }

    /**
     * remove runner including all of their runs
     * @param id unique ID
     */
    public void removeRunner(RunnerID id) // O(log(n))
    {
        throw new java.lang.UnsupportedOperationException("not implemented");
    }

    /**
     * add run of runner with ID
     * @param id unique ID
     * @param time unique time
     */
    public void addRunToRunner(RunnerID id, float time) // O(log(n) + log(m))
    {
        throw new java.lang.UnsupportedOperationException("not implemented");
    }

    /**
     * remove run of runner with ID
     * @param id unique ID
     * @param time time of run to remove
     */
    public void removeRunFromRunner(RunnerID id, float time) // O(log(n) + log(m))
    {
        throw new java.lang.UnsupportedOperationException("not implemented");
    }

    /**
     * find the runner with the lowest average run time
     * @return runner with the lowest average run time
     */
    public RunnerID getFastestRunnerAvg() // O(1)
    {

        throw new java.lang.UnsupportedOperationException("not implemented");
    }

    /**
     * find the runner with the lowest minimal run time
     * @return runner with the lowest minimal run time
     */
    public RunnerID getFastestRunnerMin() // O(1)
    {

        throw new java.lang.UnsupportedOperationException("not implemented");
    }

    /**
     * calculate the minimal run time for runner ID
     * @param id unique iD
     * @return minimal run time of runner ID
     */
    public float getMinRun(RunnerID id) // O(log(n))
    {

        throw new java.lang.UnsupportedOperationException("not implemented");
    }

    /**
     * calculate the average run time for runner ID
     * @param id unique ID
     * @return average run time of runner ID
     */
    public float getAvgRun(RunnerID id) // O(log(n))
    {
        throw new java.lang.UnsupportedOperationException("not implemented");
    }

    /**
     * find rank of runner ID based of average run times of all runners
     * @param id unique ID
     * @return rank of runner ID
     */
    public int getRankAvg(RunnerID id) // O(log(n))
    {
        throw new java.lang.UnsupportedOperationException("not implemented");
    }

    /**
     * find rank of runner ID based of minimal run times of all runners
     * @param id unique ID
     * @return rank of runner ID
     */
    public int getRankMin(RunnerID id) // O(log(n))
    {
        throw new java.lang.UnsupportedOperationException("not implemented");
    }
}

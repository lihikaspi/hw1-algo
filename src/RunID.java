/**
 * a single run <br>
 * extends RunnerID
 */
public class RunID extends RunnerID{
    private final float time;
    public RunID(float time){
        super();
        this.time = time;
    }

    /**
     * check if this runtime is smaller the other run time
     *
     * @param other runID to compare
     * @return if this runtime is smaller
     */
    @Override
    public boolean isSmaller(RunnerID other){
        if (!(other instanceof RunID))
            throw new java.lang.IllegalArgumentException("not implemented");
        return this.time < ((RunID)other).getTime();
    }
    public String toString(){
        return String.valueOf(time);
    }
    public float getTime(){
        return this.time;
    }
}

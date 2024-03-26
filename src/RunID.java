public class RunID extends RunnerID{
    private final float time;
    public RunID(float time){
        super();
        this.time = time;
    }
    @Override
    public boolean isSmaller(RunnerID other){
        if (!(other instanceof RunID))
            throw new java.lang.UnsupportedOperationException("not implemented");
        return this.time < ((RunID)other).getTime();
    }
    public String toString(){
        return String.valueOf(time);
    }
    public float getTime(){
        return this.time;
    }
}

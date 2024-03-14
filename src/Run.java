public class Run extends RunnerID{
    private final float time;
    public Run(float time){
        super();
        this.time = time;
    }
    @Override
    public boolean isSmaller(RunnerID other){
        if (!(other instanceof Run))
            throw new java.lang.UnsupportedOperationException("not implemented");
        return this.time < ((Run)other).getTime();
    }
    public String toString(){
        return String.valueOf(time);
    }
    public float getTime(){
        return this.time;
    }
}

class RunnerIDInt extends RunnerID{
    private int id;
    public RunnerIDInt(int id){
        super();
        this.id = id;
    }
    @Override
    public boolean isSmaller(RunnerID other) {
        return this.id < ((RunnerIDInt)other).id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }


}


public class Main {
    public static void main(String[] args) {
        // The ids which we will check will not necessarily be RunnerIDInt
        // This is just for the example
        RunnerIDInt id1 = new RunnerIDInt(3);
        RunnerIDInt id2 = new RunnerIDInt(5);
        RunnerIDInt id3 = new RunnerIDInt(7);
        RunnerIDInt id4 = new RunnerIDInt(4);
        Race race = new Race();
        race.addRunner(id1);
        race.addRunner(id2);
        race.addRunner(id3);
        race.addRunner(id4);
        race.addRunToRunner(id1, (float)118.0);
        race.addRunToRunner(id2, (float) 90);
        race.addRunToRunner(id1, (float) 140);
        race.addRunToRunner(id3, (float) 88);
        race.addRunToRunner(id3, (float) 100);
        race.addRunToRunner(id4, (float) 91);
        System.out.println(id1 + "- id1 min: " + race.getRankMin(id1));
        System.out.println(id2 + "- id2 min: " + race.getRankMin(id2));
        System.out.println(id3 + "- id3 min: " + race.getRankMin(id3));
        System.out.println(id4 + "- id4 min: " + race.getRankMin(id4));
        System.out.println();
        System.out.println(id1 + "- id1 avg: " + race.getRankAvg(id1));
        System.out.println(id2 + "- id2 avg: " + race.getRankAvg(id2));
        System.out.println(id3 + "- id3 avg: " + race.getRankAvg(id3));
        System.out.println(id4 + "- id4 avg: " + race.getRankAvg(id4));
        System.out.println();
        System.out.println("The min running time of " + id2.toString() + " is " + race.getMinRun(id2));
        System.out.println("The avg running time of " + id1.toString() + " is " + race.getAvgRun(id1));
        System.out.println("The runner with the smallest minimum time is " + race.getFastestRunnerMin());
        System.out.println("The runner with the smallest average time is " + race.getFastestRunnerAvg());
        System.out.println("min rank of " + id4 + ": " + race.getRankMin(id4));
        System.out.println("remove 7");
        race.removeRunner(id3);
        System.out.println("The runner with the smallest minimum time is " + race.getFastestRunnerMin());
        System.out.println("avg rank of " + id2 + ": " + race.getRankAvg(id2));
        race.removeRunFromRunner(id1, (float) 118);
        System.out.println("remove run 118 from 3");
        System.out.println("The min running time of " + id1.toString() + " is " + race.getMinRun(id1));
        System.out.println("The avg running time of " + id1.toString() + " is " + race.getAvgRun(id1));

    }
}

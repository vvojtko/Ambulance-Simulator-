package solution;

import org.junit.jupiter.api.Test;
import uk.ac.aber.cs21120.hospital.*;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class Simulator implements ISimulator {
    private int ambulances;
    private int currentTime;

    public Simulator(int ambulances) {
        this.ambulances = ambulances;
    }
    Map<Integer, Integer> completionTimeMap = new TreeMap<>();
    Map<Integer, Integer> numOfJobsAtEachLvl = new TreeMap<>(); //hashmap to avoid concurrent modification exception
    PriorityQueue<IJob> waitingQueue = new PriorityQueue<>();
    Set<IJob> runningSet = new CopyOnWriteArraySet<>();


    @Override
    public void add(IJob j) {
        waitingQueue.add(j);
        j.setSubmitTime(currentTime);
    }

    @Override
    public void tick() throws NullPointerException, ConcurrentModificationException {
        for (IJob job : runningSet) {
            job.tick();
            if (job.isDone()) {             //if job2 is done run follow methods
                completionTimeMapping(job);
                numsOfJobMapping(job);
                ambulances++; //free up ambulance that done the job2
                runningSet.remove(job);
            }
        }

        while ((!waitingQueue.isEmpty()) && ambulances > 0) {

            ambulances--;
            runningSet.add(waitingQueue.poll());
        }

        currentTime++;
    }

    public void numsOfJobMapping(IJob job) {
        if (numOfJobsAtEachLvl.containsKey(job.getPriority())) {
            int r = numOfJobsAtEachLvl.get(job.getPriority());
            int r2 = r + 1;
            numOfJobsAtEachLvl.remove(job.getPriority());
            numOfJobsAtEachLvl.put(job.getPriority(), r2);
        } else {

            numOfJobsAtEachLvl.put(job.getPriority(), 1);
        }

    }

    public void completionTimeMapping(IJob job) throws NullPointerException {
        if(!completionTimeMap.containsKey(job.getPriority())){
            completionTimeMap.put(job.getPriority(),job.getTimeSinceSubmit(currentTime));

        }else {
            int x = completionTimeMap.get(job.getPriority());
            int x2 = x + job.getTimeSinceSubmit(currentTime);
            completionTimeMap.put(job.getPriority(), x2);
        }
        }



    @Override
    public int getTime() {
        return currentTime;
    }


    @Override
    public boolean allDone() {
        if (runningSet.isEmpty() && waitingQueue.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Set<Integer> getRunningJobs() {
        Set<Integer> returnSet = new HashSet<>();
        for (IJob job : runningSet) {
            returnSet.add(job.getID());
        }
        return returnSet;
    }

    @Override
    public double getAverageJobCompletionTime(int priority) throws NullPointerException {
        return (((double)((completionTimeMap.get(priority)) )/ (numOfJobsAtEachLvl.get(priority))));
        }

    public static void task3(int amb) {
        Simulator s = new Simulator(amb);
        JobDisplay jd = new JobDisplay();
        RandomPriorityGenerator r = new RandomPriorityGenerator();
        int id = 0;
        for (int i = 0; i < 10000; i++) {
            s.tick();
            jd.add(s);
            int g = new Random().nextInt(3);
            if (g == 1) {
                int p = r.next();
                int d = new Random().nextInt(11 + 1) + 10;
                Job k = new Job(id++, p, d);
                s.add(k);
                s.tick();
            }

            jd.add(s);
        }
        while (!s.allDone()) {
            s.tick();
            jd.add(s);

        }
        System.out.println("TEST WITH NUMBER OF AMBULANCES: " + amb);
        System.out.println("avgrage for priority 0: " + s.getAverageJobCompletionTime(0));
        System.out.println("average for priority 1: " + s.getAverageJobCompletionTime(1));
        System.out.println("average for priority 2: " + s.getAverageJobCompletionTime(2));
        System.out.println("average for priority 3: " + s.getAverageJobCompletionTime(3));
        System.out.println("\n");


    }

    public static void task4(){
        for(int i = 4; i<21; i++) {
            task3(i);
        }
    }
}







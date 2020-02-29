package solution;


import uk.ac.aber.cs21120.hospital.IJob;

public class Job implements IJob, Comparable<IJob> {
    //id to keep track of jobs
    private int id = 0;         //id to keep track of jobs
    private int priority;       //declaring priority for Job
    private int duration;       //declaring dura
    private int setSubTime;
    private int ticks_left;


    public Job(int id, int priority, int duration) {
        this.duration = duration;
        this.id = id;
        this.priority = priority;
        ticks_left = duration;
    }


    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getPriority() {
        return priority;
    }


    @Override
    public void tick() {
        ticks_left--;

    }


    @Override
    public boolean isDone() {
     if ((ticks_left == 0) || (ticks_left < 0)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getTimeSinceSubmit(int now) throws RuntimeException, NullPointerException {
            return (now - setSubTime);
        }


    @Override
    public void setSubmitTime(int time) {
        setSubTime = time;
    }

    @Override
    public int compareTo(IJob o) {
        return Integer.compare(priority, o.getPriority());
    }


}


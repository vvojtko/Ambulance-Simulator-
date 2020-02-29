package uk.ac.aber.cs21120.hospital;

import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import solution.Job;
import solution.Simulator;

public class CustomTests {

    @Test
    public void NoneAmbulanceTestAndTwoJobs(){
        Simulator s = new Simulator(0);
        Job a = new Job(1,2,3);
        Job b = new Job(2,2,3);
        s.add(a);
        for(int i = 0; i<100; i++) {
            s.tick();
        }
        Assertions.assertEquals(false,s.allDone());

    }
    @Test
    public void testCompletionTime_2() {
        JobDisplay jd = new JobDisplay();
        Simulator s = new Simulator(2);
        Job a = new Job(1, 1, 3);
        Job b = new Job(2, 1, 3);
        Job c = new Job(3, 1, 6);
        Job d = new Job(4,2,3);
        Job e = new Job(5,2,9);
        s.add(a);
        s.add(b);
        s.add(c);
        s.add(e);
        s.add(d);
        for (int i = 0; i < 20; i++) {
            s.tick();
            jd.add(s);
        }
        jd.show();
        Assertions.assertEquals(12,s.getAverageJobCompletionTime(2));


    }
    @Test
    public void testCompletionTime() {
        JobDisplay jd = new JobDisplay();

        Simulator s = new Simulator(2);
        Job a = new Job(0, 1, 3);
        Job b = new Job(1, 1, 3);
        Job c = new Job(2, 1, 6);
        Job d = new Job(2, 1, 3);
        s.add(a);
        s.add(b);
        s.add(c);
        for (int i = 0; i < 20; i++) {
            s.tick();
            jd.add(s);
            if(i==10) {
                s.add(d);
            }
        }
        jd.show();
        Assertions.assertEquals(4.5, s.getAverageJobCompletionTime(1));
    }

    @Test
    public void areJobsDone() {
        Simulator s = new Simulator(1);
        Job a = new Job(1,1,6);
        Job b = new Job(2,1,4);
        s.add(a);
        for(int i = 0; i < 10; i++) {
            s.tick();
        }
        Assertions.assertEquals(true, s.allDone());
    }
/*
    @Test
    public void task3Test(){
    	Simulator s = new Simulator(4);
    	s.task3(4);

    	Assertions.assertEquals(1,s.getAverageJobCompletionTime(0));
    }

*/  @Test
    public void task3Test(){
        Simulator.task3(4);
    }

    @Test
    public void test4Test(){
        Simulator.task4();
    }

    @Test
    public void displayTest2() {
        Simulator sim = new Simulator(2);
        JobDisplay jd = new JobDisplay();
        Job a = new Job(1, 1, 3);
        Job b = new Job(2, 1, 3);
        Job c = new Job(3, 1, 6);
        Job d = new Job(4,2,3);
        Job e = new Job(5,2,9);


        sim.add(a);
        sim.add(b);
        sim.add(c);
        sim.add(e);
        sim.add(d);
        for(int i=0;i<40;i++) {
            sim.tick();
            jd.add(sim);

        }

        jd.show();

    }

    @Test
    public void isJobDone(){
        Job j = new Job(1,2,3);
        for(int i = 0; i < 3; i ++) {
            j.tick();
        }
        Assertions.assertEquals(true,j.isDone());
    }

    @Test void setSubmitTest(){
        Job j = new Job(1,2,3);
        j.getTimeSinceSubmit(5);
    }

    @Test
    public void OneJobTest(){
        Simulator sim = new Simulator(1);
        Job job = new Job(1,2,5);
        sim.add(job);
        for(int i = 0; i < 6; i++) {
            sim.tick();
        }
        Assertions.assertEquals(true, job.isDone());

    }

    @Test
    public void OneAmbulanceThreeJobs() {
        Simulator sim = new Simulator(1);
        Job job = new Job(1,2,3);
        Job job2 = new Job(2,3,4);
        sim.add(job);
        sim.add(job2);
        for(int i = 0; i < 8; i++) {
            sim.tick();
        }
        Assertions.assertEquals(true, sim.allDone());
    }

    @Test
    public void CombinedTrueTest(){
        Simulator s = new Simulator(1);
        Job a = new Job(1, 1, 3);
        Job b = new Job(2, 1, 3);
        Job c = new Job(3, 1, 3);
        s.add(a);
        s.add(b);
        s.add(c);
        for (int i = 0; i < 13; i++) {
            s.tick();
        }
        Assertions.assertEquals(true,s.allDone());
        Assertions.assertEquals(6, s.getAverageJobCompletionTime(1));


    }


}

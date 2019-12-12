package com.example.tasks.remade;

import com.example.tasks.task_one.remade.Manager;
import com.example.tasks.task_one.remade.Programmer;
import com.example.tasks.task_one.remade.Worker;
import com.example.tasks.task_two.remade.Algorithms;
import com.example.tasks.task_two.remade2.RemadeAlgorithms;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class NewMergeTest {
    private List<Worker> workers1 = new ArrayList<>();
    private List<Worker> workers2 = new ArrayList<>();
    private List<Worker> deep = new ArrayList<>();

    @Before
    public void setUp() {
        Worker worker1 = new Manager(2000, 200, 160, 1);
        Worker worker2 = new Programmer(3000, 140, 160, 2);
        Worker worker3 = new Manager(2100, 190, 160, 3);
        Worker worker4 = new Programmer(3500, 200, 160, 4);
        Worker worker5 = new Manager(1800, 120, 160, 5);
        workers1.add(worker1);
        workers1.add(worker2);
        workers1.add(worker3);
        workers1.add(worker4);
        workers1.add(worker5);

        Worker worker11 = new Manager(1900, 210, 160, 1);
        Worker worker12 = new Manager(2500, 170, 160, 6);
        Worker worker13 = new Manager(2100, 190, 160, 3);
        Worker worker14 = new Programmer(3100, 190, 160, 4);
        Worker worker15 = new Manager(1800, 120, 160, 5);
        workers2.add(worker11);
        workers2.add(worker12);
        workers2.add(worker13);
        workers2.add(worker14);
        workers2.add(worker15);
    }

    @Test
    public void mergeTest() throws IllegalAccessException {
        workers1 = RemadeAlgorithms.mergeTwoWorkerLists(workers1,workers2);
        //RemadeAlgorithms.newMergeMethod(workers1, workers2);

        for(Worker worker : workers1){
            System.out.println("Id: " + worker.getId() + "; Stake: " + worker.getStake());
        }

        // see @Before result list variables to ensure
        Assert.assertEquals("Stake value of first worker has not been updated, bit it should", 1900, workers1.get(0).getStake(), 0.0f); // should be worker 11 stake value
        Assert.assertEquals("Stake of third worker shouldn't have changed. This worker wasn't updated", 2100, workers1.get(1).getStake(), 0.0f); // should be worker 13 stake value
        Assert.assertEquals("Stake of fourth worker hasn't been updated, but it should", 3100, workers1.get(2).getStake(), 0.0f); // should be worker 14 stake value
        Assert.assertEquals("Stake of fifth worker shouldn't have changed, but it had", 1800, workers1.get(3).getStake(), 0.0f); // should be worker 15 stake value
        Assert.assertEquals("Stake of new worker aren't right!", 2500, workers1.get(4).getStake(), 0.0f); // should be worker12 stake value
        Assert.assertEquals("The last worker wasn't merged properly! Class hasn't changed from PRG to MNG", workers1.get(4).getClass(), Manager.class);

    }
}

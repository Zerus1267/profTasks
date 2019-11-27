package com.example.tasks.remade;

import com.example.tasks.task_one.remade.Accountant;
import com.example.tasks.task_one.remade.Manager;
import com.example.tasks.task_one.remade.Programmer;
import com.example.tasks.task_one.remade.Worker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RemadeTaskOne {
    private List<Worker> workers;

    @Before
    public void setWorkers() {
        workers = new ArrayList<>();
        workers.add(new Programmer(20, 180, 160));
        workers.add(new Manager(15, 140, 160));
        workers.add(new Programmer(25, 120, 160));
    }

    @Test
    public void salaryTest() {
        Accountant accountant = new Accountant();
        Assert.assertEquals("Actual salary of all workers is wrong", accountant.calculateWorkersSalary(workers), 3600 + 2100 + 3000, 0.0f);
        /* 1) Programmer 20*160 (month normal salary) = 3200; 3200 * (180/160) = 3600
         * 2) Manager 15*160 (month normal salary) = 2400; 2400 * (140/160) = 2100
         * 3) Programmer 25*160 (normal salary) = 4000; 4000 * (120/160) = 3000 */
    }
}

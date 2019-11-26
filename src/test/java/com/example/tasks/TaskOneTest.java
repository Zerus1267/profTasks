/*
 * FINISHED
 * */

package com.example.tasks;

import com.example.tasks.task_one.Accountant;
import com.example.tasks.task_one.Manager;
import com.example.tasks.task_one.Programmer;
import com.example.tasks.task_one.Worker;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TaskOneTest {

    private Accountant accountant;
    private List<Worker> workers;

    @BeforeEach
    public void onSetup() {

        workers = new ArrayList<>();
        workers.add(new Programmer());
        workers.add(new Manager());
        workers.add(new Manager());
        //set first worker info
        workers.get(0).setStake(2000);
        workers.get(0).setWorkedHours(200);
        //set second worker info
        workers.get(1).setStake(1500);
        workers.get(1).setWorkedHours(120);
        //set third worker info
        workers.get(2).setStake(1300);
        workers.get(2).setWorkedHours(250);
        accountant = new Accountant(workers);
    }

    @Test
    public void contextLoads() {
        double salary = accountant.calcMonthSalary();
        Assert.assertEquals("Ожидаемая месячная зарплата не правильно расчитана!", salary, 4925, 0.0f);
        // Имеем зарплату программиста 2000 * (200/160) = 2500
        // Первый менеджер 1500 * (120/160) = 1500 * 0.75 = 1125
        // Второй менеджер 1300, он не получает сверхурочных.
        // Итого имеем 2500 + 1125 + 1300 = 4925
    }

}

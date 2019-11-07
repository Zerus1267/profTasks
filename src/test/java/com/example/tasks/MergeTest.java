package com.example.tasks;

import com.example.tasks.task_one.Address;
import com.example.tasks.task_one.Manager;
import com.example.tasks.task_one.Programmer;
import com.example.tasks.task_one.Worker;
import com.example.tasks.task_two.Algorithms;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MergeTest {

    private List<Worker> workers1 = new ArrayList<>();
    private List<Worker> workers2 = new ArrayList<>();
    private List<Worker> deepTest = new ArrayList<>();

    @Before
    public void setupDeepMerge(){
        Worker worker1 = new Manager(5000,160, new Address("Poltava","Grushevskogo"));
        worker1.setId(1);
        Worker worker2 = new Programmer(4000,200, new Address("Kharkiv","Sumskaya"));
        worker2.setId(2);
        Worker worker3 = new Manager(1500,180, new Address("Kyiv", "Maidan"));
        worker3.setId(3);
        Worker worker4 = new Manager(5500,200, new Address("Kyiv", "HTZ"));
        worker3.setId(5);
        Worker worker5 = new Programmer(1500,180, new Address("Kharkiv", "Obschaga"));
        worker3.setId(6);

        Worker workerA1 = new Manager(5000,160, new Address("Poltava","Vakulenci"));
        workerA1.setId(1);
        Worker workerA2 = new Manager(1500,180, new Address("Kyiv", "Maidan"));
        workerA2.setId(3);
        Worker workerA3 = new Programmer(4000,200, new Address("Kharkiv","Sumskaya"));
        workerA3.setId(2);
        Worker workerA4 = new Manager(1000,100, new Address("Dnipro","DniproStreet"));
        workerA4.setId(4);
        workers1.add(worker1);
        workers1.add(worker2);
        workers1.add(worker3);
        workers1.add(worker4);
        workers1.add(worker5);
        workers2.add(workerA1);
        workers2.add(workerA2);
        workers2.add(workerA3);
        workers2.add(workerA4);

        deepTest.add(workerA1); //Обновленный работник с id = 1. Изменено значение свойства улицы поля адрес
        deepTest.add(worker2); // С этим елементом ничего не произошло
        deepTest.add(worker3); // С эти также ничего не произошло
        // два обьекта из первого списка удалятся так как их нету во втором списке.
        deepTest.add(workerA4); // Новый елемент из второго списка. Должен быть в новом.
    }

    @Test
    public void deepMergeListOfWorkers(){
        Assert.assertEquals("Алгоритм deep merge работает не так как нужно", deepTest, Algorithms.mergeWorkersLists(workers1,workers2));
        // Здесь также главным является переопределение equals & hashcode, что бы они учитывали значения вложеных свойств
    }

    @Test
    public void mergeListOfWorkers(){
        Worker worker1 = new Manager(5000,160);
        worker1.setId(1);
        Worker worker2 = new Programmer(4000,200);
        worker2.setId(2);
        Worker worker3 = new Manager(1500,180);
        worker3.setId(3);

        Worker workerA1 = new Manager(5000,160);
        workerA1.setId(1);
        Worker workerA2 = new Manager(1500,200);
        workerA2.setId(3);
        Worker workerA3 = new Programmer(4000,200);
        workerA3.setId(2);
        Worker workerA4 = new Manager(1000,100);
        workerA4.setId(4);

        workers1.add(worker1);
        workers1.add(worker2);
        workers1.add(worker3);

        workers2.add(workerA1);
        workers2.add(workerA2);
        workers2.add(workerA3);
        workers2.add(workerA4);

        List<Worker> test = new ArrayList<>(); //создадим список работников которые должны остатся после merge`a
        test.add(worker1);
        test.add(worker2);
        test.add(workerA2);
        test.add(workerA4); // Единственный не повторяющийся елемент из двух списков
        Assert.assertEquals("Алгоритм merge работает не так как нужно", test, Algorithms.mergeWorkersLists(workers1,workers2));
        // сама проблема задачи в переопределении equals & hashcode поэтому необходимо переопределять их и для вложенных свойств обьектов.
    }

    @After
    public void clearLists(){
        workers1.clear();
        workers2.clear();
        deepTest.clear();
    }
}

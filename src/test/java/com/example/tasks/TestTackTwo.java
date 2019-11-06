/*
* NOT FINISHED YET
* */

package com.example.tasks;

import com.example.tasks.task_one.Address;
import com.example.tasks.task_one.Manager;
import com.example.tasks.task_one.Programmer;
import com.example.tasks.task_one.Worker;
import com.example.tasks.task_two.Algorithms;
import com.example.tasks.task_two.Scripts;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestTackTwo {

    @Test
    public void stringVowelWithAsterisk(){
        String string1 = "hello";
        String string2 = "healo";
        String string3 = "abc";
        String string4 = "oab";

        Assert.assertEquals("h*e*ll*o", Algorithms.interviewRecursionTest(string1));
        Assert.assertEquals("h*e*a*l*o", Algorithms.interviewRecursionTest(string2));
        Assert.assertEquals("a*bc", Algorithms.interviewRecursionTest(string3));
        Assert.assertEquals("o*a*b", Algorithms.interviewRecursionTest(string4));
    }

    @Test
    public void codingBinarySignalsToZeroes(){
        String signal1 = "01000111";
        String signal2 = "101101010111";

        Assert.assertEquals("00 0 0 0 00 000 0 000", Algorithms.messageCoding(signal1));
        Assert.assertEquals("0 0 00 0 0 00 00 0 0 0 00 0 0 0 00 0 0 000", Algorithms.messageCoding(signal2));
    }

    @Test
    public void mergeListOfWorkers(){
        Worker worker1 = new Manager(5000,160);
        Worker worker2 = new Programmer(4000,200);
        Worker worker3 = new Manager(1500,180);
        Worker workerA1 = new Manager(5000,160);
        Worker workerA2 = new Manager(1500,180);
        Worker workerA3 = new Programmer(4000,200);
        Worker workerA4 = new Manager(1000,100);

        List<Worker> workers1 = new ArrayList<>();
        workers1.add(worker1);
        workers1.add(worker2);
        workers1.add(worker3);
        List<Worker> workers2 = new ArrayList<>();
        workers2.add(workerA1);
        workers2.add(workerA2);
        workers2.add(workerA3);
        workers2.add(workerA4);

        List<Worker> test = new ArrayList<>(); //создадим список работников которые должны остатся после merge`a
        test.add(worker1);
        test.add(worker2);
        test.add(worker3);
        test.add(workerA4); // Единственный не повторяющийся елемент из двух списков
        Assert.assertEquals("Алгоритм merge работает так как нужно", test, Algorithms.mergeWorkersLists(workers1,workers2));
        // сама проблема задачи в переопределении equals & hashcode поэтому необходимо переопределять их и для вложенных свойств обьектов.
    }

    @Test
    public void deepMergeListOfWorkers(){
        Worker worker1 = new Manager(5000,160, new Address("Poltava","Grushevskogo"));
        Worker worker2 = new Programmer(4000,200, new Address("Kharkiv","Sumskaya"));
        Worker worker3 = new Manager(1500,180, new Address("Kyiv", "Maidan"));
        Worker workerA1 = new Manager(5000,160, new Address("Poltava","Vakulenci"));
        Worker workerA2 = new Manager(1500,180, new Address("Kyiv", "Maidan"));
        Worker workerA3 = new Programmer(4000,200, new Address("Kharkiv","Sumskaya"));
        Worker workerA4 = new Manager(1000,100, new Address("Dnipro","DniproStreet"));
        List<Worker> workers1 = new ArrayList<>();
        workers1.add(worker1);
        workers1.add(worker2);
        workers1.add(worker3);
        List<Worker> workers2 = new ArrayList<>();
        workers2.add(workerA1);
        workers2.add(workerA2);
        workers2.add(workerA3);
        workers2.add(workerA4);
        List<Worker> deepTest = new ArrayList<>();
        deepTest.add(worker1); //Три дублированных работников
        deepTest.add(worker2);
        deepTest.add(worker3);
        deepTest.add(workerA1); // Не повторяющийся елемент из-за вложенного свойства street поля address
        deepTest.add(workerA4); // Не повторяющийся елемент по всему полю address
        Assert.assertEquals("Алгоритм deep merge работает так как нужно", deepTest, Algorithms.mergeWorkersLists(workers1,workers2));
        // Здесь также главным является переопределение equals & hashcode, что бы они учитывали значения вложеных свойств
    }

    @Test
    public void createDBTablesTest() throws SQLException {
        Scripts scripts = new Scripts();
        scripts.createTables();
    }
}

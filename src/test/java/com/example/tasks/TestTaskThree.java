package com.example.tasks;

import com.example.tasks.task_one.Accountant;
import com.example.tasks.task_one.Manager;
import com.example.tasks.task_one.Programmer;
import com.example.tasks.task_one.Worker;
import com.example.tasks.task_three.JSONMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestTaskThree {

    List<Worker> workers = new ArrayList<>();

    @BeforeEach
    public void setup() {
        Worker worker = new Programmer(5000, 200);
        Worker worker1 = new Manager(3000, 120);
        workers.add(worker);
        workers.add(worker1);
    }

    @Test
    public void bTestReadFromJSON() throws IOException {
        Accountant accountant = JSONMapper.readFromJSONFile();
        Assert.assertEquals("Рабочие считались с файла не корректно!", accountant.getWorkers(), workers);
    }

    @Test
    public void aTestWriteIntoJSON() throws IOException {
        JSONMapper.writeIntoJSON(new Accountant(workers));
    }


}

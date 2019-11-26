package com.example.tasks;

import com.example.tasks.task_four.WorkerDAOImpl;
import com.example.tasks.task_one.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestTaskFour {
    private List<Worker> workers;
    private WorkerDAOImpl workerDAO;

    @Before
    public void createWorkers() {
        workerDAO = new WorkerDAOImpl();
        workers = new ArrayList<>();
        workers.add(new Programmer(2000, 200, new Address("Poltava", "Grusha")));
        workers.add(new Manager(1500, 210, new Address("Kharkiv", "Sumskaya")));
        workers.add(new Programmer(2500, 160, new Address("Kharkiv", "Tselynogradska")));
        workers.add(new Manager(1800, 140, new Address("Kyiv", "Maidan")));
    }

    @Test
    public void ainsertIntoDBTest() {
        try {
            workerDAO.saveWorkers(workers);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void breadFromDBTable() {
        List<Worker> workersTest = workerDAO.getAllWorkers();
        Assert.assertNotNull(workersTest);
        Accountant accountant = new Accountant(workers);
        Assert.assertEquals("Во время считывания с базы не были учтены классы обьектов", 8075, accountant.calcMonthSalary(), 0.0f);
        // Посчитаем зарплату программистов: 1) ставка 2000 и 200 часов. Получаем 2000*(200/160) = 2500
        // 2) Ставка 2500 и 160 часов, то есть норма. Получаем 2500. Итого 5000
        // Теперь менеджеры 1) 1500 ставка, независимо от переработки берем 1500. Итого 6500
        // 2) 1800 ставка, 140 часов. Получаем 1800*(140/160) = 1575.   Итого 8075.
    }

}

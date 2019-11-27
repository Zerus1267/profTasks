package com.example.tasks.task_one.remade;

import java.util.List;

public class Accountant {

    public double calculateWorkersSalary(List<Worker> workers) {
        double totalSalary = 0;
        for (Worker worker : workers) {
            totalSalary += worker.calculateSalary();
        }
        return totalSalary;
    }

    public double calculateSingleSalary(Worker worker) {
        return worker.calculateSalary();
    }
}

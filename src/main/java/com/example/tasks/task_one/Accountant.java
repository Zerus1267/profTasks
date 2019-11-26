package com.example.tasks.task_one;

import java.util.ArrayList;
import java.util.List;

public class Accountant {

    List<Worker> workers;

    public Accountant() {
        workers = new ArrayList<>();
    }

    public Accountant(List<Worker> workers) {
        this.workers = workers;
    }

    public double calcMonthSalary() {
        double resSalary = 0;
        for (Worker worker : this.workers) {
            resSalary += worker.calculateWorkLoad(40 * 4.0) * worker.getStake();
        }
        return resSalary;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}

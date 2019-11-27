package com.example.tasks.task_one.remade;

public class Manager extends Worker {

    public Manager() {
    }

    public Manager(double stake, double workedHours, double monthHours) {
        super(stake, workedHours, monthHours);
    }

    @Override
    public double calculateWorkLoad() {
        double workLoad = super.getWorkedHours() / super.getMonthHours();
        if (workLoad >= 1.0) {
            return 1.0;
        } else {
            return workLoad;
        }
    }
}

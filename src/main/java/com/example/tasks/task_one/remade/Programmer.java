package com.example.tasks.task_one.remade;

public class Programmer extends Worker {

    public Programmer() {
    }

    public Programmer(double stake, double workedHours, double monthHours) {
        super(stake, workedHours, monthHours);
    }

    @Override
    public double calculateWorkLoad() {
        return super.getWorkedHours() / super.getMonthHours();
    }
}

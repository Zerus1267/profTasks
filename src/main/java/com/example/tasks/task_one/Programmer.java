package com.example.tasks.task_one;


public class Programmer extends Worker{

    public Programmer() {
    }

    public Programmer(double stake, int workedHours) {
        super(stake, workedHours);
    }

    public Programmer(double stake, int workedHours, Address address) {
        super(stake, workedHours, address);
    }

    @Override
    public double calculateSalary() {
        return super.getStake()*super.getPureWorkLoad();
    }
}

package com.example.tasks.task_one;


public class Manager extends Worker{

    public Manager() {
    }

    public Manager(double stake, int workedHours) {
        super(stake, workedHours);
    }

    public Manager(double stake, int workedHours, Address address) {
        super(stake, workedHours, address);
    }

    @Override
    public double calculateSalary() {
        double workLoadCoefficient = super.getPureWorkLoad();
        if(workLoadCoefficient >= 1.0) workLoadCoefficient = 1.0;
        return super.getStake()*workLoadCoefficient;
    }
}

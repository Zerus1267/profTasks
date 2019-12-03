package com.example.tasks.task_one.remade;

public class Manager extends Worker {

    public Manager() {
    }

    public Manager(double stake, double workedHours, double monthHours) {
        super(stake, workedHours, monthHours);
    }

    public Manager(double stake, double workedHours, double monthHours, int id) {
        super(stake, workedHours, monthHours, id);
    }

    public Manager(double stake, double workedHours, double monthHours, int id, Address address) {
        super(stake, workedHours, monthHours, id, address);
    }

    public Manager(Manager manager){
        super(manager);
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

package com.example.tasks.task_one.remade;

public class Programmer extends Worker {

    public Programmer() {
    }

    public Programmer(double stake, double workedHours, double monthHours) {
        super(stake, workedHours, monthHours);
    }

    public Programmer(double stake, double workedHours, double monthHours, int id) {
        super(stake, workedHours, monthHours, id);
    }

    public Programmer(Programmer programmer){
        super(programmer);
    }

    public Programmer(double stake, double workedHours, double monthHours, int id, Address address) {
        super(stake, workedHours, monthHours, id, address);
    }

    @Override
    public double calculateWorkLoad() {
        return super.getWorkedHours() / super.getMonthHours();
    }
}

package com.example.tasks.task_one.remade;

public abstract class Worker {
    private double stake; // ставка работника в час
    private double workedHours; // отработаные часы
    private double monthHours; // необходимые для отработки часы в месяце

    public Worker() {
    }

    public Worker(double stake, double workedHours, double monthHours) {
        this.stake = stake;
        this.workedHours = workedHours;
        this.monthHours = monthHours;
    }

    abstract double calculateWorkLoad();

    public double calculateSalary() {
        return this.calculateWorkLoad() * (this.stake * this.monthHours);
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public double getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(double workedHours) {
        this.workedHours = workedHours;
    }

    public double getMonthHours() {
        return monthHours;
    }

    public void setMonthHours(double monthHours) {
        this.monthHours = monthHours;
    }
}

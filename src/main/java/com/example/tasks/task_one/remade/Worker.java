package com.example.tasks.task_one.remade;

import java.util.Objects;

public abstract class Worker {
    private double stake; // ставка работника в час
    private double workedHours; // отработаные часы
    private double monthHours; // необходимые для отработки часы в месяце
    private int id;
    private Address address;

    public Worker() {
    }

    public Worker(double stake, double workedHours, double monthHours) {
        this.stake = stake;
        this.workedHours = workedHours;
        this.monthHours = monthHours;
    }

    public Worker(double stake, double workedHours, double monthHours, int id) {
        this.stake = stake;
        this.workedHours = workedHours;
        this.monthHours = monthHours;
        this.id = id;
    }

    public Worker(double stake, double workedHours, double monthHours, int id, Address address) {
        this.stake = stake;
        this.workedHours = workedHours;
        this.monthHours = monthHours;
        this.id = id;
        this.address = address;
    }

    public Worker(Worker worker){
        this.stake = worker.getStake();
        this.workedHours = worker.getWorkedHours();
        this.monthHours = worker.getMonthHours();
        this.id = worker.getId();
        this.address = new Address(worker.getAddress());
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker worker = (Worker) o;
        return getId() == worker.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

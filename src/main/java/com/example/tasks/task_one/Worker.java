package com.example.tasks.task_one;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public abstract class Worker {
    private double stake; // ставка при 100% отработке
    private int workedHours;
    private final int weekWorkingNorma = 40; // Норма отработаных часов на неделю
    private Address address;

    public Worker() {
    }

    public Worker(double stake, int workedHours) {
        this.stake = stake;
        this.workedHours = workedHours;
    }

    public Worker(double stake, int workedHours, Address address) {
        this.stake = stake;
        this.workedHours = workedHours;
        this.address = address;
    }

    public abstract double calculateSalary();

    @JsonIgnore
    public double getPureWorkLoad(){
        return this.workedHours/(double)(this.weekWorkingNorma*4);
    }

    public void setStake(int stake) {
        this.stake = stake;
    }

    public double getStake() {
        return stake;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    public int getWorkedHours() {
        return workedHours;
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
        return Double.compare(worker.getStake(), getStake()) == 0 &&
                getWorkedHours() == worker.getWorkedHours() &&
                Objects.equals(getAddress(), worker.getAddress());
        /*if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker worker = (Worker) o;
        return Double.compare(worker.getStake(), getStake()) == 0 && getWorkedHours() == worker.getWorkedHours();*/
    }

    @Override
    public int hashCode() {
        return Objects.hash(15*getStake(), 7*getWorkedHours(), getAddress());
    }
}

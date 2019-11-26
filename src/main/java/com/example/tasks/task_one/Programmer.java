package com.example.tasks.task_one;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PRG")
public class Programmer extends Worker {

    public Programmer() {
    }

    public Programmer(double stake, int workedHours) {
        super(stake, workedHours);
    }

    public Programmer(double stake, int workedHours, Address address) {
        super(stake, workedHours, address);
    }

    // Old version
    @Override
    public double calculateSalary() {
        return super.getStake() * super.getPureWorkLoad();
    }

    // New version
    @Override
    public double calculateWorkLoad(double neededHours) {
        return super.getWorkedHours() / neededHours;
    }
}

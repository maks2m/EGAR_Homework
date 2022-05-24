package ru.elkin.egar.vehicle;

public class Boat extends Vehicle implements Swimming {


    public Boat(String brand, int speed, int weight, int fuelTank) {
        super(brand, speed, weight, fuelTank);
    }

    @Override
    public void swimming() {
        System.out.println(super.getBrand() + " swim!");
    }
}

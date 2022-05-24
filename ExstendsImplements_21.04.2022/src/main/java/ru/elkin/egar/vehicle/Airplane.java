package ru.elkin.egar.vehicle;

public class Airplane extends Vehicle implements Flying {


    public Airplane(String brand, int speed, int weight, int fuelTank) {
        super(brand, speed, weight, fuelTank);
    }

    @Override
    public void flying() {
        System.out.println(super.getBrand() + " fly!");
    }
}

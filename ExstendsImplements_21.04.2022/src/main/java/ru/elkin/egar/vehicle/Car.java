package ru.elkin.egar.vehicle;

public class Car extends Vehicle implements Driving {


    public Car(String brand, int speed, int weight, int fuelTank) {
        super(brand, speed, weight, fuelTank);
    }

    @Override
    public void driving() {
        System.out.println(super.getBrand() + " drive");
    }
}

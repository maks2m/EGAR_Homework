package ru.elkin.egar.vehicle;

public class Vehicle {

    private String brand;
    private int speed;
    private int weight;
    private int fuelTank;


    public Vehicle(String brand, int speed, int weight, int fuelTank) {
        this.brand = brand;
        this.speed = speed;
        this.weight = weight;
        this.fuelTank = fuelTank;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getFuelTank() {
        return fuelTank;
    }

    public void setFuelTank(int fuelTank) {
        this.fuelTank = fuelTank;
    }
}

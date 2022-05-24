package ru.elkin.egar;

import ru.elkin.egar.vehicle.Airplane;
import ru.elkin.egar.vehicle.Boat;
import ru.elkin.egar.vehicle.Car;
import ru.elkin.egar.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Airplane("Boeing 737", 800, 50000, 26020));
        vehicles.add(new Airplane("АН-24", 300, 20000, 5100));
        vehicles.add(new Boat("Costa Concordia", 40, 114147000, 3000000));
        vehicles.add(new Boat("Лодка надувная с мотором", 15, 70, 4));
        vehicles.add(new Car("Lada Vesta", 182, 1380, 55));
        vehicles.add(new Car("Hammer H-1", 150, 3500, 150));

        List<Airplane> airplanes = new ArrayList<>();
        List<Boat> boats = new ArrayList<>();
        List<Car> cars = new ArrayList<>();

        for (Vehicle v :
                vehicles) {
            System.out.println("Brand " + v.getBrand() +
                    ", Speed " + v.getSpeed() +
                    ", Weight " + v.getWeight() +
                    ", Fuel Tank " + v.getFuelTank()
            );
            if (v instanceof Airplane) {
                airplanes.add((Airplane) v);
            }
            if (v instanceof Boat) {
                boats.add((Boat) v);
            }
            if (v instanceof Car) {
                cars.add((Car) v);
            }
        }

        for (Airplane airplane :
                airplanes) {
            airplane.flying();
        }
        for (Boat boat :
                boats) {
            boat.swimming();
        }
        for (Car car :
                cars) {
            car.driving();
        }

    }
}

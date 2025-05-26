package edu.erciyes.trafficsimulation;

import java.util.Queue;

abstract public class Vehicle {
    private String direction;
    private double velocity;
    private double accelaration;

    public Vehicle(String direction, double velocity, double accelaration) {
        this.direction = direction;
        this.velocity = velocity;
        this.accelaration = accelaration;
    }

    public static Queue<Vehicle> VehicleCreator(String direction, int numberOfCars){
        Queue<Vehicle> Vehicles = null;
        for (int i = 0; i<numberOfCars; i++){
            Vehicles.add(new Car(direction, 0,1));
        }
        return Vehicles;
    }

    @Override
    public String toString() {
        return direction + " " + velocity + " " + accelaration;
    }
}

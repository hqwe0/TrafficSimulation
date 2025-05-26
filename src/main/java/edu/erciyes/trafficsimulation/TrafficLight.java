package edu.erciyes.trafficsimulation;

public class TrafficLight {
    public static final double CYCLE_TIME = 12;
    public static final double MAX_GREEN_TIME = 6;
    public static final double MIN_GREEN_TIME = 1;
    private double greenTime;
    private final double yellowTime = 0.3;
    private double redTime;
    private String direction;


    public TrafficLight(double greenTime, String direction) {
        this.greenTime = greenTime;
        this.redTime = CYCLE_TIME - greenTime - yellowTime;
        this.direction = direction;
    }

    public void setTimes(double greenTime){
        this.greenTime = greenTime;
        this.redTime = CYCLE_TIME - greenTime - yellowTime;
    }
}

package edu.erciyes.trafficsimulation;

import javafx.scene.shape.Circle;

public class TrafficLight {
    public static final int CYCLE_TIME = 120;
    public static final int MAX_GREEN_TIME = 60;
    public static final int MIN_GREEN_TIME = 10;
    private final int yellowTime = 3;
    private int greenTime;
    private int redTime;
    private String direction;


    public TrafficLight(int greenTime, String direction) {
        this.greenTime = greenTime;
        this.redTime = CYCLE_TIME - greenTime - yellowTime;
        this.direction = direction;
    }

    public void setTimes(int greenTime){
        this.greenTime = greenTime;
        this.redTime = CYCLE_TIME - greenTime - yellowTime;
    }

    public int getGreenTime() {
        return greenTime;
    }

    public int getRedTime() {
        return redTime;
    }

    public int getYellowTime() {
        return yellowTime;
    }

    public static void opacity(Circle one, Circle half1, Circle half2){
        one.setOpacity(1);
        half1.setOpacity(0.5);
        half2.setOpacity(0.5);
    }
}
package edu.erciyes.trafficsimulation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

import java.util.Random;

public class TrafficController {
    @FXML private TextField inputNorth, inputEast, inputSouth, inputWest;
    @FXML private Label northVehicleCountLabel;//, eastVehicleCountLabel, southVehicleCountLabel, westVehicleCountLabel;

    private static int northCarNumber, eastCarNumber, southCarNumber, westCarNumber;

    public void getCarNumbers(){
        System.out.println("Get Car Numbers çalıştı");
        northCarNumber = Integer.parseInt(inputNorth.getText());
        eastCarNumber = Integer.parseInt(inputEast.getText());
        southCarNumber = Integer.parseInt(inputSouth.getText());
        westCarNumber = Integer.parseInt(inputWest.getText());
        //northVehicleCountLabel.setText(inputNorth.getText());

    }
    public void randomCarNumbers(){
        System.out.println("Random Car Number çalıştı");
    }

    public void startTimer(){
        TrafficLight northLight = new TrafficLight(3,"NORTH");
        TrafficLight eastLight = new TrafficLight(3,"EAST");
        TrafficLight southLight = new TrafficLight(3,"SOUTH");
        TrafficLight westLight = new TrafficLight(3,"WEST");

    }


}

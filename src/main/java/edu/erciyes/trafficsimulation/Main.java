package edu.erciyes.trafficsimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Queue;
import java.util.Scanner;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280,720);
        stage.setTitle("Trafik Kontrol Sistemi");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();

//        TrafficLight northLight = new TrafficLight(3,"NORTH");
//        TrafficLight eastLight = new TrafficLight(3,"EAST");
//        TrafficLight southLight = new TrafficLight(3,"SOUTH");
//        TrafficLight westLight = new TrafficLight(3,"WEST");


//        while (true){
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("North car: ");
//            int northCarsNumber = scanner.nextInt();
//            System.out.print("East car: ");
//            int eastCarsNumber = scanner.nextInt();
//            System.out.print("South car: ");
//            int southCarsNumber = scanner.nextInt();
//            System.out.print("West car: ");
//            int westCarsNumber = scanner.nextInt();
//
//            TrafficCalculator.calculateTraffic(northLight,northCarsNumber,eastLight,eastCarsNumber,
//                    southLight,southCarsNumber,westLight,westCarsNumber);
//
//            Queue<Vehicle> northCars = Vehicle.VehicleCreator("NORTH",northCarsNumber);
//            Queue<Vehicle> eastCars = Vehicle.VehicleCreator("EAST",eastCarsNumber);
//            Queue<Vehicle> southCars = Vehicle.VehicleCreator("SOUTH",southCarsNumber);
//            Queue<Vehicle> westCars = Vehicle.VehicleCreator("WEST",westCarsNumber);
//        }


    }


}

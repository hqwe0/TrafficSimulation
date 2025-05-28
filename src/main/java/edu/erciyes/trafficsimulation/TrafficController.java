package edu.erciyes.trafficsimulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.Random;

public class TrafficController {
    @FXML private TextField inputNorth, inputEast, inputSouth, inputWest;
    @FXML private Label northVehicleCountLabel, eastVehicleCountLabel, southVehicleCountLabel, westVehicleCountLabel;
    @FXML private Label northTimer, eastTimer,southTimer,westTimer;
    @FXML private Circle northRed, northYellow, northGreen, eastRed, eastYellow, eastGreen, southRed, southYellow, southGreen, westRed, westYellow, westGreen;
    @FXML private Button btnPause;
    @FXML private Line northLine,eastLine,southLine,westLine;
    @FXML private Pane intersectionPane;

    private Timeline timeline;
    private boolean timelineInitial = false;
    private int northCurrentIndex = 0;
    private int northRemainingTime;
    private int[] northTimes = new int[3];

    private int eastCurrentIndex = 0;
    private int eastRemainingTime;
    private int[] eastTimes = new int[3];
    private int eastFirstRedTime;
    private boolean eastBool = true;

    private int southCurrentIndex = 0;
    private int southRemainingTime;
    private int[] southTimes = new int[3];
    private int southFirstRedTime;
    private boolean southBool = true;

    private int westCurrentIndex = 0;
    private int westRemainingTime;
    private int[] westTimes = new int[3];
    private int westFirstRedTime;
    private boolean westBool = true;
    private static int northCarNumber, eastCarNumber, southCarNumber, westCarNumber;

    public void getCarNumbers(){
        System.out.println("Get Car Numbers çalıştı");
        northCarNumber = Integer.parseInt(inputNorth.getText());
        eastCarNumber = Integer.parseInt(inputEast.getText());
        southCarNumber = Integer.parseInt(inputSouth.getText());
        westCarNumber = Integer.parseInt(inputWest.getText());
        northVehicleCountLabel.setText("North : " + inputNorth.getText());
        eastVehicleCountLabel.setText("East: " + inputEast.getText());
        southVehicleCountLabel.setText("South: " + inputSouth.getText());
        westVehicleCountLabel.setText("West: " + inputWest.getText());

    }
    public void randomCarNumbers(){
        System.out.println("Random Car Number çalıştı");
        Random random = new Random();
        northCarNumber = random.nextInt(0,25);
        eastCarNumber = random.nextInt(0,25);
        southCarNumber = random.nextInt(0,25);
        westCarNumber = random.nextInt(0,25);
        northVehicleCountLabel.setText("North : " + northCarNumber);
        eastVehicleCountLabel.setText("East: " + eastCarNumber);
        southVehicleCountLabel.setText("South: " + southCarNumber);
        westVehicleCountLabel.setText("West: " + westCarNumber);
    }

    public void startTimer (){
        timelineInitial = true;
        TrafficLight northLight = new TrafficLight(27,"NORTH");
        TrafficLight eastLight = new TrafficLight(27,"EAST");
        TrafficLight southLight = new TrafficLight(27,"SOUTH");
        TrafficLight westLight = new TrafficLight(27,"WEST");
        TrafficCalculator calculator = new TrafficCalculator();
        calculator.calculateTraffic(northLight,northCarNumber,eastLight,eastCarNumber,southLight,southCarNumber,westLight,westCarNumber);

        northTimes[0] = northLight.getGreenTime();
        northTimes[1] = northLight.getRedTime();
        northTimes[2] = northLight.getYellowTime();
        eastTimes[0] = eastLight.getRedTime();
        eastTimes[1] = eastLight.getYellowTime();
        eastTimes[2] = eastLight.getGreenTime();
        southTimes[0] = southLight.getRedTime();
        southTimes[1] = southLight.getYellowTime();
        southTimes[2] = southLight.getGreenTime();
        westTimes[0] = westLight.getRedTime();
        westTimes[1] = westLight.getYellowTime();
        westTimes[2] = westLight.getGreenTime();

        eastFirstRedTime = northLight.getGreenTime();
        southFirstRedTime = eastFirstRedTime + eastLight.getYellowTime() + eastLight.getGreenTime();
        westFirstRedTime = southFirstRedTime + southLight.getYellowTime() + southLight.getGreenTime();

        northCurrentIndex = 0;
        northRemainingTime = northTimes[northCurrentIndex];
        eastCurrentIndex = 0;
        eastRemainingTime = eastTimes[eastCurrentIndex];
        southCurrentIndex = 0;
        southRemainingTime = southTimes[southCurrentIndex];
        westCurrentIndex = 0;
        westRemainingTime = westTimes[westCurrentIndex];

        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);


        KeyFrame oneSecondTick = new KeyFrame(Duration.seconds(1), event -> {
            northControl(northTimes);
            eastControl(eastTimes, eastFirstRedTime);
            southControl(southTimes, southFirstRedTime);
            westControl(westTimes,westFirstRedTime);
        });
        timeline.getKeyFrames().addAll(oneSecondTick);
        timeline.play();


    }

    private void northControl(int[] times) {
        // Renk ayarlamaları
        if (this.northCurrentIndex == 0) { // Green
            northTimer.setTextFill(Color.GREEN);
            TrafficLight.opacity(northGreen,northYellow,northRed);
        } else if (this.northCurrentIndex == 1) { // Yellow
            northTimer.setTextFill(Color.RED);
            TrafficLight.opacity(northRed,northGreen,northYellow);
        } else { // Red
            northTimer.setTextFill(Color.YELLOW);
            TrafficLight.opacity(northYellow,northGreen,northRed);
        }

        // Kalan süreyi azalt
        northTimer.setText(String.valueOf((this.northRemainingTime)));
        this.northRemainingTime--;

        // Süre sıfırlandığında bir sonraki faza geç
        if (this.northRemainingTime <= 0) {
            this.northCurrentIndex = (this.northCurrentIndex + 1) % times.length;
            // Yeni faza geçince, o fazın başlangıç süresini remainingTime'a ata
            this.northRemainingTime = times[this.northCurrentIndex];
        }
    }

    private void eastControl(int[] times, int firstTime) {
        // Renk ayarlamaları
        if (this.eastCurrentIndex == 0) { // Red
            eastTimer.setTextFill(Color.RED);
            TrafficLight.opacity(eastRed,eastGreen,eastYellow);
        } else if (this.eastCurrentIndex == 1) { // Yellow
            eastTimer.setTextFill(Color.YELLOW);
            TrafficLight.opacity(eastYellow,eastGreen,eastRed);
        } else { // Green
            eastTimer.setTextFill(Color.GREEN);
            TrafficLight.opacity(eastGreen,eastYellow,eastRed);
        }

        if (eastBool){
            this.eastRemainingTime = firstTime;
            eastBool = false;
        }

        // Kalan süreyi azalt
        eastTimer.setText(String.valueOf(this.eastRemainingTime));
        this.eastRemainingTime--;

        // Süre sıfırlandığında bir sonraki faza geç
        if (this.eastRemainingTime <= 0) {
            this.eastCurrentIndex = (this.eastCurrentIndex + 1) % times.length;
            this.eastRemainingTime = times[this.eastCurrentIndex];
        }
    }

    private void southControl(int[] times,int firstTime) {
        // Renk ayarlamaları
        if (this.southCurrentIndex == 0) { // Green
            southTimer.setTextFill(Color.RED);
            TrafficLight.opacity(southRed,southGreen,southYellow);
        } else if (this.southCurrentIndex == 1) { // Yellow
            southTimer.setTextFill(Color.YELLOW);
            TrafficLight.opacity(southYellow,southGreen,southRed);
        } else { // Red
            southTimer.setTextFill(Color.GREEN);
            TrafficLight.opacity(southGreen,southYellow,southRed);
        }

        if (southBool){
            this.southRemainingTime = firstTime;
            southBool= false;
        }

        // Kalan süreyi azalt
        southTimer.setText(String.valueOf(this.southRemainingTime));
        this.southRemainingTime--;

        // Süre sıfırlandığında bir sonraki faza geç
        if (this.southRemainingTime <= 0) {
            this.southCurrentIndex = (this.southCurrentIndex + 1) % times.length;
            this.southRemainingTime = times[this.southCurrentIndex];
        }
    }

    private void westControl(int[] times,int firstTime) {
        // Renk ayarlamaları
        if (this.westCurrentIndex == 0) {
            westTimer.setTextFill(Color.RED);
            TrafficLight.opacity(westRed,westYellow,westGreen);
        } else if (this.westCurrentIndex == 1) {
            westTimer.setTextFill(Color.YELLOW);
            TrafficLight.opacity(westYellow,westGreen,westRed);
        } else {
            westTimer.setTextFill(Color.GREEN);
            TrafficLight.opacity(westGreen,westRed,westYellow);
        }

        if (westBool){
            this.westRemainingTime = firstTime;
            westBool = false;
        }

        // Kalan süreyi azalt
        westTimer.setText(String.valueOf(this.westRemainingTime));
        this.westRemainingTime--;

        // Süre sıfırlandığında bir sonraki faza geç
        if (this.westRemainingTime <= 0) {
            this.westCurrentIndex = (this.westCurrentIndex + 1) % times.length;
            this.westRemainingTime = times[this.westCurrentIndex];
        }
    }
    public void stopTimer(){
        if (timelineInitial){
            timelineInitial = false;
            timeline.stop();
            btnPause.setText("Devam");
        }
        else{
            timelineInitial = true;
            timeline.play();
            btnPause.setText("Durdur");
        }
    }

    public void resetTimer(){
        timeline.stop();
        northCurrentIndex = 0;
        eastCurrentIndex = 0;
        southCurrentIndex = 0;
        westCurrentIndex = 0;
        northRemainingTime = northTimes[northCurrentIndex];
        eastRemainingTime = eastFirstRedTime;
        southRemainingTime = southFirstRedTime;
        westRemainingTime = westFirstRedTime;

        timeline.playFromStart();
    }
}

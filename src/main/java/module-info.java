module edu.erciyes.trafficsimulation {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.erciyes.trafficsimulation to javafx.fxml;
    exports edu.erciyes.trafficsimulation;
}
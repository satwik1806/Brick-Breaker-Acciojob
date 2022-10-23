module com.example.brickbreaker_acciojob {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.brickbreaker_acciojob to javafx.fxml;
    exports com.example.brickbreaker_acciojob;
}
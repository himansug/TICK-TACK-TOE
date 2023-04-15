module com.example.ticktacktoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ticktacktoe to javafx.fxml;
    exports com.example.ticktacktoe;
}
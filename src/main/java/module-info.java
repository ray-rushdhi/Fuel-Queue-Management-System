module com.example.task4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.task4 to javafx.fxml;
    exports com.example.task4;
}
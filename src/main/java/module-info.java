module com.example.carbajalgonzalez_david_recuperacion2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.carbajalgonzalez_david_recuperacion2.controller to javafx.fxml;
    opens com.example.carbajalgonzalez_david_recuperacion2.model to javafx.fxml, javafx.base;

    exports com.example.carbajalgonzalez_david_recuperacion2;
}

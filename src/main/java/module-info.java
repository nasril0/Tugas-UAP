module org.example.uaptest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.uaptest to javafx.fxml;
    exports org.example.uaptest;
}
module com.example.dungeon {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.xml;
    requires java.desktop;

    //opens java to javafx.fxml;
    //exports java;
    exports MainMenu;
    opens MainMenu to javafx.fxml;
}
module at.zombi.shooter {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.zombi.shooter to javafx.fxml;
    exports at.zombi.shooter;
    exports at.zombi.shooter.controller;
    opens at.zombi.shooter.controller to javafx.fxml;
}
module at.zombi.shooter {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.zombi.shooter to javafx.fxml;
    exports at.zombi.shooter;
    exports at.zombi.shooter.scene;
    opens at.zombi.shooter.scene to javafx.fxml;
}
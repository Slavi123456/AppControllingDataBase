package myApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;
import myApp.DbConnector;

public class dataBaseApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root
                = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/resources/fxml/dbApp.fxml")));

        Scene scene = new Scene(root);

        // Set the Window title
        stage.setTitle("Data base controller");
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

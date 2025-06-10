package myApp.controller.CreateMenu;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import myApp.DBConnectionManager;

import java.io.IOException;


public class CreateMenuController {

    @FXML
    private StackPane CreateMenuDefault;

    @FXML
    private Label errorLabel;

    @FXML
    private StackPane paneForType;

    @FXML
    private ComboBox<String> typeSelector;

    public void initialize() {
        typeSelector.setItems(FXCollections.observableArrayList("Cave", "Rock formation", "Plant", "Reserve", "Swamp"));
        //from here i will be loading the region combobox
    }

    @FXML
    void handleChangeType(ActionEvent event) throws IOException {
        String selectedRole = typeSelector.getValue();
        Node form = null;

        switch (selectedRole) {
            case "Cave":
                form = FXMLLoader.load(getClass().getResource("/resources/fxml/createMenu/createMenuCave.fxml"));
                break;
            case "Swamp":
                form = FXMLLoader.load(getClass().getResource("/resources/fxml/createMenu/createMenuSwamp.fxml"));
                break;
            case "Plant":
                form = FXMLLoader.load(getClass().getResource("/resources/fxml/createMenu/createMenuPlant.fxml"));
                break;
            case "Rock formation":
                form = FXMLLoader.load(getClass().getResource("/resources/fxml/createMenu/createMenuRockFormation.fxml"));
                break;
            case "Reserve":
                form = FXMLLoader.load(getClass().getResource("/resources/fxml/createMenu/createMenuReserve.fxml"));
                break;
        }

        paneForType.getChildren().setAll(form);
    }

    @FXML
    void SubmitNewDataToDatabase(ActionEvent event) {
        String stmnt = "";
        String region = "Burgas";
        String title = "Governor";
        String governor = "Plamen Yanev";

        try {
            stmnt = " INSERT INTO FN1MI0700208.REGION (name, district_governor, regional_title)"
                    + " VALUES ('" + region + "','" + governor + "','" + title + "')";

            DBConnectionManager manager = DBConnectionManager.getInstance();
            manager.insert(stmnt);
        }
        catch (RuntimeException e) {
            errorLabel.setText(e.getMessage());
        }
    }
}



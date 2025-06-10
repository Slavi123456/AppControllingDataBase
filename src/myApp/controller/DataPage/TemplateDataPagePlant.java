package myApp.controller.DataPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import myApp.utils.dataTypes.DataBase;
import myApp.utils.dataTypes.DataPlant;

public class TemplateDataPagePlant {

    @FXML
    private Label familyInfo;

    @FXML
    private Label genusInfo;

    @FXML
    private ImageView imgOfPlant;

    @FXML
    private Label orderInfo;

    @FXML
    private Label plantName;
    private DataPlant dataPlant;
    @FXML
    void bttnForEdit(ActionEvent event) {

    }


    private void setFields() {
        plantName.setText(dataPlant.getName());
        orderInfo.setText(dataPlant.getOrder());
        genusInfo.setText(dataPlant.getGenus());
        familyInfo.setText(dataPlant.getFamily());
    }
    public void setReserve(DataBase plant) {
        if (plant == null) { return;} //or throw
        if (plant instanceof DataPlant) {
            this.dataPlant = (DataPlant) plant;
            setFields();
        }
        else {
            //throw ...
        }
    }

}


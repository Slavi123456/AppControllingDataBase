package myApp.controller.DataPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import myApp.utils.dataTypes.DataBase;
import myApp.utils.dataTypes.DataRockFormation;
import myApp.utils.dataTypes.DataSwamp;

import java.time.format.DateTimeFormatter;

public class TemplateDataPageSwamp {

    @FXML private ImageView imgOfPlant;
    @FXML private Label swampAltitude;
    @FXML private Label swampLenght;
    @FXML private Label swampName;
    @FXML private Label swampWidth;
    private DataSwamp dataSwamp;

    @FXML
    void bttnForEdit(ActionEvent event) {

    }


    private void setFields() {
        swampName.setText(dataSwamp.getName());
        swampLenght.setText(String.format("%d", dataSwamp.getAltitude()));
        swampAltitude.setText(String.format("%d", dataSwamp.getAltitude()));
        swampWidth.setText(String.format("%d", dataSwamp.getWidth()));
    }
    public void setReserve(DataBase dataSwamp) {
        if (dataSwamp == null) { return;}
        if (dataSwamp instanceof DataSwamp) {
            this.dataSwamp = (DataSwamp) dataSwamp;
            setFields();
        }
        else {
            //throw ...
        }
    }

}
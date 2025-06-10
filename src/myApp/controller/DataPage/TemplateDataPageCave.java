package myApp.controller.DataPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import myApp.utils.dataTypes.DataBase;
import myApp.utils.dataTypes.DataCave;
import myApp.utils.dataTypes.DataReserve;

import java.time.format.DateTimeFormatter;

public class TemplateDataPageCave {

    @FXML private Label caveDate_of_discovary;
    @FXML private Label caveHeight;
    @FXML private Label caveHumidity;
    @FXML private Label caveDepth;
    @FXML private Label caveName;
    @FXML private Label caveTemperature;
    @FXML private ImageView imgOfPlant;
    private DataCave dataCave;
    @FXML
    void bttnForEdit(ActionEvent event) {

    }

    private void setFields() {
        caveName.setText(dataCave.getName());
        caveHeight.setText(String.format("%d", dataCave.getHeight()));
        caveDate_of_discovary.setText(dataCave.getDate_of_discovary().format(DateTimeFormatter.ISO_LOCAL_DATE));
        caveHumidity.setText(String.format("%d", dataCave.getHumidity()));
        caveDepth.setText(String.format("%d", dataCave.getDepth()));
        caveTemperature.setText(String.format("%d", dataCave.getTemperature()));
    }
    public void setReserve(DataBase dataCave) {
        if (dataCave == null) { return;}
        if (dataCave instanceof DataCave) {
            this.dataCave = (DataCave) dataCave;
            setFields();
        }
        else {
            //throw ...
        }
    }

}


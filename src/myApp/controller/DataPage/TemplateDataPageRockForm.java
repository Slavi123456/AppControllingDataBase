package myApp.controller.DataPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import myApp.utils.dataTypes.DataBase;
import myApp.utils.dataTypes.DataReserve;
import myApp.utils.dataTypes.DataRockFormation;

import java.time.format.DateTimeFormatter;

public class TemplateDataPageRockForm {

    @FXML
    private ImageView imgOfPlant;

    @FXML
    private Label rockFormArea;

    @FXML
    private Label rockFormDate_of_Discovary;

    @FXML
    private Label rockFormName;
    private DataRockFormation dataRockFormation;
    @FXML
    void bttnForEdit(ActionEvent event) {

    }

    private void setFields() {
        rockFormName.setText(dataRockFormation.getName());
        rockFormArea.setText(String.format("%d", dataRockFormation.getArea()));
        rockFormDate_of_Discovary.setText(dataRockFormation.getDate_of_creation().format(DateTimeFormatter.ISO_LOCAL_DATE));

    }
    public void setReserve(DataBase rockFormation) {
        if (rockFormation == null) { return;}
        if (rockFormation instanceof DataRockFormation) {
            this.dataRockFormation = (DataRockFormation) rockFormation;
            setFields();
        }
        else {
            //throw ...
        }
    }

}


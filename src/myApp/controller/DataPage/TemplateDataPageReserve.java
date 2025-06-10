package myApp.controller.DataPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import myApp.utils.dataTypes.DataBase;
import myApp.utils.dataTypes.DataReserve;

import java.time.format.DateTimeFormatter;


public class TemplateDataPageReserve {
    private DataReserve reserveData;
    @FXML private ImageView imgOfPlant;
    @FXML private Label reserveArea;
    @FXML private Label reserveName;
    @FXML private Label reserve_date_of_discovary;

    @FXML
    public void bttnForEdit(ActionEvent actionEvent) {
    }
    private void setFields() {
        reserveName.setText(reserveData.getName());
        reserveArea.setText(String.format("%d", reserveData.getArea()));
        reserve_date_of_discovary.setText(reserveData.getDate_of_creation().format(DateTimeFormatter.ISO_LOCAL_DATE));

    }
    public void setReserve(DataBase reserve) {
        if (reserve == null) { return;}
        if (reserve instanceof DataReserve) {
            this.reserveData = (DataReserve) reserve;
            setFields();
        }
        else {
            //throw ...
        }
    }

}

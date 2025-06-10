package myApp.controller.selectPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import myApp.utils.dataTypes.DataBase;
import myApp.utils.dataTypes.DataCave;
import myApp.utils.dataTypes.DataPlant;
import myApp.utils.offers.AttractionOffer;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SelectMenu {
    @FXML private TableColumn<AttractionOffer, String> TableColumnName;
    @FXML private Label errorNameLabel;
    @FXML private TableView<AttractionOffer> tableForData;
    @FXML private ComboBox<String> typeAttraction;
    @FXML private TextField nameAttraction;
    @FXML private ComboBox<String> regionForAttraction;
    private ObservableList<AttractionOffer> selectedAttractions = FXCollections.observableArrayList();
    private ObservableList<AttractionOffer> attractionData = FXCollections.observableArrayList();
    private final ObjectProperty<AttractionOffer> selectedOffer = new SimpleObjectProperty<>();
    private Consumer<AttractionOffer> onAttractionSelected;

    public void setOnAttractionSelected(Consumer<AttractionOffer> handler) {
        this.onAttractionSelected = handler;
    }

    //should be refactored
    private void makeNameSelectQuery (String nameToMatch) {
        selectedAttractions.clear(); // clear previous results

        for (AttractionOffer offer : attractionData) {
            if (offer.getName().equals(nameToMatch)) {
                selectedAttractions.add(offer);
            }
        }

        if (selectedAttractions.isEmpty()) {
            errorNameLabel.setText("There is no matching attraction");
        } else {
            errorNameLabel.setText(""); // clear error if found
        }

        // Show only the filtered result
        tableForData.setItems(selectedAttractions);
    }
    private void makeTypeSelectQuery (String typeToMatch) {
        selectedAttractions.clear(); // clear previous results

        for (AttractionOffer offer : attractionData) {
            if (offer.getType().equals(typeToMatch)) {
                selectedAttractions.add(offer);
            }
        }

        if (selectedAttractions.isEmpty()) {
            errorNameLabel.setText("There is no matching attraction");
        } else {
            errorNameLabel.setText(""); // clear error if found
        }

        // Show only the filtered result
        tableForData.setItems(selectedAttractions);
    }
    private void makeNameAndTypeSelect (String nameToMatch,String typeToMatch) {
        selectedAttractions.clear(); // clear previous results

        for (AttractionOffer offer : attractionData) {
            if (offer.getType().equals(typeToMatch) && offer.getName().equals(nameToMatch)) {
                selectedAttractions.add(offer);
            }
        }

        if (selectedAttractions.isEmpty()) {
            errorNameLabel.setText("There is no matching attraction");
        } else {
            errorNameLabel.setText(""); // clear error if found
        }

        // Show only the filtered result
        tableForData.setItems(selectedAttractions);
    }

    @FXML
    void searchForAttractions(ActionEvent event) {
        String attractionName = nameAttraction.getText() == null ? "" : nameAttraction.getText();
        String attractionType = typeAttraction.getSelectionModel().getSelectedItem() == null ? "" : typeAttraction.getSelectionModel().getSelectedItem();

        //for reset
        if (attractionName.trim().isEmpty() && attractionType.trim().isEmpty()) {
            tableForData.setItems(attractionData);
            errorNameLabel.setText("");
            return;
        }
        else if (attractionType.trim().isEmpty() ) {
            makeNameSelectQuery(attractionName);
        } else if (attractionName.trim().isEmpty()) {
            makeTypeSelectQuery(attractionType);
        }else {
            makeNameAndTypeSelect(attractionName,attractionType);
        }


    }
    @FXML
    public void initialize() {
        //initialize the region combo box from the database
        typeAttraction.setItems(FXCollections.observableArrayList("Cave", "Rock formation", "Plant", "Reserve", "Swamp"));
        TableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        //typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        //regionColumn.setCellValueFactory(new PropertyValueFactory<>("region"));

        // Add custom cell factory for special formatting if needed
        /*designationDateColumn.setCellFactory(column -> {
            return new TableCell<AttractionOffer, LocalDate>() {
                @Override
                protected void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    if (empty || date == null) {
                        setText(null);
                    } else {
                        setText(DateTimeFormatter.ofPattern("MMM dd, yyyy").format(date));
                    }
                }
            };
        });
*/
        tableForData.setItems(attractionData);

        setupTableInteractions();
    }
    public void setAttractions(List<DataBase> attractions) {
        attractionData.clear();
        attractions.stream()
                .map(AttractionOffer::new)
                .forEach(attractionData::add);
    }
    private void setupTableInteractions() {

        // Row factory for click handling
        tableForData.setRowFactory(tv -> {
            TableRow<AttractionOffer> row = new TableRow<>();

            // Single click - selection highlight
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    if (event.getClickCount() >= 1) { //is it a problem ">="
                        selectAttraction(row.getItem());
                        onAttractionSelected.accept(selectedOffer.get());
                    }
                }
            });

            // Hover effects
            row.hoverProperty().addListener((obs, wasHovered, isNowHovered) -> {
                if (isNowHovered && !row.isEmpty()) {
                    row.setStyle("-fx-cursor: hand; -fx-background-color: #f5f5f5;");
                } else {
                    row.setStyle("");
                }
            });

            return row;
        });
    }

    private void selectAttraction(AttractionOffer offer) {
        selectedOffer.set(offer);
    }

}

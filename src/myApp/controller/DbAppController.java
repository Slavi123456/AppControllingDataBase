package myApp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import myApp.DBConnectionManager;
import myApp.DbConnector;
import myApp.controller.DataPage.*;
import myApp.controller.homePage.HomePageController;
import myApp.controller.selectPage.SelectMenu;
import myApp.utils.dataTypes.DataBase;
import myApp.utils.offers.AttractionOffer;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

//Singleton ??
public class DbAppController {

    @FXML
    private StackPane menuPane;
    private String currPage = "";
    private List<DataBase> attractions;
    @FXML
    void bttnHome(ActionEvent event) throws IOException {
        handleButtonAction("Home");
    }
    @FXML
    void bttnCreate(ActionEvent event)throws IOException {
        handleButtonAction("Create");
    }
    @FXML
    void bttnSearch(ActionEvent event) throws IOException {
        handleButtonAction("Search");
    }
    @FXML
    public void initialize() {
        // Initialization code here
        try {
            handleButtonAction("Home");
        }catch (IOException e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }


        /// ////////////initializing of the data
        attractions = new ArrayList<DataBase>();
        DBConnectionManager manager = DBConnectionManager.getInstance();
        //manager.initilizeDatabase();//fill the array of data with records
        attractions.addAll(manager.initilizeDatabase());
        attractions.forEach(System.out::println);

    }

    public List<DataBase> getAttractions() {
        return attractions;
    }

    void handleButtonAction(String pageName) throws IOException {
        if (pageName.equals(currPage)) { return; }

        Node form = null;
        switch (pageName) {
            case "Home":
                FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/resources/fxml/homePage/homePage.fxml"));
                form = homeLoader.load();
                break;
            case "Create":
                FXMLLoader createLoader = new FXMLLoader(getClass().getResource("/resources/fxml/createMenu/createMenu.fxml"));
                form = createLoader.load();
                break;
            case "Search":
                FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("/resources/fxml/selectPage/selectMenu.fxml"));
                form = searchLoader.load();
                Object controller = searchLoader.getController();
                // If SearchController needs data
                if (controller instanceof SelectMenu selectMenu) {
                    selectMenu.setAttractions(attractions);
                    selectMenu.setOnAttractionSelected(this::openDataPage);
                }
                break;
        }

        if (form != null) {
            menuPane.getChildren().setAll(form);
            currPage = pageName;
        }
    }

    public void openDataPage(AttractionOffer offer){
        String offerType = offer.getType();
        //System.out.printf("%s\n", offerType );

        //this could be refactored as: making one interface which is implemented by loaders for every class
        Node form = null;
        switch (offerType ) {
            case "Reserve":
                try {
                    FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("/resources/fxml/dataPage/TemplateDataPageReserve.fxml"));
                    form = searchLoader.load();
                    Object controller = searchLoader.getController();

                    if (controller instanceof TemplateDataPageReserve pageReserve) {
                        pageReserve.setReserve(offer.getAttraction());
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                    System.out.printf("Couldnt open Reserve data page");
                };
                break;
            case "Plant":
                try {
                    FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("/resources/fxml/dataPage/TemplateDataPagePlant.fxml"));
                    form = searchLoader.load();
                    Object controller = searchLoader.getController();

                    if (controller instanceof TemplateDataPagePlant pagePlant) {
                        pagePlant.setReserve(offer.getAttraction());
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                    System.out.printf("Couldnt open Plant data page");
                };
                break;
            case "RockFormation":
                try {
                    FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("/resources/fxml/dataPage/TemplateDataPageRockForm.fxml"));
                    form = searchLoader.load();
                    Object controller = searchLoader.getController();

                    if (controller instanceof TemplateDataPageRockForm pageRockForm) {
                        pageRockForm.setReserve(offer.getAttraction());
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                    System.out.printf("Couldnt open Rock Formation data page");
                };
                break;
            case "Swamp":
                try {
                    FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("/resources/fxml/dataPage/TemplateDataPageSwamp.fxml"));
                    form = searchLoader.load();
                    Object controller = searchLoader.getController();

                    if (controller instanceof TemplateDataPageSwamp pageSwamp) {
                        pageSwamp.setReserve(offer.getAttraction());
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                    System.out.printf("Couldnt open Swamp data page");
                };
                break;
            case "Cave":
                try {
                    FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("/resources/fxml/dataPage/TemplateDataPageCave.fxml"));
                    form = searchLoader.load();
                    Object controller = searchLoader.getController();

                    if (controller instanceof TemplateDataPageCave pageCave) {
                        pageCave.setReserve(offer.getAttraction());
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                    System.out.printf("Couldnt open Cave data page");
                };
                break;
            default:
                //throw ...
                break;
        }
        //if (form == null) { throw new IOException(pageName); }
        if (form != null) {
            menuPane.getChildren().setAll(form);
            currPage = "Data";
        }
    }
}

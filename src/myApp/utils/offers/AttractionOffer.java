package myApp.utils.offers;

import myApp.utils.dataTypes.DataBase;
import myApp.utils.dataTypes.DataCave;
import myApp.utils.dataTypes.DataReserve;
import myApp.utils.dataTypes.DataRockFormation;

//this is Offer without fxml
public class AttractionOffer {
    private final DataBase attraction;
    private final String type;

    public AttractionOffer(DataBase attraction) {
        this.attraction = attraction;
        this.type = attraction.getClass().getSimpleName().replace("Data", "");
    }

    public String getName() { return attraction.getName(); }
    //public String getRegion() { return attraction.getRegionName(); }
    //public LocalDate getDesignationDate() { return attraction.getDesignationDate(); }
    public String getType() { return type; }


    public Integer getArea() {
        if (attraction instanceof DataRockFormation) {
            return ((DataRockFormation)attraction).getArea();
        }
        if (attraction instanceof DataReserve) {
            return ((DataReserve)attraction).getArea();
        }
        return null;
    }

    public Integer getTemperature() {
        return (attraction instanceof DataCave) ?
                ((DataCave)attraction).getTemperature() : null;
    }

    public DataBase getAttraction() {
        return attraction;
    }
// Add more getters as needed for your table columns
}

package myApp.utils.dataTypes;

import java.time.LocalDate;

public class DataRockFormation extends DataBase {
    private int area;
    private LocalDate date_of_creation;

    public DataRockFormation(String img_path, String name) {
        super(img_path, name);
        setArea(0);
        setDate_of_creation(LocalDate.now());
    }

    public DataRockFormation(String img_path, String name, int area, LocalDate date_of_creation) {
        super(img_path, name);
        setArea(area);
        setDate_of_creation(date_of_creation);
    }

    public int getArea() {
        return area;
    }
    public LocalDate getDate_of_creation() {
        return date_of_creation;
    }

    public void setArea(int area) {
        if (area >= 0) { this.area = 0; }
        this.area = area;
    }
    public void setDate_of_creation(LocalDate date_of_creation) {
        if (date_of_creation == null) { this.date_of_creation = LocalDate.now(); }
        else {this.date_of_creation = date_of_creation; }
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\n  Rock Formation: Area: %d m², Creation Date: %s",
                this.area,
                this.date_of_creation);
    }
}

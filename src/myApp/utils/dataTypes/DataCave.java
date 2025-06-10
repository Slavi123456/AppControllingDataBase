package myApp.utils.dataTypes;

import java.time.LocalDate;

public class DataCave extends DataBase {
    private int humidity;
    private int temperature;
    private int height;
    private int depth;
    private LocalDate date_of_discovary;

    public DataCave(String img_path, String name) {
        super(img_path, name);
        setHumidity(0);
        setTemperature(0);
        setHeight(0);
        setDepth(0);
        setDate_of_discovary(LocalDate.now());
    }
    public DataCave(String img_path, String name, int humidity, int temperature, int height, int depth, LocalDate date_of_discovary) {
        super(img_path, name);
        setHumidity(humidity);
        setTemperature(temperature);
        setHeight(height);
        setDepth(depth);
        setDate_of_discovary(date_of_discovary);
    }

    public int getHumidity() {
        return humidity;
    }
    public int getTemperature() {
        return temperature;
    }
    public int getHeight() {
        return height;
    }
    public int getDepth() {
        return depth;
    }
    public LocalDate getDate_of_discovary() {
        return date_of_discovary;
    }

    public void setDate_of_discovary(LocalDate date_of_discovary) {
        if (date_of_discovary == null) { this.date_of_discovary = LocalDate.now(); }
        else { this.date_of_discovary = date_of_discovary; }
    }
    public void setHumidity(int humidity) {
        if (humidity < 0 || humidity > 100) { this.humidity = 0; }
        this.humidity = humidity;
    }
    public void setTemperature(int temperature) {
        if (temperature < 0 || temperature > 100) { this.temperature = 0; }
        this.temperature = temperature;
    }
    public void setHeight(int height) {
        if (height < 0) { this.height = 0; }
        this.height = height;
    }
    public void setDepth(int depth) {
        if (depth < 0) { this.depth = 0; }
        this.depth = depth;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\n  Cave Details: Longitude: %d, Elevation: %d, Humidity: %d%%, Temp: %d°C, Discovered: %s",
                this.depth,
                this.height,
                this.humidity,
                this.temperature,
                this.date_of_discovary);
    }
}

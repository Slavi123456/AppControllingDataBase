package myApp.utils.dataTypes;

import com.sun.prism.PixelFormat;

public class DataSwamp extends DataBase {
    private int width;
    private int length;
    private int altitude;
    public DataSwamp(String img_path, String name) {
        super(img_path, name);
        setLength(0);
        setAltitude(0);
        setWidth(0);
    }
    public DataSwamp(String img_path, String name, int width, int length, int altitude) {
        super(img_path, name);
        setWidth(width);
        setLength(length);
        setAltitude(altitude);
    }

    public int getWidth() {
        return width;
    }
    public int getLength() {
        return length;
    }
    public int getAltitude() {
        return altitude;
    }

    public void setWidth(int width) {
        if (width < 0) { this.width = 0; }
        this.width = width;
    }
    public void setLength(int length) {
        if (length < 0) { this.length = 0; }
        this.length = length;
    }
    public void setAltitude(int altitude) {
        if (altitude < 0) { this.altitude = 0; }
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\n  Swamp Details: Longitude: %d, Latitude: %d, Altitude: %d",
                this.width,
                this.length,
                this.altitude);
    }

}

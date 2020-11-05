package sample;

public class Coordinates {
    private double longitude;
    private double latitude;

    public Coordinates(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLon() {
        return longitude;
    }

    public void setLon(double longitude) {
        this.longitude = longitude;
    }

    public double getLat() {
        return latitude;
    }

    public void setLat(double latitude) {
        this.latitude = latitude;
    }
}

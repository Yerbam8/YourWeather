package pl.sda.pk.YourWeather.MVC.location;

public class LocationDTO {
    private String fullName;
    private String fullCoordinate;

    public LocationDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullCoordinate() {
        return fullCoordinate;
    }

    public void setFullCoordinate(String fullCoordinate) {
        this.fullCoordinate = fullCoordinate;
    }
}

package pl.sda.pk.YourWeather.MVC.location;

public class LocationDTO {
    private String fullName;
    private String fullCoordinate;
    private float temp;
    private int pressure;
    private float windSpeed;
    private int humidity;

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

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}

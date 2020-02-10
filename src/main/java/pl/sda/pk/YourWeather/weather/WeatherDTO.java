package pl.sda.pk.YourWeather.weather;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class WeatherDTO {

    private Long id;

    private int temp;
    private int humidity;
    private int pressure;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    private WindDirections windDirection;
    private int windSpeed;
    private String locationName;
    private String locationId;

    public WeatherDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public WindDirections getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(WindDirections windDirection) {
        this.windDirection = windDirection;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}

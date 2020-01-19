package pl.sda.pk.YourWeather.weather;

import pl.sda.pk.YourWeather.location.Location;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Max(60)
    @Min(-60)
    private int temp;

    @Min(0)
    @Max(100)
    private int humidity;

    @Min(900)
    @Max(1100)
    private int pressure;

    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$")
    private String date;

    @Column(name = "wind_directions")
    private WindDirections windDirections;

    @Min(0)
    @Max(300)
    @Column(name = "wind_speed")
    private int windSpeed;

    @ManyToOne
    private Location location;

    public Weather() {
    }

    public Long getId() {
        return id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public WindDirections getWindDirections() {
        return windDirections;
    }

    public void setWindDirections(WindDirections windDirections) {
        this.windDirections = windDirections;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return id == weather.id &&
                temp == weather.temp &&
                humidity == weather.humidity &&
                pressure == weather.pressure &&
                windSpeed == weather.windSpeed &&
                Objects.equals(date, weather.date) &&
                windDirections == weather.windDirections &&
                Objects.equals(location, weather.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, temp, humidity, pressure, date, windDirections, windSpeed, location);
    }
}

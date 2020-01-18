package pl.sda.pk.YourWeather.weather;

import org.hibernate.annotations.GenericGenerator;
import pl.sda.pk.YourWeather.location.Location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
public class Weather {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

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

    private WindDirections windDirections;

    @Min(0)
    @Max(300)
    private int windSpeed;


    public Weather() {
    }

    public String getId() {
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
}
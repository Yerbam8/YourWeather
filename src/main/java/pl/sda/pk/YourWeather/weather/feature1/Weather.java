package pl.sda.pk.YourWeather.weather.feature1;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Weather {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Max(60)
    @Min(-60)
    private int temp;

    @NotNull
    @Min(0)
    @Max(100)
    private int humidity;

    @NotNull
    @Min(1013)
    @Max(1100)
    private int pressure;

    @NotNull
    private WindDirections windDirections;


    @NotNull
    private int windSpeed;

    public Weather() {
    }

    public Weather(@NotNull @Max(60) @Min(-60) @NotEmpty int temp, @NotNull @Min(0) @Max(100) @NotEmpty int humidity,
                   @NotNull @Min(1013) @Max(1100) @NotEmpty int pressure, @NotNull @NotEmpty WindDirections windDirections,
                   @NotEmpty @NotNull int windSpeed) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windDirections = windDirections;
        this.windSpeed = windSpeed;
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

    public WindDirections getWindDirections() {
        return windDirections;
    }

    public void setWindDirections(WindDirections windDirections) {
        this.windDirections = windDirections;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }
}

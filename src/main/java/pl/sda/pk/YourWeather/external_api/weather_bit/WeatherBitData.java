package pl.sda.pk.YourWeather.external_api.weather_bit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class WeatherBitData {

    private long lon;
    private long lat;
    private long pres;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("city_name")
    private String cityName;
    @JsonProperty("wind_spd")
    private long windSpeed;
    private long temp;
    @JsonProperty("rh")
    private long humidity;
    @JsonProperty("ob_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime localDate;

    public long getLon() {
        return lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getPres() {
        return pres;
    }

    public void setPres(long pres) {
        this.pres = pres;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(long windSpeed) {
        this.windSpeed = windSpeed;
    }

    public long getTemp() {
        return temp;
    }

    public void setTemp(long temp) {
        this.temp = temp;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDateTime localDate) {
        this.localDate = localDate;
    }
}

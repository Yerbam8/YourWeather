package pl.sda.pk.YourWeather.location;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Primary;
import pl.sda.pk.YourWeather.weather.Weather;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Location {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @NotEmpty
    @Column(name = "city_name")
    private String cityName;

    @Size(min = 2)
    @NotNull
    private String region;

    @Min(-90)
    @Max(90)
    private float latitude;

    @Min(-180)
    @Max(180)
    private float longitude;

    @NotNull
    @NotEmpty
    @Column(name = "country_name")
    private String countryName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Weather> weathers;

    public Location() {
    }


    public Location(@NotNull @NotEmpty String cityName, @Size(min = 2) @NotNull String region, @Min(-90) @Max(90) float latitude,
                    @Min(-180) @Max(180) float longitude, @NotNull @NotEmpty String countryName, List<Weather> weathers) {

        this.cityName = cityName;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
        this.countryName = countryName;
        this.weathers = weathers;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Float.compare(location.latitude, latitude) == 0 &&
                Float.compare(location.longitude, longitude) == 0 &&
                Objects.equals(id, location.id) &&
                Objects.equals(cityName, location.cityName) &&
                Objects.equals(region, location.region) &&
                Objects.equals(countryName, location.countryName);
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cityName, region, latitude, longitude, countryName);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", cityName='" + cityName + '\'' +
                ", region='" + region + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}

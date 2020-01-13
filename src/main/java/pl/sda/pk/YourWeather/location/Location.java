package pl.sda.pk.YourWeather.location;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class Location {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @NotNull
    @NotEmpty
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
    private String countryName;

    public Location() {
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

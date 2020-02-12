package pl.sda.pk.YourWeather.MVC.location;

import org.springframework.stereotype.Component;

@Component
public class LocationDTOTransformer {

    public LocationDTO toLocationDTO(Location location){
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setFullName(location.getCityName()+", "+location.getRegion());
        locationDTO.setFullCoordinate(location.getLatitude()+", "+location.getLongitude());
        return  locationDTO;
    }

    //TODO: połączyc dwa api tutaj

}

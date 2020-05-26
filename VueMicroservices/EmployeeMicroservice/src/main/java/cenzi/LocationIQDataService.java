package cenzi;

import cenzi.exceptions.LocationBadRequestException;
import cenzi.exceptions.LocationNotFoundException;
import cenzi.locationiq.AddressResource;
import cenzi.locationiq.LocationResource;
import cenzi.locationiq.LonLat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class LocationIQDataService {
    private static final String KEY = "d8e41550e67d61";

    public LonLat getLonLat(String address) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<>();

        vars.put("searchString", address);
        vars.put("key", KEY);

        try {
            ResponseEntity<LocationResource[]> response = restTemplate.getForEntity("https://eu1.locationiq.com/v1/search.php?key={key}&q={searchString}&format=json", LocationResource[].class, vars);
            LonLat result = new LonLat();
            LocationResource[] locations = response.getBody();

            result.setLon(locations[0].getLon());
            result.setLat(locations[0].getLat());

            return result;
        } catch (RestClientResponseException e) {

            if (e.getRawStatusCode() == 400) {
                throw new LocationNotFoundException("Location wurde nicht gefunden");
            } else {
                throw new LocationBadRequestException(e.getResponseBodyAsString());
            }

        }
    }
}

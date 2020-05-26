package cenzi;

import cenzi.exceptions.ServiceDataServiceException;
import cenzi.exceptions.ServiceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceDataService {
    public void deleteServiceByEmployee(int id) throws ServiceNotFoundException, ServiceDataServiceException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<>();

        vars.put("id", "" + id);

        try{
            restTemplate.delete("http://localhost:6969/services/employees/{id}", vars);
        } catch (RestClientResponseException e) {

            if(e.getRawStatusCode() == 400) {
                throw new ServiceNotFoundException("Service mit Mitarbeiter (" + id + ") wurde nicht gefunden");
            } else {
                throw new ServiceDataServiceException(e.getResponseBodyAsString());
            }

        }
    }
}

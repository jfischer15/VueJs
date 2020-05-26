package cenzi;

import cenzi.employee.EmployeeResource;
import cenzi.exceptions.EmployeeDataServiceException;
import cenzi.exceptions.EmployeeNotFoundException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class EmpDataService {


    public EmployeeResource getById(int id)
            throws EmployeeNotFoundException, EmployeeDataServiceException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<>();

        vars.put("id", id + "");

        try{
            EmployeeResource res = restTemplate.getForObject("http://localhost:1871/employees/{id}",
                    EmployeeResource.class, vars);

            return res;
        } catch (RestClientResponseException e) {

            if(e.getRawStatusCode() == 400) {
                throw new EmployeeNotFoundException("Employee mit der Id (" + id + ") wurde nicht gefunden");
            } else {
                throw new EmployeeDataServiceException(e.getResponseBodyAsString());
            }

        }
    }


}



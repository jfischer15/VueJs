package cenzi;

import cenzi.service.ServiceDto;
import cenzi.service.ServiceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("services")
public class Controller {
    @Autowired
    private ServiceDataService serviceDataService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<ServiceResource>> getAll() {
        return new HttpEntity<>(serviceDataService.getAll());
    }

    @RequestMapping(value = "employees/{id}", method = RequestMethod.DELETE)
    public HttpEntity<List<ServiceResource>> deleteServiceOfEmployee(@PathVariable Integer id) {
        return new HttpEntity<>(serviceDataService.deleteServiceOfEmployeeWithId(id));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ServiceResource delete(@PathVariable Integer id) {
        return serviceDataService.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ServiceResource edit(@PathVariable Integer id, @RequestBody ServiceDto serviceDto) {
        return serviceDataService.edit(id, serviceDto);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ServiceResource get(@PathVariable Integer id) {
        return serviceDataService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ServiceResource add(@RequestBody ServiceDto serviceDto) {
        return serviceDataService.add(serviceDto);
    }
}

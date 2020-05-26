package cenzi;

import cenzi.employee.EmployeeDto;
import cenzi.employee.EmployeeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("employees")
public class Controller {
    @Autowired
    private EmpDataService empDataService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<EmployeeResource>> getAllEmployees() {
        return new HttpEntity<>(empDataService.getAll());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public EmployeeResource edit(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {
        return empDataService.edit(id, employeeDto);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public EmployeeResource getById(@PathVariable Integer id) {
        return empDataService.getById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public EmployeeResource delete(@PathVariable Integer id) {
        return empDataService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public EmployeeResource add(@RequestBody EmployeeDto employeeDto) {
        return empDataService.add(employeeDto);
    }
}

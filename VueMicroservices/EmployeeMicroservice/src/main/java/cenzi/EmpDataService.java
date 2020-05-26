package cenzi;

import cenzi.employee.Employee;
import cenzi.employee.EmployeeDto;
import cenzi.employee.EmployeeResource;
import cenzi.exceptions.EmployeeBadRequestException;
import cenzi.locationiq.LonLat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EmpDataService {
    @Autowired
    private Manager manager;
    
    @Autowired
    private LocationIQDataService locationIQDataService;
    
    @Autowired
    private ServiceDataService serviceDataService;

    private Employee empDto2Emp(EmployeeDto employeeDto) {
        Employee e = new Employee();
        e.setId(-1);
        LonLat lonlat = locationIQDataService.getLonLat(employeeDto.getAddress());
        e.setLon(lonlat.getLon());
        e.setLat(lonlat.getLat());
        e.setVorname(employeeDto.getForename());
        e.setAddress(employeeDto.getAddress());
        e.setNachname(employeeDto.getSurname());
        return e;
    }

    public List<EmployeeResource> getAll() {
        List<EmployeeResource> result = new ArrayList<>();
        for (Employee e : manager.getAll()) {
            result.add(emp2EmpResource(e));
        }
        return result;
    }

    private void checkEmployeeDto(EmployeeDto employeeDto) {
        if(isNullOrEmpty(employeeDto.getForename())) {
            throw new EmployeeBadRequestException("Vorname muss gesetzt sein");
        }
        if(employeeDto.getForename().length() < 5) {
            throw new EmployeeBadRequestException("Vorname muss länger als 4 sein");
        }
        if(isNullOrEmpty(employeeDto.getSurname())) {
            throw new EmployeeBadRequestException("Nachname muss gesetzt sein");
        }
        if(employeeDto.getSurname().length() < 5) {
            throw new EmployeeBadRequestException("Nachname muss länger als 4 sein");
        }
        if(isNullOrEmpty(employeeDto.getAddress())) {
            throw new EmployeeBadRequestException("Adresse muss gesetzt sein");
        }
    }

    public EmployeeResource edit(Integer id, EmployeeDto dto) {
        checkEmployeeDto(dto);
        Employee emp = manager.edit(id, empDto2Emp(dto));
        return emp2EmpResource(emp);
    }

    public EmployeeResource delete(Integer id) {
        serviceDataService.deleteServiceByEmployee(id);
        Employee emp = manager.delete(id);
        return emp2EmpResource(emp);
    }

    public EmployeeResource getById(Integer id) {
        return emp2EmpResource(manager.getById(id));
    }

    public EmployeeResource add(EmployeeDto dto) {
        checkEmployeeDto(dto);
        Employee emp = manager.add(empDto2Emp(dto));
        return emp2EmpResource(emp);
    }

    private EmployeeResource emp2EmpResource(Employee employee) {
        EmployeeResource result = new EmployeeResource();
        result.setId(employee.getId());
        result.setLatitude(employee.getLat());
        result.setName(employee.getNachname()+" "+employee.getVorname());
        result.setAddress(employee.getAddress());
        result.setLongitude(employee.getLon());
        return result;
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.equals("");
    }
}

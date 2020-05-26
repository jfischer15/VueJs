package cenzi;

import cenzi.employee.Employee;
import cenzi.employee.EmployeeEntity;
import cenzi.exceptions.EmployeeEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Manager {
    @Autowired
    private Repo repo;

    public Employee add(Employee employee) {
        EmployeeEntity storedEntity = repo.save(emp2EmpEntity(employee));
        return empEntity2Emp(storedEntity);
    }

    public Employee getById(Integer employeeId) {
        Optional<EmployeeEntity> foundEntity = repo.findById(employeeId);
        if(!foundEntity.isPresent()) throw new EmployeeEntityNotFoundException(
                String.format("Employee mit der Id: %d existiert nicht!", employeeId));
        return empEntity2Emp(foundEntity.get());
    }

    public Employee edit(Integer employeeId, Employee newEmployee) {
        Employee employee = getById(employeeId);
        employee.setLat(newEmployee.getLat());
        employee.setLon(newEmployee.getLon());
        employee.setVorname(newEmployee.getVorname());
        employee.setNachname(newEmployee.getNachname());
        employee.setAddress(newEmployee.getAddress());

        EmployeeEntity storedEntity = repo.save(emp2EmpEntity(employee));
        return empEntity2Emp(storedEntity);
    }

    private EmployeeEntity emp2EmpEntity(Employee employee) {
        EmployeeEntity result = new EmployeeEntity();
        if(employee.getId() != -1) result.setId(employee.getId());
        result.setVorname(employee.getVorname());
        result.setLon(employee.getLon());
        result.setAddress(employee.getAddress());
        result.setNachname(employee.getNachname());
        result.setLat(employee.getLat());
        return result;
    }

    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();
        for(EmployeeEntity employeeEntity : repo.findAll()) {
            result.add(empEntity2Emp(employeeEntity));
        }
        return result;
    }

    public Employee delete(Integer employeeId) {
        Optional<EmployeeEntity> emp = repo.findById(employeeId);
        if(!emp.isPresent()) {
            throw new EmployeeEntityNotFoundException(String.format("Employee mit der Id: %d existiert nicht!", employeeId));
        }
        EmployeeEntity entity = emp.get();
        repo.delete(entity);
        return empEntity2Emp(entity);
    }

    private Employee empEntity2Emp(EmployeeEntity employeeEntity) {
        Employee result = new Employee();
        result.setId(employeeEntity.getId());
        result.setVorname(employeeEntity.getVorname());
        result.setLon(employeeEntity.getLon());
        result.setAddress(employeeEntity.getAddress());
        result.setNachname(employeeEntity.getNachname());
        result.setLat(employeeEntity.getLat());
        return result;
    }
}

package cenzi;

import cenzi.employee.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface Repo extends CrudRepository<EmployeeEntity, Integer> {
}

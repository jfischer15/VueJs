package cenzi;

import cenzi.service.ServiceEntity;
import org.springframework.data.repository.CrudRepository;

public interface Repo extends CrudRepository<ServiceEntity, Integer> {
}

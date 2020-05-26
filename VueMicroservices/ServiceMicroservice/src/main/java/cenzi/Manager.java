package cenzi;

import cenzi.exceptions.ServiceEntityNotFoundException;
import cenzi.service.Service;
import cenzi.service.ServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Manager {
    @Autowired
    private Repo repo;

    public List<Service> getAll() {
        List<Service> list = new ArrayList<>();

        for (ServiceEntity entity : repo.findAll()) {
            Service service = serviceEntity2Service(entity);
            list.add(service);
        }

        return list;
    }

    public Service delete(Integer id) {
        Optional<ServiceEntity> entity = repo.findById(id);

        if(!entity.isPresent()) {
            throw new ServiceEntityNotFoundException("Service mit der Id " + id + " existiert nicht");
        }

        ServiceEntity serviceEntity = entity.get();
        repo.delete(serviceEntity);
        return serviceEntity2Service(serviceEntity);
    }

    public Service edit(Integer id, Service newService) {
        Optional<ServiceEntity> entity = repo.findById(id);

        if(!entity.isPresent()) {
            throw new ServiceEntityNotFoundException("Service mit der Id " + id + " existiert nicht");
        }

        ServiceEntity serviceEntity = entity.get();
        Service service = serviceEntity2Service(serviceEntity);

        service.setName(newService.getName());
        service.setAddress(newService.getAddress());
        service.setEmployeeId(newService.getEmployeeId());
        service.setDate(newService.getDate());
        service.setLongitude(newService.getLongitude());
        service.setLatitude(newService.getLatitude());

        serviceEntity = service2ServiceEntity(service);
        ServiceEntity newEntity = repo.save(serviceEntity);
        return serviceEntity2Service(newEntity);
    }

    public Service get(Integer id) {
        Optional<ServiceEntity> entity = repo.findById(id);

        if(!entity.isPresent()) {
            throw new ServiceEntityNotFoundException("Service mit der Id " + id + " existiert nicht");
        }

        ServiceEntity serviceEntity = entity.get();
        return serviceEntity2Service(serviceEntity);
    }

    public Service add(Service service) {
        ServiceEntity serviceEntity = service2ServiceEntity(service);
        ServiceEntity entity = repo.save(serviceEntity);
        return serviceEntity2Service(entity);
    }

    public List<Service> deleteServiceOfEmployeeWithId(Integer id) {
        List<Service> list = new ArrayList<>();
        for(Service service : getAll()) {
            if(service.getEmployeeId() == id) {
                list.add(service);
                delete(service.getId());
            }
        }
        return list;
    }

    private Service serviceEntity2Service(ServiceEntity serviceEntity) {
        Service service = new Service();
        service.setId(serviceEntity.getId());
        service.setName(serviceEntity.getName());
        service.setEmployeeId(serviceEntity.getEmployeeId());
        service.setAddress(serviceEntity.getAddress());
        service.setDate(serviceEntity.getDate());
        service.setLongitude(serviceEntity.getLongitude());
        service.setLatitude(serviceEntity.getLatitude());
        return service;
    }

    private ServiceEntity service2ServiceEntity(Service service) {
        ServiceEntity serviceEntity = new ServiceEntity();
        if(service.getId() != -1) serviceEntity.setId(service.getId());
        serviceEntity.setName(service.getName());
        serviceEntity.setDate(service.getDate());
        serviceEntity.setAddress(service.getAddress());
        serviceEntity.setEmployeeId(service.getEmployeeId());
        serviceEntity.setLongitude(service.getLongitude());
        serviceEntity.setLatitude(service.getLatitude());
        return serviceEntity;
    }
}

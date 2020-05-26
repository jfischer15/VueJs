package cenzi;

import cenzi.exceptions.ServiceBadRequestException;
import cenzi.exceptions.ServiceNotFoundException;
import cenzi.locationiq.LonLat;
import cenzi.service.Service;
import cenzi.service.ServiceDto;
import cenzi.service.ServiceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ServiceDataService {
    @Autowired
    private LocationIQDataService locationIQDataService;
    @Autowired
    private Manager manager;
    @Autowired
    private EmpDataService empDataService;

    public List<ServiceResource> getAll() {
        List<ServiceResource> list = new ArrayList<>();
        for (Service service : manager.getAll()) {
            ServiceResource serviceResource = service2ServiceResource(service);
            list.add(serviceResource);
        }
        return list;
    }

    private ServiceResource service2ServiceResource(Service service) {
        ServiceResource serviceResource = new ServiceResource();
        serviceResource.setId(service.getId());
        serviceResource.setName(service.getName());
        serviceResource.setEmployee(empDataService.getById(service.getEmployeeId()));
        serviceResource.setDate(service.getDate());
        serviceResource.setAddress(service.getAddress());
        serviceResource.setLongitude(service.getLongitude());
        serviceResource.setLatitude(service.getLatitude());
        return serviceResource;
    }

    public List<ServiceResource> deleteServiceOfEmployeeWithId(Integer id) {
        List<ServiceResource> list = new ArrayList<>();
        for (Service service : manager.deleteServiceOfEmployeeWithId(id)) {
            ServiceResource serviceResource = service2ServiceResource(service);
            list.add(serviceResource);
        }
        return list;
    }

    public ServiceResource edit(Integer id, ServiceDto serviceDto) {
        validateDto(serviceDto);
        Service service = serviceDto2Service(serviceDto);
        Service editedService = manager.edit(id, service);
        return service2ServiceResource(editedService);
    }

    public ServiceResource delete(Integer id) {
        Service service = manager.delete(id);
        return service2ServiceResource(service);
    }

    public ServiceResource add(ServiceDto serviceDto) {
        validateDto(serviceDto);
        Service service = manager.add(serviceDto2Service(serviceDto));
        return service2ServiceResource(service);
    }

    public ServiceResource get(Integer id) {
        Service service = manager.get(id);
        return service2ServiceResource(service);
    }

    private Service serviceDto2Service(ServiceDto serviceDto) {
        LonLat lonlat = locationIQDataService.getLonLat(serviceDto.getAddress());

        Service service = new Service();
        service.setName(serviceDto.getName());
        service.setEmployeeId(serviceDto.getEmployeeId());
        service.setAddress(serviceDto.getAddress());
        service.setDate(serviceDto.getDate());
        service.setLongitude(lonlat.getLon());
        service.setLatitude(lonlat.getLat());
        return service;
    }

    private void validateDto(ServiceDto dto) {
        if(isNullOrEmpty(dto.getName())) {
            throw new ServiceBadRequestException("Name darf nicht leer sein");
        }
        if(dto.getName().length() <= 4) {
            throw new ServiceBadRequestException("Name muss länger als 4 sein");
        }
        if(isNullOrEmpty(dto.getDate())) {
            throw new ServiceBadRequestException("Datum darf nicht leer sein");
        }
        if(!isThisDateValid(dto.getDate())) {
            throw new ServiceNotFoundException("Datum muss dem Format dd.MM.yyyy hh:mm entsprechen");
        }
        if(isNullOrEmpty(dto.getAddress())) {
            throw new ServiceBadRequestException("Adresse darf nicht leer sein");
        }
        if(dto.getAddress().length() <= 5) {
            throw new ServiceBadRequestException("Adresse muss länger als 5 sein");
        }
    }

    private boolean isThisDateValid(String dateToValidate){

        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);

        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.equals("");
    }
}

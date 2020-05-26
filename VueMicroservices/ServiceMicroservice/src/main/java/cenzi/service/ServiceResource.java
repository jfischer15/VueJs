package cenzi.service;

import cenzi.employee.EmployeeResource;
import lombok.Data;

@Data
public class ServiceResource {
    private int id;
    private String name;
    private String address;
    private String date;
    private String longitude;
    private String latitude;
    private EmployeeResource employee;
}



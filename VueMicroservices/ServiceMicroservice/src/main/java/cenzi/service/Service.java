package cenzi.service;

import lombok.Data;

@Data
public class Service {
    private int id;
    private String name;
    private String address;
    private String date;
    private String latitude;
    private String longitude;
    private int employeeId;
}

package cenzi.employee;

import lombok.Data;

@Data
public class Employee {
    private int id;
    private String vorname;
    private String nachname;
    private String address;
    private String lon;
    private String lat;
}

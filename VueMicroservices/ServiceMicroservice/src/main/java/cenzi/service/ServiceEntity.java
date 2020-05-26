package cenzi.service;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "services")
@Data
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String address;
    private String date;
    private Integer employeeId;
    private String latitude;
    private String longitude;
}



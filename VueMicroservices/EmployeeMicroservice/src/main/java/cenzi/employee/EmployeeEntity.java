package cenzi.employee;

import lombok.Data;

import javax.persistence.*;

@Table(name="emps")
@Data
@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    private String vorname;
    private String nachname;
    private String address;
    private String lat;
    private String lon;
}

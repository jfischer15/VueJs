package cenzi.locationiq;

import lombok.Data;

@Data
public class Address {
    private String house_number;
    private String road;
    private String hamlet;
    private String village;
    private String county;
    private String state;
    private String postcode;
    private String country;
    private String country_code;
}



package KesmarkiApp.dto;

import KesmarkiApp.domain.enums.StreetType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AddressInfo {

    private Integer id;
    private String country;
    private String postcode;
    private String streetName;
    private StreetType streetType;
    private String streetNumber;
    private List<ContactInfoList> contacts;
    private PersonInfoList person;

}

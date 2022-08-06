package KesmarkiApp.dto;

import KesmarkiApp.domain.Contact;
import KesmarkiApp.domain.Person;
import KesmarkiApp.domain.enums.StreetType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private PersonInfoForAddress person;

}

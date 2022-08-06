package KesmarkiApp.dto;

import KesmarkiApp.domain.enums.StreetType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressCreateCommand {

    private String country;
    private String postcode;
    private String streetName;
    private StreetType streetType;
    private String streetNumber;

}

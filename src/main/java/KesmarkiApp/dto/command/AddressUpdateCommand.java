package KesmarkiApp.dto.command;

import KesmarkiApp.domain.enums.StreetType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressUpdateCommand {

    @NotBlank(message = "must be not blank")
    private String country;

    @NotBlank(message = "must be not blank")
    private String postcode;

    @NotBlank(message = "must be not blank")
    private String streetName;

    @NotNull(message = "must be not null")
    private StreetType streetType;

    @NotBlank(message = "must be not blank")
    private String streetNumber;

}

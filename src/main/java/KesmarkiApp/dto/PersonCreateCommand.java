package KesmarkiApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@NoArgsConstructor
public class PersonCreateCommand {

    @NotBlank(message = "must be not blank")
    private String firstName;

    @NotBlank(message = "must be not blank")
    private String secondName;

    @Past(message = "must be past date")
    private Date dateOfBirth;

    @NotBlank(message = "must be not blank")
    private String nationality;

}

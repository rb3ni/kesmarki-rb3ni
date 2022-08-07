package KesmarkiApp.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonCreateCommand {

    @NotBlank(message = "must be not blank")
    @NotNull(message = "must be not null")
    private String firstName;

    @NotBlank(message = "must be not blank")
    private String secondName;

    @Past(message = "must be past date")
    private LocalDate dateOfBirth;

    @NotBlank(message = "must be not blank")
    private String nationality;

}

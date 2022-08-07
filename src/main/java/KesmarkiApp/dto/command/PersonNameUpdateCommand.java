package KesmarkiApp.dto.command;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PersonNameUpdateCommand {

    @NotBlank(message = "must be not blank")
    private String firstName;

}

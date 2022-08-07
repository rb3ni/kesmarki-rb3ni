package KesmarkiApp.dto.command;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ContactCreateCommand {

    @Email(message = "must be an email")
    private String email;

    @NotBlank(message = "must be not blank")
    private String phoneNumber;

    @Size(max = 255, message = "the size must be between 0 and 255")
    private String otherContactDetail;

}

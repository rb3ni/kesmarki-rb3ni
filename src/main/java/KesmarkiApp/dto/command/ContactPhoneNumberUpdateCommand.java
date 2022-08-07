package KesmarkiApp.dto.command;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ContactPhoneNumberUpdateCommand {

    @NotBlank(message = "must be not blank")
    private String phoneNumber;

}

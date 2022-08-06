package KesmarkiApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactCreateCommand {

    private String email;
    private String phoneNumber;
    private String otherContactDetail;

}

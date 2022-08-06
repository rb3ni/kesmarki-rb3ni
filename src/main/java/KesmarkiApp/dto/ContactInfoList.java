package KesmarkiApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactInfoList {

    private Integer id;
    private String email;
    private String phoneNumber;
    private String OtherContactDetail;

}

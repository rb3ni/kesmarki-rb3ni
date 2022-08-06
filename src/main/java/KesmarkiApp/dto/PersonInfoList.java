package KesmarkiApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PersonInfoList {

    private Integer id;
    private String firstName;
    private String secondName;
    private Date dateOfBirth;
    private String nationality;

}

package KesmarkiApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PersonInfoList {

    private Integer id;
    private String firstName;
    private String secondName;
    private LocalDate dateOfBirth;
    private String nationality;

}

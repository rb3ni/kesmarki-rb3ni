package KesmarkiApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class PersonInfo {

    private Integer id;
    private String firstName;
    private String secondName;
    private LocalDate dateOfBirth;
    private String nationality;
    private List<AddressInfoList> addresses;

}

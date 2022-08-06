package KesmarkiApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PersonInfo {

    private Integer id;
    private String firstName;
    private String secondName;
    private Date dateOfBirth;
    private String nationality;
    private List<AddressInfoList> addresses;

}

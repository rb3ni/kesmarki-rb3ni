package KesmarkiApp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "nationality")
    private String nationality;

    @OneToMany(mappedBy = "person")
    private List<Address> addresses;

    @Column(name = "deleted")
    private boolean deleted = false;

}

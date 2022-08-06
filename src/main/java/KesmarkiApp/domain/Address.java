package KesmarkiApp.domain;

import KesmarkiApp.domain.enums.StreetType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer id;

    @Column(name = "country")
    private String country;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "street_name")
    private String streetName;

    @Enumerated(EnumType.STRING)
    @Column(name = "street_type")
    private StreetType streetType;

    @Column(name = "street_number")
    private String streetNumber;

    @OneToMany(mappedBy = "address")
    private List<Contact> contacts;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "deleted")
    private boolean deleted = false;

}

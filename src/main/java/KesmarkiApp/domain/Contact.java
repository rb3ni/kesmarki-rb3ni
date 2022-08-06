package KesmarkiApp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "otherContactDetail")
    private String otherContactDetail;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "deleted")
    private boolean deleted = false;

}

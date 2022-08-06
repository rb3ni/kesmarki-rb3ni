package KesmarkiApp.repository;

import KesmarkiApp.domain.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class AddressRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Address saveAddress(Address ToSave) {
        entityManager.persist(ToSave);
        return ToSave;
    }

    public Optional<Address> findAddressById(Integer addressId) {
        return Optional.ofNullable(entityManager.find(Address.class, addressId));
    }

    public void deleteAddress(Address address) {
        address.setDeleted(true);
    }
}

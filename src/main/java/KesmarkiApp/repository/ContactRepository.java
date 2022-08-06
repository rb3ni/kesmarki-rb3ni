package KesmarkiApp.repository;

import KesmarkiApp.domain.Contact;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class ContactRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Contact saveContact(Contact toSave) {
        entityManager.persist(toSave);
        return toSave;
    }

    public Optional<Contact> findContactById(Integer contactId) {
        return Optional.ofNullable(entityManager.find(Contact.class, contactId));
    }

    public void deleteContact(Contact contactToDelete) {
        contactToDelete.setDeleted(true);
    }
}

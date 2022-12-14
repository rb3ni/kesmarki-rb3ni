package KesmarkiApp.repository;

import KesmarkiApp.domain.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Person savePerson(Person toSave) {
        entityManager.persist(toSave);
        return toSave;
    }

    public Optional<Person> findPersonById(Integer personId) {
        return Optional.ofNullable(entityManager.find(Person.class, personId));
    }

    public List<Person> findPeople() {
        return entityManager.createQuery("SELECT p FROM Person p " +
                "WHERE p.deleted = false ", Person.class).getResultList();
    }

    public void deletePerson(Person personToDelete) {
        personToDelete.setDeleted(true);
    }
}

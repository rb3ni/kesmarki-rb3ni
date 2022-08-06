package KesmarkiApp.repository;

import KesmarkiApp.domain.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Person savePerson(Person toSave) {
        entityManager.persist(toSave);
        return toSave;
    }
}

package KesmarkiApp.service;

import KesmarkiApp.domain.Person;
import KesmarkiApp.dto.PersonCreateCommand;
import KesmarkiApp.dto.PersonInfo;
import KesmarkiApp.dto.PersonInfoList;
import KesmarkiApp.exceptionhandling.PersonNotFoundException;
import KesmarkiApp.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public PersonService(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    public PersonInfo savePerson(PersonCreateCommand command) {
        Person personToSave = modelMapper.map(command, Person.class);
        personToSave.setAddresses(new ArrayList<>());

        Person personSaved = personRepository.savePerson(personToSave);
        return modelMapper.map(personSaved, PersonInfo.class);
    }

    public PersonInfo getPersonById(Integer personId) {
        Person person = findPersonById(personId);
        return modelMapper.map(person, PersonInfo.class);
    }

    public List<PersonInfoList> getPeople() {
        List<Person> people = personRepository.findPeople();
        return people.stream()
                .map(person -> modelMapper.map(person, PersonInfoList.class))
                .collect(Collectors.toList());
    }

    public void deletePerson(Integer personId) {
        Person personToDelete = findPersonById(personId);
        personRepository.deletePerson(personToDelete);
    }

    protected Person findPersonById(Integer personId) {
        Optional<Person> personFound = personRepository.findEventById(personId);
        if (personFound.isEmpty() || personFound.get().isDeleted()) {
            throw new PersonNotFoundException(personId);
        }
        return personFound.get();
    }
}

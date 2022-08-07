package KesmarkiApp.service;

import KesmarkiApp.domain.Address;
import KesmarkiApp.domain.Person;
import KesmarkiApp.dto.AddressInfoList;
import KesmarkiApp.dto.ContactInfoList;
import KesmarkiApp.dto.PersonInfo;
import KesmarkiApp.dto.PersonInfoList;
import KesmarkiApp.dto.command.PersonCreateCommand;
import KesmarkiApp.dto.command.PersonNameUpdateCommand;
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
        return mapPersonToPersonInfo(personSaved);
    }

    public PersonInfo getPersonById(Integer personId) {
        Person person = findPersonById(personId);
        return mapPersonToPersonInfo(person);
    }

    public List<PersonInfoList> getPeople() {
        List<Person> people = personRepository.findPeople();
        return people.stream()
                .map(person -> modelMapper.map(person, PersonInfoList.class))
                .collect(Collectors.toList());
    }

    public PersonInfo modifyPersonName(Integer personId, PersonNameUpdateCommand command) {
        Person personToModify = findPersonById(personId);
        personToModify.setFirstName(command.getFirstName());

        return mapPersonToPersonInfo(personToModify);
    }

    public void deletePerson(Integer personId) {
        Person personToDelete = findPersonById(personId);
        personRepository.deletePerson(personToDelete);
    }

    protected Person findPersonById(Integer personId) {
        Optional<Person> personFound = personRepository.findPersonById(personId);
        if (personFound.isEmpty() || personFound.get().isDeleted()) {
            throw new PersonNotFoundException(personId);
        }
        return personFound.get();
    }

    private PersonInfo mapPersonToPersonInfo(Person person) {
        PersonInfo personInfo = modelMapper.map(person, PersonInfo.class);
        List<AddressInfoList> addressInfoList = new ArrayList<>();

        for (Address address : person.getAddresses()) {
            if (!address.isDeleted()) {
                List<ContactInfoList> contactInfoList = address.getContacts().stream()
                        .filter(contact -> !contact.isDeleted())
                        .map(contact -> modelMapper.map(contact, ContactInfoList.class)).toList();

                AddressInfoList addressMapped = modelMapper.map(address, AddressInfoList.class);
                addressMapped.setContacts(contactInfoList);
                addressInfoList.add(addressMapped);
            }
        }
        personInfo.setAddresses(addressInfoList);
        return personInfo;
    }
}

package KesmarkiApp.service;

import KesmarkiApp.domain.Person;
import KesmarkiApp.dto.PersonCreateCommand;
import KesmarkiApp.dto.PersonInfo;
import KesmarkiApp.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

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
}

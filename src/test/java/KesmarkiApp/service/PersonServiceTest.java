package KesmarkiApp.service;

import KesmarkiApp.domain.Person;
import KesmarkiApp.dto.PersonInfo;
import KesmarkiApp.dto.PersonInfoList;
import KesmarkiApp.dto.command.PersonCreateCommand;
import KesmarkiApp.exceptionhandling.PersonNotFoundException;
import KesmarkiApp.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private PersonService personService;

    private Person firstPerson;
    private Person secondPerson;

    @Test
    void testSavePerson_Success() {
        when(personRepository.savePerson(firstPerson)).thenReturn(firstPerson);

        PersonInfo result = personService.savePerson(new PersonCreateCommand("János", "Tóth",
                LocalDate.of(1995, Month.AUGUST, 10), "magyar"));

        PersonInfo expected = modelMapper.map(firstPerson, PersonInfo.class);
        assertEquals(expected, result);
    }

    @Test
    void testGetPeople_EmptyList() {
        when(personRepository.findPeople()).thenReturn(List.of());
        assertThat(personService.getPeople()).isEmpty();
    }

    @Test
    void testGetPeople_HasTwoPeople() {
        when(personRepository.findPeople()).thenReturn(List.of(firstPerson, secondPerson));
        PersonInfoList firstPersonInfo = modelMapper.map(firstPerson, PersonInfoList.class);
        PersonInfoList secondPersonInfo = modelMapper.map(secondPerson, PersonInfoList.class);

        assertThat(personService.getPeople())
                .hasSize(2)
                .contains(firstPersonInfo, secondPersonInfo);
    }

    @Test
    void testGetPersonById_Success() {
        when(personRepository.findPersonById(1)).thenReturn(Optional.ofNullable(firstPerson));

        PersonInfo result = personService.getPersonById(1);
        PersonInfo expected = modelMapper.map(firstPerson, PersonInfo.class);
        assertEquals(expected, result);
    }

    @Test
    void testGetPersonById_PersonNotFoundException() {
        when(personRepository.findPersonById(3)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () ->
                personService.getPersonById(3));
    }

    @BeforeEach
    void init() {
        personService = new PersonService(personRepository, modelMapper);

        firstPerson = new Person();
        firstPerson.setFirstName("János");
        firstPerson.setSecondName("Tóth");
        firstPerson.setDateOfBirth(LocalDate.of(1995, Month.AUGUST, 10));
        firstPerson.setNationality("magyar");
        firstPerson.setAddresses(new ArrayList<>());
        firstPerson.setDeleted(false);

        secondPerson = new Person();
        secondPerson.setFirstName("Enikő");
        secondPerson.setSecondName("Tóth");
        secondPerson.setDateOfBirth(LocalDate.of(1987, Month.AUGUST, 25));
        secondPerson.setNationality("magyar");
        secondPerson.setAddresses(new ArrayList<>());
        secondPerson.setDeleted(false);

    }
}

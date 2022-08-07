package KesmarkiApp.controller;

import KesmarkiApp.dto.AddressInfo;
import KesmarkiApp.dto.command.PersonCreateCommand;
import KesmarkiApp.dto.PersonInfo;
import KesmarkiApp.dto.PersonInfoList;
import KesmarkiApp.dto.command.PersonNameUpdateCommand;
import KesmarkiApp.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/people")
@Slf4j
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonInfo> savePerson(@Valid @RequestBody PersonCreateCommand command) {
        log.info("Http request, POST /api/persons, body: " + command.toString());
        PersonInfo saved = personService.savePerson(command);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonInfo> getPersonById(@PathVariable Integer personId) {
        log.info("Http request, GET /api/people/{personId}, parameter: " + personId);
        PersonInfo person = personService.getPersonById(personId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PersonInfoList>> getPeople() {
        log.info("Http request, GET /api/people");
        List<PersonInfoList> persons = personService.getPeople();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonInfo> modifyPersonName(@PathVariable Integer personId,
                                                       @Valid @RequestBody PersonNameUpdateCommand command) {
        log.info("Http request, PUT /api/people/{personId}, body: " + command.toString() +
                ", parameters: " + personId);
        PersonInfo modifyPersonName = personService.modifyPersonName(personId, command);
        return new ResponseEntity<>(modifyPersonName, HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer personId) {
        log.info("Http request, DELETE /api/people/{personId}, parameter: "
                + personId);
        personService.deletePerson(personId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

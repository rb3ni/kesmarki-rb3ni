package KesmarkiApp.controller;

import KesmarkiApp.dto.PersonCreateCommand;
import KesmarkiApp.dto.PersonInfo;
import KesmarkiApp.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/persons")
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
        log.info("Http request, GET /api/persons/{personId}, parameter: " + personId);
        PersonInfo person = personService.getPersonById(personId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}

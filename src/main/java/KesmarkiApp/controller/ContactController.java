package KesmarkiApp.controller;

import KesmarkiApp.dto.ContactCreateCommand;
import KesmarkiApp.dto.ContactInfo;
import KesmarkiApp.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/contacts")
@Slf4j
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/addresses/{addressId}")
    public ResponseEntity<ContactInfo> saveContact(@PathVariable Integer addressId,
                                                   @Valid @RequestBody ContactCreateCommand command) {
        log.info("Http request, POST /api/contacts/{addressId}, body: " + command.toString() +
                ", parameters: " + addressId);
        ContactInfo contact = contactService.saveContact(addressId, command);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<ContactInfo> getContactById(@PathVariable Integer contactId) {
        log.info("Http request, GET /api/contacts/{contactId}, parameter: " + contactId);
        ContactInfo contact = contactService.getContactById(contactId);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Void> deleteContract(@PathVariable Integer contactId) {
        log.info("Http request, DELETE /api/contacts/{contactId}, parameter: "
                + contactId);
        contactService.deleteContact(contactId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

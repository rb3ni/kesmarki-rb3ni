package KesmarkiApp.controller;

import KesmarkiApp.dto.AddressInfo;
import KesmarkiApp.dto.command.AddressUpdateCommand;
import KesmarkiApp.dto.command.ContactCreateCommand;
import KesmarkiApp.dto.ContactInfo;
import KesmarkiApp.dto.command.ContactPhoneNumberUpdateCommand;
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

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactInfo> modifyContactPhoneNumber(@PathVariable Integer contactId,
                                                     @Valid @RequestBody ContactPhoneNumberUpdateCommand command) {
        log.info("Http request, PUT /api/contacts/{contactId}, body: " + command.toString() +
                ", parameters: " + contactId);
        ContactInfo modifyContact = contactService.modifyContactPhoneNumber(contactId, command);
        return new ResponseEntity<>(modifyContact, HttpStatus.OK);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Void> deleteContract(@PathVariable Integer contactId) {
        log.info("Http request, DELETE /api/contacts/{contactId}, parameter: "
                + contactId);
        contactService.deleteContact(contactId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

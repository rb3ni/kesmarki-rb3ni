package KesmarkiApp.controller;

import KesmarkiApp.dto.AddressInfo;
import KesmarkiApp.dto.command.AddressCreateCommand;
import KesmarkiApp.dto.command.AddressUpdateCommand;
import KesmarkiApp.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
@Slf4j
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/people/{personId}")
    public ResponseEntity<AddressInfo> saveAddress(@PathVariable Integer personId,
                                                   @Valid @RequestBody AddressCreateCommand command) {
        log.info("Http request, POST /api/addresses/{personId}, body: " + command.toString() +
                ", parameters: " + personId);
        AddressInfo address = addressService.saveAddress(personId, command);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressInfo> getAddressById(@PathVariable Integer addressId) {
        log.info("Http request, GET /api/addresses/{addressId}, parameter: " + addressId);
        AddressInfo address = addressService.getAddressById(addressId);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressInfo> modifyAddress(@PathVariable Integer addressId,
                                                     @Valid @RequestBody AddressUpdateCommand command) {
        log.info("Http request, PUT /api/addresses/{addressId}, body: " + command.toString() +
                ", parameters: " + addressId);
        AddressInfo modifyAddress = addressService.modifyAddress(addressId, command);
        return new ResponseEntity<>(modifyAddress, HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer addressId) {
        log.info("Http request, DELETE /api/addresses/{addressId}, parameter: "
                + addressId);
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

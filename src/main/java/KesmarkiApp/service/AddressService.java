package KesmarkiApp.service;

import KesmarkiApp.domain.Address;
import KesmarkiApp.domain.Person;
import KesmarkiApp.dto.AddressCreateCommand;
import KesmarkiApp.dto.AddressInfo;
import KesmarkiApp.exceptionhandling.AddressNotFoundException;
import KesmarkiApp.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class AddressService {

    private final AddressRepository addressRepository;
    private final PersonService personService;
    private final ModelMapper modelMapper;

    public AddressService(AddressRepository addressRepository, PersonService personService, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    public AddressInfo saveAddress(Integer personId, AddressCreateCommand command) {
        Person person = personService.findPersonById(personId);
        Address addressToSave = modelMapper.map(command, Address.class);
        addressToSave.setPerson(person);
        addressToSave.setContacts(new ArrayList<>());

        Address addressSaved = addressRepository.saveAddress(addressToSave);
        return modelMapper.map(addressSaved, AddressInfo.class);
    }

    public AddressInfo getAddressById(Integer addressId) {
        Address address = findAddressById(addressId);
        return modelMapper.map(address, AddressInfo.class);
    }

    public void deleteAddress(Integer addressId) {
        Address address = findAddressById(addressId);
        addressRepository.deleteAddress(address);
    }

    protected Address findAddressById(Integer addressId) {
        Optional<Address> addressFound = addressRepository.findAddressById(addressId);
        if (addressFound.isEmpty() || addressFound.get().isDeleted()) {
            throw new AddressNotFoundException(addressId);
        }
        return addressFound.get();
    }
}

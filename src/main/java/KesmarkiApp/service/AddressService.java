package KesmarkiApp.service;

import KesmarkiApp.domain.Address;
import KesmarkiApp.domain.Person;
import KesmarkiApp.dto.AddressCreateCommand;
import KesmarkiApp.dto.AddressInfo;
import KesmarkiApp.dto.ContactInfoList;
import KesmarkiApp.dto.PersonInfoList;
import KesmarkiApp.exceptionhandling.AddressNotFoundException;
import KesmarkiApp.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
        return mapAddressToAddressInfo(addressSaved);
    }

    public AddressInfo getAddressById(Integer addressId) {
        Address address = findAddressById(addressId);
        return mapAddressToAddressInfo(address);
    }

    public void deleteAddress(Integer addressId) {
        Address address = findAddressById(addressId);
        addressRepository.deleteAddress(address);
    }

    private AddressInfo mapAddressToAddressInfo(Address address) {
        AddressInfo addressInfo = modelMapper.map(address, AddressInfo.class);
        PersonInfoList personInfoList = null;
        if (!address.getPerson().isDeleted()) {
            personInfoList = modelMapper.map(address.getPerson(), PersonInfoList.class);
        }
        List<ContactInfoList> contactInfoList = address.getContacts().stream()
                .filter(contact -> !contact.isDeleted())
                .map(contact -> modelMapper.map(contact, ContactInfoList.class)).toList();

        addressInfo.setContacts(contactInfoList);
        addressInfo.setPerson(personInfoList);
        return addressInfo;
    }

    protected Address findAddressById(Integer addressId) {
        Optional<Address> addressFound = addressRepository.findAddressById(addressId);
        if (addressFound.isEmpty() || addressFound.get().isDeleted()) {
            throw new AddressNotFoundException(addressId);
        }
        return addressFound.get();
    }
}

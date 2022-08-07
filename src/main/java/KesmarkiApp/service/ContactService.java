package KesmarkiApp.service;

import KesmarkiApp.domain.Address;
import KesmarkiApp.domain.Contact;
import KesmarkiApp.dto.AddressForContactInfo;
import KesmarkiApp.dto.ContactCreateCommand;
import KesmarkiApp.dto.ContactInfo;
import KesmarkiApp.exceptionhandling.ContactNotFoundException;
import KesmarkiApp.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ContactService {

    private final ContactRepository contactRepository;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    public ContactService(ContactRepository contactRepository, AddressService addressService, ModelMapper modelMapper) {
        this.contactRepository = contactRepository;
        this.addressService = addressService;
        this.modelMapper = modelMapper;
    }


    public ContactInfo saveContact(Integer addressId, ContactCreateCommand command) {
        Address addressFound = addressService.findAddressById(addressId);
        Contact contactToSave = modelMapper.map(command, Contact.class);
        contactToSave.setAddress(addressFound);

        Contact contactSaved = contactRepository.saveContact(contactToSave);
        return mapContactToContactInfo(contactSaved);
    }

    public ContactInfo getContactById(Integer contactId) {
        Contact contact = findContactById(contactId);
        return mapContactToContactInfo(contact);
    }

    public void deleteContact(Integer contactId) {
        Contact contactToDelete = findContactById(contactId);
        contactRepository.deleteContact(contactToDelete);
    }

    private ContactInfo mapContactToContactInfo(Contact contact) {
        ContactInfo contactInfo = modelMapper.map(contact, ContactInfo.class);
        AddressForContactInfo addressForContactInfo = null;
        if (!contact.getAddress().isDeleted()) {
            addressForContactInfo = modelMapper.map(contact.getAddress(), AddressForContactInfo.class);
        }

        contactInfo.setAddress(addressForContactInfo);
        return contactInfo;
    }

    private Contact findContactById(Integer contactId) {
        Optional<Contact> contactFound = contactRepository.findContactById(contactId);
        if (contactFound.isEmpty() || contactFound.get().isDeleted()) {
            throw new ContactNotFoundException(contactId);
        }
        return contactFound.get();
    }
}

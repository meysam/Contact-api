package ir.zeroandone.app.controller;

import ir.zeroandone.app.domain.contact.model.Contact;
import ir.zeroandone.app.domain.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Contact newContact(@PathVariable Long id) {

        return contactRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createContact(@ModelAttribute("contact") @Valid Contact contact) {

        contactRepository.save(contact);
        return "Ok";
    }

}

package ir.zeroandone.app.controller;

import ir.zeroandone.app.domain.contact.model.Contact;
import ir.zeroandone.app.domain.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;


    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String newContact(Contact contact) {
        return "persons/contact";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String createContact(@ModelAttribute("contact") @Valid Contact contact, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "persons/contact";
        }
        contactRepository.save(contact);
        return "persons/contact-results";
    }

}

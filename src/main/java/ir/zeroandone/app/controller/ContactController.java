package ir.zeroandone.app.controller;

import ir.zeroandone.app.application.address.dto.AddressDto;
import ir.zeroandone.app.application.address.service.AddressService;
import ir.zeroandone.app.application.sms.service.SmsService;
import ir.zeroandone.app.domain.contact.model.Contact;
import ir.zeroandone.app.domain.contact.repository.ContactRepository;
import ir.zeroandone.app.domain.person.model.Attachment;
import ir.zeroandone.app.domain.person.repository.AttachmentRepository;
import ir.zeroandone.app.domain.person.model.Person;
import ir.zeroandone.app.domain.person.repository.PersonRepository;
import ir.zeroandone.app.infra.helper.RandomString;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

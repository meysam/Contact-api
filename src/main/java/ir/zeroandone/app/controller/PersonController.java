package ir.zeroandone.app.controller;

import ir.zeroandone.app.application.address.dto.AddressDto;
import ir.zeroandone.app.application.address.service.AddressService;
import ir.zeroandone.app.application.sms.service.SmsService;
import ir.zeroandone.app.domain.Person;
import ir.zeroandone.app.domain.PersonRepository;
import ir.zeroandone.app.infra.helper.RandomString;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonController extends WebMvcConfigurerAdapter {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPerson(Person person) {
        return "persons/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, Model model) {
        RandomString randomString = new RandomString(8);
        person.setFollowingCode(randomString.nextString());
        if (bindingResult.hasErrors()) {
            return "persons/new";
        }
        repository.save(person);
        String message = String.format("%s \n %s : %s", "اطلاعات شما با موفقیت ثبت شد.", "کد رهگیری شما", person.getFollowingCode());
        smsService.sendBySoap(message, person.getCellPhone());
        return "persons/results";
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getAddress(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        return addressService.getAddress(params);
    }

    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("persons/results").setViewName("results");
    }*/

 /*   @RequestMapping(value = "", method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("persons", repository.findAll());
        return "persons/list";
    }*/

/*    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id) {
        repository.delete(id);
        return new ModelAndView("redirect:/persons");
    }*/



 /*   @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("person_id") long id,
                               @RequestParam("firstName") String firstName) {
        Person person = repository.findOne(id);
        person.setFirstName(firstName);
        repository.save(person);
        return new ModelAndView("redirect:/persons");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable long id,
                       Model model) {
        Person person = repository.findOne(id);
        model.addAttribute("person", person);
        return "persons/edit";
    }*/
}

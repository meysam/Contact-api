package ir.zeroandone.app.controller;

import ir.zeroandone.app.domain.Person;
import ir.zeroandone.app.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
@RequestMapping("/persons")
public class PersonController extends WebMvcConfigurerAdapter {

    @Autowired
    private PersonRepository repository;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

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

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPerson(Person person) {
        return "persons/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "persons/new";
        }
        repository.save(person);
        return "redirect:/results";
    }

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

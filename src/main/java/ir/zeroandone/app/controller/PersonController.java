package ir.zeroandone.app.controller;

import ir.zeroandone.app.domain.Person;
import ir.zeroandone.app.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @RequestMapping(value="", method= RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("persons", repository.findAll());
        return "persons/list";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id) {
        repository.delete(id);
        return new ModelAndView("redirect:/persons");
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String newProject() {
        return "persons/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("firstName") String firstName) {
        repository.save(new Person(firstName));
        return new ModelAndView("redirect:/persons");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("person") long id,
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
    }
}

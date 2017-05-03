package ir.zeroandone.app.domain.person.validator;

import ir.zeroandone.app.domain.person.model.Person;
import ir.zeroandone.app.domain.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class PersonValidator implements Validator {

    @Autowired
    PersonRepository personRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personRepository.findPersonByNationalId(person.getNationalId()) != null) {
            errors.rejectValue("nationalId", "Duplicate.person.nationalId");
        }
    }
}

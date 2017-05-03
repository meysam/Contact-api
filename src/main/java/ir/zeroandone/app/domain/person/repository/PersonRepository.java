package ir.zeroandone.app.domain.person.repository;

import ir.zeroandone.app.domain.person.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findPersonByNationalId(String nationalId);
}

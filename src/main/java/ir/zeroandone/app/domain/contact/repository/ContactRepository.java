package ir.zeroandone.app.domain.contact.repository;

import ir.zeroandone.app.domain.contact.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}

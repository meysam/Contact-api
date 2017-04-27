package ir.zeroandone.app.domain.person.repository;

import ir.zeroandone.app.domain.person.model.Attachment;
import org.springframework.data.repository.CrudRepository;

public interface AttachmentRepository extends CrudRepository<Attachment,Long> {
   /* @Query(value = "select a from Attachment as a inner join Person as p where p.id = :userId and a.title = :title")
    Attachment getAttachment(@Param("userId") long userId, @Param("title") String title);*/
}

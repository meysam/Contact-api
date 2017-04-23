package ir.zeroandone.app.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface AttachmentRepository extends CrudRepository<Attachment,Long> {
   /* @Query(value = "select a from Attachment as a inner join Person as p where p.id = :userId and a.title = :title")
    Attachment getAttachment(@Param("userId") long userId, @Param("title") String title);*/
}

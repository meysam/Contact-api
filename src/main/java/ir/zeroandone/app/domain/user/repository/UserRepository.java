package ir.zeroandone.app.domain.user.repository;

import ir.zeroandone.app.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

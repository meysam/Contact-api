package ir.zeroandone.app.domain.user.repository;

import ir.zeroandone.app.domain.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

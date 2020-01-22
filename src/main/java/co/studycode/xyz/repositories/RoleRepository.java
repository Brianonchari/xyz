package co.studycode.xyz.repositories;

import co.studycode.xyz.models.Role;
import co.studycode.xyz.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}

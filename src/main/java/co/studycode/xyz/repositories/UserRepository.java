package co.studycode.xyz.repositories;


import co.studycode.xyz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findUserByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByFullName(String fullName);
}


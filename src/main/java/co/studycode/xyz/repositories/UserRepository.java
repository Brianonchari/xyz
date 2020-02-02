package co.studycode.xyz.repositories;


import co.studycode.xyz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    public static final String FETCH_USERS = "SELECT full_name, email FROM users";


    Optional<User> findByUsernameOrEmail(String username, String email);

    @Query(value = FETCH_USERS, nativeQuery = true)
    public List<Object[]> findAllUsers();
    Optional<User> findUserByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByFullName(String fullName);
}


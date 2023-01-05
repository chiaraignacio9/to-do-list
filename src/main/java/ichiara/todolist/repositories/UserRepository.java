package ichiara.todolist.repositories;

import ichiara.todolist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query(value = "SELECT COUNT(user_email) FROM _users WHERE user_email =:email", nativeQuery = true)
    Integer existsByEmail(String email);

    @Query(value = "SELECT * FROM _users WHERE user_email =:email", nativeQuery = true)
    Optional<User> findByEmail(String email);

}

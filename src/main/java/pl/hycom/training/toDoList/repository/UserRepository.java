package pl.hycom.training.toDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hycom.training.toDoList.model.User;

/**
 * Created by hycom on 11.04.18.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

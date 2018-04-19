package pl.hycom.training.toDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.hycom.training.toDoList.model.Role;
import pl.hycom.training.toDoList.model.User;

import java.util.Set;

/**
 * Created by hycom on 11.04.18.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.roles = :role WHERE u.id = :id")
    int setRoles(@Param("id") Long id, @Param("role") Role role);
}

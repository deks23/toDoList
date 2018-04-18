package pl.hycom.training.toDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hycom.training.toDoList.model.Role;

import java.util.Optional;

/**
 * Created by hycom on 11.04.18.
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findById(Long id);
    Optional<Role> findByName(String name);
}

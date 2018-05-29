package pl.damian.training.toDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.damian.training.toDoList.model.Role;

import java.util.List;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findById(Long id);
    Optional<Role> findByName(String name);
    List<Role> findAll();

}

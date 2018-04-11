package pl.hycom.training.toDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hycom.training.toDoList.model.Role;

/**
 * Created by hycom on 11.04.18.
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
}

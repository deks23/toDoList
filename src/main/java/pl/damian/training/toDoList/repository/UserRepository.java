package pl.damian.training.toDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.damian.training.toDoList.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

  /*  @Transactional
    @Modifying
    @Query("UPDATE User u SET u.roles = :role WHERE u.id = :id")
    int setRoles(@Param("id") Long id, @Param("role") Set<Role> roles);*/
}

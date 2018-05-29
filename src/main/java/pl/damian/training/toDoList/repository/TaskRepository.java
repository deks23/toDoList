package pl.damian.training.toDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.damian.training.toDoList.model.User;
import pl.damian.training.toDoList.model.Task;

import java.util.List;


 public interface TaskRepository extends JpaRepository <Task, Long> {
    List<Task> findAll();
    List<Task> findByUser(User user);

}

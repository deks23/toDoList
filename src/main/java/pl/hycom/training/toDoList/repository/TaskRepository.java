package pl.hycom.training.toDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hycom.training.toDoList.model.Task;

import java.util.List;

/**
 * Created by hycom on 04.04.18.
 */
 public interface TaskRepository extends JpaRepository <Task, Long> {
    List<Task> findAll();

}

package pl.hycom.training.toDoList.service;

import pl.hycom.training.toDoList.model.Task;
import pl.hycom.training.toDoList.model.User;

import java.util.List;

/**
 * Created by hycom on 12.04.18.
 */
public interface AdminService {
    List<User> getAllUsers();
    List<Task> getAllTasks();
    void deleteTask(Long id);
}

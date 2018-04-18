package pl.hycom.training.toDoList.service;

import pl.hycom.training.toDoList.model.Role;
import pl.hycom.training.toDoList.model.Task;
import pl.hycom.training.toDoList.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by hycom on 12.04.18.
 */
public interface AdminService {
    List<User> getAllUsers();
    List<Task> getAllTasks();
    void deleteTask(Long id);
    void addTask(String username, String description, String finishDate);
    List<Role> getRoles();
    void addUser(String username, String password, Set<String> authorities);
}

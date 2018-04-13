package pl.hycom.training.toDoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hycom.training.toDoList.model.Task;
import pl.hycom.training.toDoList.model.User;
import pl.hycom.training.toDoList.repository.TaskRepository;
import pl.hycom.training.toDoList.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by hycom on 12.04.18.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(taskRepository.getOne(id));
    }

    @Override
    public void addTask(String username, String description, String finishDate) {
        Task task = new Task();
        task.setUser(userRepository.findByUsername(username));
        task.setDescription(description);
        if(finishDate!="")
            task.setFinishDate(LocalDate.parse(finishDate));
        else task.setFinishDate(LocalDate.now());
        taskRepository.save(task);
    }
}

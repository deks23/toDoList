package pl.damian.training.toDoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.damian.training.toDoList.repository.TaskRepository;
import pl.damian.training.toDoList.repository.UserRepository;
import pl.damian.training.toDoList.model.Task;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by hycom on 09.04.18.
 */
@Service
public class ToDoListService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public List<Task> getAllTasks(){
           return taskRepository.findAll();
    }


    public List<Task> getMytaks(Long id){
        return taskRepository.findByUser(userRepository.findById(id).get());
    }

    public void  addTask(String description, String date){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Task task = new Task();
        task.setDescription(description);
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        task.setUser(userRepository.findByUsername(user.getUsername()));
        auth.getPrincipal();
        if (date != "")
            task.setFinishDate(LocalDate.parse(date));
        else
            task.setFinishDate(LocalDate.now());
        taskRepository.save(task);
    }


    public void restAddTask(String description, String date, Long id){
        Task task = new Task();
        task.setDescription(description);
        if (date != "")
            task.setFinishDate(LocalDate.parse(date));
        else
            task.setFinishDate(LocalDate.now());
        task.setUser(userRepository.findById(id).get());
        taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.delete(taskRepository.getOne(id));
    }

    public List<Task> getUserTasks(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        return taskRepository.findByUser(userRepository.findByUsername(user.getUsername()));
    }

    public void restDeleteTask(Long taskId, Long userId) throws AccessDeniedException{
        Task taskToDelete = taskRepository.getOne(taskId);
        if (taskToDelete.getUser().getId() == userId)
            taskRepository.delete(taskToDelete);
        else
            throw new AccessDeniedException("");
    }
}

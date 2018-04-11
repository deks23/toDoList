package pl.hycom.training.toDoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pl.hycom.training.toDoList.model.Task;
import pl.hycom.training.toDoList.model.User;
import pl.hycom.training.toDoList.repository.TaskRepository;
import pl.hycom.training.toDoList.repository.UserRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void deleteTask(Long id){
        taskRepository.delete(taskRepository.getOne(id));
    }
}

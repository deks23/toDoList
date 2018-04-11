package pl.hycom.training.toDoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pl.hycom.training.toDoList.model.Task;
import pl.hycom.training.toDoList.repository.TaskRepository;

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

    public List<Task> getAllTasks(){
           return taskRepository.findAll();
    }

    public void  addTask(String description, String date){
        Task task = new Task();
        task.setDescription(description);
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

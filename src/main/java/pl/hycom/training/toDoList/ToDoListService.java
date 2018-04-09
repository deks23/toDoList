package pl.hycom.training.toDoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pl.hycom.training.toDoList.model.Task;
import pl.hycom.training.toDoList.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

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
            task.setFinishDate(date.substring(0, 10));
        else
            task.setFinishDate("");
        taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.delete(taskRepository.getOne(id));
    }
}

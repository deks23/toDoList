package pl.hycom.training.toDoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pl.hycom.training.toDoList.repository.TaskRepository;

/**
 * Created by hycom on 09.04.18.
 */
@Service
public class ToDoListService {
    @Autowired
    TaskRepository taskRepository;

    ModelAndView getAllTasks(){
        try{
            return new ModelAndView("/index", "model", taskRepository.findAll());
        }catch (Exception e){
            return new ModelAndView("/index");
        }
    }

    String  addTask(String description, String date){

        return "redirect:/";
    }
}

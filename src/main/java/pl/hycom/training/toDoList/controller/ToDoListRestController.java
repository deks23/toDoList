package pl.hycom.training.toDoList.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.hycom.training.toDoList.model.Task;
import pl.hycom.training.toDoList.repository.TaskRepository;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by hycom on 09.04.18.
 */
@RestController
public class ToDoListRestController {

    @Autowired
    TaskRepository tasksRepository;

    @RequestMapping(value="/restGet", method = GET)
    public String test(){
        return "TEST";
    }

    @RequestMapping(value="/restGet", params={"par"}, method = POST)
    public String test2(@RequestParam String par){
        return par;
    }

    @RequestMapping(value="/restGetTasks", method = GET)
    public ResponseEntity getTasks(){
        return ResponseEntity.ok(tasksRepository.findAll());
    }

    @RequestMapping(value="/restAddTask", params = {"description", "finishDate"}, method = POST)
    public ResponseEntity addTask(@RequestParam String description, @RequestParam String finishDate){
        Task task = new Task();

     // return   ResponseEntity.ok(tasksRepository.)
    }
}

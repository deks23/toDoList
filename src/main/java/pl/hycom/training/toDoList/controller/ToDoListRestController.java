package pl.hycom.training.toDoList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.hycom.training.toDoList.service.ToDoListService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by hycom on 09.04.18.
 */
@RestController
public class ToDoListRestController {

    @Autowired
    ToDoListService toDoListService;


    @RequestMapping(value="/rest/GetTasks", method = GET)
    public ResponseEntity getTasks(){
        return ResponseEntity.ok(toDoListService.getAllTasks());
    }

    @RequestMapping(value="/rest/AddTask", params = {"description", "finishDate"}, method = POST)
    public void addTask(@RequestParam String description, @RequestParam String finishDate){
        toDoListService.addTask(description, finishDate);
    }

    @RequestMapping(value="/rest/DeleteTask", params ={"id"}, method = POST)
    public void removeTask(@RequestParam String id){
        toDoListService.deleteTask(Long.valueOf(id));
    }

    @RequestMapping(value="/rest/login", params={"login", "password"}, method = POST)
    public ResponseEntity login (@RequestParam String login, @RequestParam String password){
        
        return ResponseEntity.ok("qwe");
    }
}

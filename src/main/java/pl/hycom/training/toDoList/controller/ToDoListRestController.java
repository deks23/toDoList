package pl.hycom.training.toDoList.controller;

import com.sun.deploy.net.HttpResponse;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.HttpResource;
import pl.hycom.training.toDoList.model.Task;
import pl.hycom.training.toDoList.model.User;
import pl.hycom.training.toDoList.service.AdminService;
import pl.hycom.training.toDoList.service.ToDoListService;
import pl.hycom.training.toDoList.service.UserService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by hycom on 09.04.18.
 */
@RestController
@CrossOrigin(maxAge = 7000)
public class ToDoListRestController {

    @Autowired
    ToDoListService toDoListService;

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @RequestMapping(value="/rest/GetTasks", method = GET)
    public List<Task> getTasks(){
        return toDoListService.getAllTasks();
    }

    @RequestMapping(value="/rest/register", params ={"username", "password"}, method = POST)
    public void register(@RequestParam String username, @RequestParam String password){
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        userService.save(user);
    }


    @RequestMapping(value="/rest/DeleteTask", params ={"id"}, method = POST)
    public void removeTask(@RequestParam String id){
        toDoListService.deleteTask(Long.valueOf(id));
    }

    @RequestMapping(value="/rest/AddTask", params = {"description", "finishDate"}, method = POST)
    public void addTask(@RequestParam String description, @RequestParam String finishDate){
        toDoListService.addTask(description, finishDate);
    }

    @RequestMapping(value="/rest/login", params={"username", "password"}, method = POST)
    public ResponseEntity login (@RequestParam String username, @RequestParam String password){
        return ResponseEntity.ok(userService.restLogin(password, password));
    }

    @RequestMapping(value="/rest/check", params={"token"}, method = POST)
    public ResponseEntity check (@RequestParam String token){
        try{
            try {
                return ResponseEntity.ok(userService.parseJWT(token));
            }catch (MalformedJwtException e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        }catch (SignatureException exception){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
}

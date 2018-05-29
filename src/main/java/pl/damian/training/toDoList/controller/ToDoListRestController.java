package pl.damian.training.toDoList.controller;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.damian.training.toDoList.exceptions.PasswordInvalidException;
import pl.damian.training.toDoList.model.User;
import pl.damian.training.toDoList.service.ToDoListService;
import pl.damian.training.toDoList.service.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@CrossOrigin(maxAge = 7000)
public class ToDoListRestController {

    @Autowired
    ToDoListService toDoListService;

    @Autowired
    UserService userService;


    @RequestMapping(value="/rest/GetMyTasks", params ={"token"}, method = POST)
    public ResponseEntity getMyTasks(@RequestParam String token){
        Claims claims;
        try {
           claims = userService.parseJWT(token);
        }catch (JwtException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(toDoListService.getMytaks(Long.valueOf(claims.getId())));
    }

    @RequestMapping(value="/rest/register", params ={"username", "password"}, method = POST)
    public void register(@RequestParam String username, @RequestParam String password){
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        userService.save(user);
    }


    @RequestMapping(value="/rest/DeleteTask", params ={"id", "token"}, method = POST)
    public ResponseEntity removeTask(@RequestParam String id, @RequestParam String token){
        Claims claims;
        try {
            claims = userService.parseJWT(token);
        }catch (JwtException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            toDoListService.restDeleteTask(Long.valueOf(id), Long.valueOf(claims.getId()));
        }catch (AccessDeniedException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @RequestMapping(value="/rest/AddTask", params = {"description", "finishDate", "token"}, method = POST)
    public ResponseEntity addTask(@RequestParam String description, @RequestParam String finishDate, @RequestParam String token){
        Claims claims;
        try {
            claims = userService.parseJWT(token);
        }catch (JwtException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        toDoListService.restAddTask(description, finishDate, Long.valueOf(claims.getId()));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value="/rest/login", params={"username", "password"}, method = POST)
    public ResponseEntity login (@RequestParam String username, @RequestParam String password){
        try {
            return ResponseEntity.ok(userService.restLogin(username, password));
        } catch (PasswordInvalidException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value="/rest/check", params={"token"}, method = POST)
    public ResponseEntity check (@RequestParam String token){
        try{
            try {
                return ResponseEntity.ok(userService.parseJWT(token));
            }catch (MalformedJwtException e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }




}

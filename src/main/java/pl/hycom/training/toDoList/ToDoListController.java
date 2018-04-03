package pl.hycom.training.toDoList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

/**
 * Created by hycom on 03.04.18.
 */
@RestController
public class ToDoListController {
    @RequestMapping("/toDoList")
    public String index() {

        return "Greetings from Spring Boot!";
    }
}

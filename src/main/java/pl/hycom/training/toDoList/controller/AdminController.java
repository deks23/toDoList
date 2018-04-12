package pl.hycom.training.toDoList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.hycom.training.toDoList.UserValidator;
import pl.hycom.training.toDoList.service.SecurityService;
import pl.hycom.training.toDoList.service.ToDoListService;
import pl.hycom.training.toDoList.service.UserService;

/**
 * Created by hycom on 12.04.18.
 */
@Controller
public class AdminController {

    @Autowired
    ToDoListService toDoListService;


    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = { "/admin"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "admin";
    }

    
}

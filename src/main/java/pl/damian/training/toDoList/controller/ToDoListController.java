package pl.damian.training.toDoList.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import pl.damian.training.toDoList.UserValidator;
import pl.damian.training.toDoList.model.User;
import pl.damian.training.toDoList.model.Role;
import pl.damian.training.toDoList.service.SecurityService;
import pl.damian.training.toDoList.service.ToDoListService;
import pl.damian.training.toDoList.service.UserService;


import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
public class ToDoListController {

    @Autowired
    ToDoListService toDoListService;


    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;


    @RequestMapping(value = "/", method = GET)
    public ModelAndView index() {
        try{
            return new ModelAndView("/index", "model", toDoListService.getUserTasks());
        }catch (Exception e){
            return new ModelAndView("/index");
        }
    }

    @RequestMapping(value = "/add", method = POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addTask(HttpServletRequest request) {
        toDoListService.addTask(request.getParameter("description"), request.getParameter("date"));
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String deleteTask(HttpServletRequest request) {
        toDoListService.deleteTask(Long.valueOf(request.getParameter("id")));
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/register";
        }
        Set<Role> roles = new HashSet<>();
        userService.save(userForm);
        return "redirect:/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model, String error){
        if (error!=null)
            model.addAttribute("error", "Wrong data");
        return "/register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = "/indexRest", method = RequestMethod.GET)
    public String restIndex(){
        return "/indexRest";
    }


}

package pl.hycom.training.toDoList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.hycom.training.toDoList.UserValidator;
import pl.hycom.training.toDoList.model.Role;
import pl.hycom.training.toDoList.repository.RoleRepository;
import pl.hycom.training.toDoList.repository.TaskRepository;
import pl.hycom.training.toDoList.service.AdminService;
import pl.hycom.training.toDoList.service.SecurityService;
import pl.hycom.training.toDoList.service.ToDoListService;
import pl.hycom.training.toDoList.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = { "/admin"}, method = RequestMethod.GET)
    public ModelAndView welcome() {
        Map<String, Object> map = new HashMap<>();
        map.put("tasks", adminService.getAllTasks());
        map.put("users", adminService.getAllUsers());

        return new ModelAndView("/admin", "model", map);
    }

    @RequestMapping(value =  {"/admin/deleteTask"}, method = RequestMethod.POST, params = {"id"})
    public String deleteTask(@RequestParam String id){
        adminService.deleteTask(Long.valueOf(id));
        return "redirect:/admin";
    }

    @RequestMapping(value =  {"/admin/addTask"}, method = RequestMethod.POST, params = {"description", "finishDate", "username"})
    public String addTask(@RequestParam String username, @RequestParam String description, @RequestParam String finishDate){
        adminService.addTask(username, description, finishDate);
        return "redirect:/admin";
    }

    @RequestMapping(value={"/admin/users"}, method = RequestMethod.GET)
    public ModelAndView getUsers(){
        Map<String, Object> map = new HashMap<>();
        map.put("users", adminService.getAllUsers());
        map.put("roles", adminService.getRoles());
      //  adminService.getRoles().contains(roleRepository.findByName())
        return new ModelAndView("admin/users", "model", map);
    }

    @RequestMapping(value =  {"/admin/save"}, method = RequestMethod.POST, params = {"id", "authorities"})
    public String addTask(@RequestParam String id, @RequestParam Set<String> authorities){
        adminService.saveUser(id, authorities);
        return "redirect:/admin/users";
    }

    @RequestMapping(value =  {"/admin/addUser"}, method = RequestMethod.POST, params = {"username", "password", "authorities"})
    public String addUser(@RequestParam String username, @RequestParam String password, @RequestParam Set<String> authorities){
        adminService.addUser(username, password, authorities);
        return "redirect:/admin/users";
    }

}

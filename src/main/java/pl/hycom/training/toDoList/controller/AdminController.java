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
import pl.hycom.training.toDoList.service.AdminService;
import pl.hycom.training.toDoList.service.SecurityService;
import pl.hycom.training.toDoList.service.ToDoListService;
import pl.hycom.training.toDoList.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

 /*   @RequestMapping(value =  {"/admin/addTask"}, method = RequestMethod.POST, params = {"description", "finishDate"})
    public String deleteTask(@RequestParam String id){
        adminService.deleteTask(Long.valueOf(id));
        return "redirect:/admin";
    }*/

    
}

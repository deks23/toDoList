package pl.hycom.training.toDoList.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.servlet.ModelAndView;

import pl.hycom.training.toDoList.service.ToDoListService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hycom on 03.04.18.
 */
@Controller
public class ToDoListController {

    @Autowired
    ToDoListService toDoListService;

    @RequestMapping(value = "/", method = GET)
    public ModelAndView index() {
        try{
            return new ModelAndView("/index", "model", toDoListService.getAllTasks());
        }catch (Exception e){
            return new ModelAndView("/index");
        }
    }

    @RequestMapping(value = "/", method = POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addTask(HttpServletRequest request) {
        toDoListService.addTask(request.getParameter("description"), request.getParameter("date"));
        return "redirect:/";

    }

    @RequestMapping(value = "/delete", method = POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String deleteTask(HttpServletRequest request) {
        toDoListService.deleteTask(Long.valueOf(request.getParameter("id")));
        return "redirect:/";
    }


    @RequestMapping(value = "/login", method = GET)
    public ModelAndView template() {
            String str = "dane do frontu";
            return new ModelAndView("/template", "dane", str);

    }

}

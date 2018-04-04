package pl.hycom.training.toDoList.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.servlet.ModelAndView;

import pl.hycom.training.toDoList.model.Task;
import pl.hycom.training.toDoList.repository.TaskRepository;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hycom on 03.04.18.
 */
@Controller
public class ToDoListController {

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping(value = "/", method = GET)
    public ModelAndView index() {
        try{
            return new ModelAndView("/index", "model", taskRepository.findAll());
        }catch (Exception e){
            return new ModelAndView("/index");
        }

    }

    @RequestMapping(value = "/", method = POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addTask(HttpServletRequest request) {
            Task task = new Task();
            task.setDescription(request.getParameter("description"));
            if (request.getParameter("date") != "")
                task.setFinishDate(request.getParameter("date").substring(0, 10));
            else
                task.setFinishDate("");
        taskRepository.save(task);
        return "redirect:/";

    }

    @RequestMapping(value = "/delete", method = POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String deleteTask(HttpServletRequest request) {
        taskRepository.delete(taskRepository.getOne(Long.valueOf(request.getParameter("id"))));
        return "redirect:/";
    }


    @RequestMapping(value = "/template", method = GET)
    public ModelAndView template() {

            return new ModelAndView("/template");


    }

}

package pl.hycom.training.toDoList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.hycom.training.toDoList.controller.ToDoListController;
import pl.hycom.training.toDoList.model.Task;
import pl.hycom.training.toDoList.repository.TaskRepository;
import pl.hycom.training.toDoList.service.ToDoListService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hycom on 09.04.18.
 */


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ToDoListApplication.class)

public class ToDoListServiceControllerTests {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private ToDoListService service ;

    @Before
    public void init() {
        List<Task> taskList = new ArrayList<>();
        Task t1 = new Task();
        t1.setId(1L);
        t1.setDescription("test description");
        t1.setFinishDate(LocalDate.now());
        Task t2 = new Task();
        t2.setId(2L);
        t2.setDescription("test description2");
        t2.setFinishDate(LocalDate.now());
        Task t3 = new Task();
        t3.setId(3L);
        t3.setDescription("test description3");
        t3.setFinishDate(LocalDate.now());
        taskList.add(t1);
        taskList.add(t2);
        Mockito.when(repository.findAll()).thenReturn(taskList);
    }

    @Test
    public void getAllTest() {
        List<Task> tasks;
        tasks = service.getAllTasks();
        Assert.assertNotNull(tasks);
        Assert.assertEquals(2, tasks.size());
    }

    @Test
    public void addTaskTest(){
        service.addTask("desc", "1992-12-12");
    }

    @Test(expected = DateTimeParseException.class)
    public void addTaskWrongDateTest(){
        service.addTask("desc", "19922222-12-12");
    }
}

package pl.damian.training.toDoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.damian.training.toDoList.model.User;
import pl.damian.training.toDoList.repository.RoleRepository;
import pl.damian.training.toDoList.repository.TaskRepository;
import pl.damian.training.toDoList.repository.UserRepository;
import pl.damian.training.toDoList.model.Role;
import pl.damian.training.toDoList.model.Task;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(taskRepository.getOne(id));
    }

    @Override
    public void addTask(String username, String description, String finishDate) {
        Task task = new Task();
        task.setUser(userRepository.findByUsername(username));
        task.setDescription(description);
        if(finishDate!="")
            task.setFinishDate(LocalDate.parse(finishDate));
        else task.setFinishDate(LocalDate.now());
        taskRepository.save(task);
    }

    @Override
    public List<Role> getRoles() {
      return  roleRepository.findAll();
    }

    @Override
    public void addUser(String username, String password, Set<String> authorities) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRoles(convertAuthorities(authorities));
        userRepository.save(user);
    }

    @Override
    public void saveUser(String id, Set<String> authorities) {
       /* userRepository.findById(Long.valueOf(id)).get().setRoles(convertAuthorities(authorities));
        for(Role role : conv(authorities))
            userRepository.setRoles(Long.valueOf(id), convertAuthorities(authorities));
*/
        User user = userRepository.findById(Long.valueOf(id)).get();
        user.setRoles(convertAuthorities(authorities));
        userRepository.save(user);
    }

   Set<Role> convertAuthorities (Set<String> authoritiesToConvert){
       Set<Role> roles = new HashSet<>();
       for (String authority : authoritiesToConvert){
           roles.add(userService.findRoleByName(authority));
       }
       return roles;
   }

    Set<Role> conv (Set<String> authoritiesToConvert){
        Set<Role> roles = new HashSet<>();
        for (String authority : authoritiesToConvert){
            roles.add(roleRepository.findByName(authority).get());
        }
        return roles;
    }
}

package pl.hycom.training.toDoList.service;

import pl.hycom.training.toDoList.model.Role;
import pl.hycom.training.toDoList.model.User;

/**
 * Created by hycom on 11.04.18.
 */
public interface UserService {
    void save(User user);
    User findByUsername(String username);
    Role findRoleById(int id);
    String restLogin(String username, String password);
    String parseJWT(String jwt);
    Role findRoleByName(String name);


}

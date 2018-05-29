package pl.damian.training.toDoList.service;

import io.jsonwebtoken.Claims;
import pl.damian.training.toDoList.exceptions.PasswordInvalidException;
import pl.damian.training.toDoList.model.User;
import pl.damian.training.toDoList.model.Role;


/**
 * Created by hycom on 11.04.18.
 */
public interface UserService {
    void save(User user);
    User findByUsername(String username);
    Role findRoleById(int id);
    String restLogin(String username, String password) throws PasswordInvalidException;
    Claims parseJWT(String jwt);
    Role findRoleByName(String name);

}

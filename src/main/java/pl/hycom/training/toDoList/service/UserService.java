package pl.hycom.training.toDoList.service;

import pl.hycom.training.toDoList.model.User;

/**
 * Created by hycom on 11.04.18.
 */
public interface UserService {
    void save(User user);
    User findByUsername(String username);

}

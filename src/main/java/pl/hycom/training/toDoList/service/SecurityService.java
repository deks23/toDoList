package pl.hycom.training.toDoList.service;

/**
 * Created by hycom on 11.04.18.
 */
public interface SecurityService {
    String findLoggedInUsername();
    void autologin(String username, String password);
}

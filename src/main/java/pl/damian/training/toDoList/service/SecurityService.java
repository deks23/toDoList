package pl.damian.training.toDoList.service;


public interface SecurityService {
    String findLoggedInUsername();
    void autologin(String username, String password);
}

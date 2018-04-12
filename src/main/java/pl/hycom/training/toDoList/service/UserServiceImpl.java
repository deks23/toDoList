package pl.hycom.training.toDoList.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.hycom.training.toDoList.model.Role;
import pl.hycom.training.toDoList.model.User;
import pl.hycom.training.toDoList.repository.RoleRepository;
import pl.hycom.training.toDoList.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set <Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(Long.valueOf(1)).get());
        user.setRoles(roles);
       /* user.setRoles(new HashSet<>(roleRepository.findAll()));*/
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Role findRoleById(int id){
        return roleRepository.findById(Long.valueOf(id)).get();
    }
}
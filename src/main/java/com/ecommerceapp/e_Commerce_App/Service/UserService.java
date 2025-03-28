package com.ecommerceapp.e_Commerce_App.Service;


import com.ecommerceapp.e_Commerce_App.Entity.User;
import com.ecommerceapp.e_Commerce_App.Repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() { return userRepository.findAll(); }
    public User getUserById(String id) { return userRepository.findById(id).orElse(null); }
    public User saveUser(User user) { return userRepository.save(user); }
    public void deleteUser(String id) { userRepository.deleteById(id); }
    public User updateUser(String id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }
    }



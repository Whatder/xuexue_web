package service;

import model.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    List<User> getAllUser();
}

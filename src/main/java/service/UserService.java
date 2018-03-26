package service;

import model.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);

    List<User> getAllUser();

    User getUserByAccount(String account);

    Boolean logup(String account, String password, String name);
}

package service;

import dao.UserMapper;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

    public Boolean logup(String account, String password, String name) {
        return userMapper.logup(account, password, name);
    }

    public Boolean changerPassword(String password, int id) {
        return userMapper.changerPassword(password, id);
    }

    public Boolean changerName(String name, int id) {
        return userMapper.changerName(name, id);
    }
}

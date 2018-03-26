package dao;

import model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where id=#{id}")
    User getUserById(int id);

    @Select("select * from user")
    List<User> getAllUser();

    @Select("select * from user where account=#{account}")
    User getUserByAccount(String account);
}

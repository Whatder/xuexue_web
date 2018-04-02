package dao;

import model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where id=#{id}")
    User getUserById(int id);

    @Select("select * from user")
    List<User> getAllUser();

    @Select("select * from user where account=#{account}")
    User getUserByAccount(String account);

    @Insert("insert into user(account,password,name) values(#{account},#{password},#{name})")
    Boolean logup(@Param("account") String account, @Param("password") String password, @Param("name") String name);

    @Update("update user set password=#{password} where id=#{id}")
    Boolean changerPassword(@Param("password") String password, @Param("id") int id);

    @Update("update user set name=#{name} where id=#{id}")
    Boolean changerName(@Param("name") String name, @Param("id") int id);
}

package dao;

import model.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminMapper {
    @Select("select * from admin where account=#{account}")
    Admin getAdminByAccount(@Param("account") String account);

    @Select("select * from admin where id=#{id}")
    Admin getAdminById(@Param("id") int id);

    @Update("update admin set password=#{password} where id=#{id}")
    Boolean changePwd(@Param("password") String password, @Param("id") int id);
}

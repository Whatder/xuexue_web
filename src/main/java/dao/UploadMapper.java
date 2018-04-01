package dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UploadMapper {
    @Update("update user set profile_pic=#{profile_pic} where id=#{id}")
    Boolean updateProfilePic(@Param("profile_pic") String profile_pic, @Param("id") int id);
}

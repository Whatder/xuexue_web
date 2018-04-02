package dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UploadMapper {
    @Update("update user set profile_pic=#{profile_pic} where id=#{id}")
    Boolean updateProfilePic(@Param("profile_pic") String profile_pic, @Param("id") int id);

    @Insert("insert into movies(thumbnail,title,summary,url) values(#{thumbnail},#{title},#{summary},#{url})")
    Boolean addMovies(@Param("thumbnail") String thumbnail, @Param("title") String title, @Param("summary") String summary, @Param("url") String url);
}

package dao;

import model.Movie;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MovieMapper {
    @Select("select * from movies")
    List<Movie> getAllMovies();

    @Delete("delete from movies where id=#{id}")
    Boolean delMovieById(int id);
}

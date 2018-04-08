package service;

import dao.MovieMapper;
import model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieMapper movieMapper;

    public List<Movie> getAllMovies() {
        return movieMapper.getAllMovies();
    }

    public Boolean delMovieById(int id) {
        return movieMapper.delMovieById(id);
    }
}

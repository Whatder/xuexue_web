package controller;

import model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import response.ResponseData;
import response.ResponseDataUtils;
import service.MovieService;

import java.util.List;

@Controller
public class MovieController {
    @Autowired
    MovieService movieService;
    ResponseData responseData;

    @RequestMapping("movie/all")
    @ResponseBody
    public ResponseData getAllMovies() {
        List<Movie> allMovies = movieService.getAllMovies();
        if (allMovies == null)
            responseData = new ResponseDataUtils<List<Movie>>().dataBuilder(false, "获取失败", allMovies);
        else
            responseData = new ResponseDataUtils<List<Movie>>().dataBuilder(true, "", allMovies);
        return responseData;
    }
}

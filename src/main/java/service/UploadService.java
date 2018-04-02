package service;

public interface UploadService {
    Boolean updateProfilePic(String profile_pic, int id);

    Boolean addMovies(String thumbnail, String title, String summary, String url);

}

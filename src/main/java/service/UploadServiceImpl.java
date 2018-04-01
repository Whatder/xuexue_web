package service;

import dao.UploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    UploadMapper uploadMapper;

    public Boolean updateProfilePic(String profile_pic, int id) {
        return uploadMapper.updateProfilePic(profile_pic, id);
    }
}

package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import response.ResponseData;
import response.ResponseDataUtils;
import service.UploadService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

@Controller
public class UploadController {
    @Autowired
    private UploadService uploadService;
    private ResponseData responseData;

    @RequestMapping(value = "/upload/profile_pic", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData upload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request) {
        if (uploadFile == null)
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "请选择文件", "");
        else {
            try {
                //时间戳加后缀
                String name = Calendar.getInstance().getTimeInMillis() / 1000
                        + "." + uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf(".") + 1);
                String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/images/") + name;
                File filePath = new File(path);
                if (!filePath.getParentFile().exists())
                    filePath.getParentFile().mkdirs();
                uploadFile.transferTo(filePath);
//                需要更换服务器地址
                if (uploadService.updateProfilePic("http://10.1.95.99/images/" + name, Integer.parseInt(request.getParameter("user_id"))))
                    responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "更换成功");
                else
                    responseData = new ResponseDataUtils<String>().dataBuilder(false, "更换失败", "");
            } catch (IOException e) {
                e.printStackTrace();
                responseData = new ResponseDataUtils<String>().dataBuilder(false, e.getMessage(), "");
            }
        }
        return responseData;
    }

    @RequestMapping(value = "/upload/movie", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData uploadMovie(@RequestParam("image") MultipartFile uploadImage,
                                    @RequestParam("file") MultipartFile uploadFile,
                                    HttpServletRequest request) {
        String title = request.getParameter("title");
        String summary = request.getParameter("summary");
        if (uploadFile == null || uploadImage == null)
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "请选择文件", "");
        if (title == null || summary == null)
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "参数不完整", "");
        else {
            try {
//                保存缩略图
                String imageName = Calendar.getInstance().getTimeInMillis() / 1000
                        + "." + uploadImage.getOriginalFilename().substring(uploadImage.getOriginalFilename().lastIndexOf(".") + 1);
                String ImagePath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/images/") + imageName;
                File imageFilePath = new File(ImagePath);
                if (!imageFilePath.getParentFile().exists())
                    imageFilePath.getParentFile().mkdirs();
                uploadImage.transferTo(imageFilePath);


                //取时间戳加后缀作为文件名
                String name = Calendar.getInstance().getTimeInMillis() / 1000
                        + "." + uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf(".") + 1);
                String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/movies/") + name;
                File filePath = new File(path);
                if (!filePath.getParentFile().exists())
                    filePath.getParentFile().mkdirs();
                uploadFile.transferTo(filePath);
//                需要更换服务器
                if (uploadService.addMovies("http://10.1.95.99/movies/" + imageName, title, summary, "http://10.1.95.99/movies/" + name))
                    responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "添加成功");
                else
                    responseData = new ResponseDataUtils<String>().dataBuilder(false, "添加失败", "");
            } catch (IOException e) {
                e.printStackTrace();
                responseData = new ResponseDataUtils<String>().dataBuilder(false, e.getMessage(), "");
            }
        }
        return responseData;
    }
}

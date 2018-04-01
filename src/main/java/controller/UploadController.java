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
                String name = Calendar.getInstance().getTimeInMillis() / 1000 + "_" + uploadFile.getOriginalFilename();
                String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/images/") + name;
                uploadFile.transferTo(new File(path));
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
}

package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import response.ResponseData;
import response.ResponseDataUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

@Controller
public class UploadController {
    private ResponseData responseData;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData upload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request) {
        if (uploadFile == null)
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "请选择文件", "");
        else {
            try {
                String name = Calendar.getInstance().getTimeInMillis() / 1000 + "_" + uploadFile.getOriginalFilename();
//                String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/images/") + name;
                String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/images/") + name;
                uploadFile.transferTo(new File(path));
                responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "上传成功");
            } catch (IOException e) {
                e.printStackTrace();
                responseData = new ResponseDataUtils<String>().dataBuilder(false, e.getMessage(), "");
            }
        }
        return responseData;
    }
}

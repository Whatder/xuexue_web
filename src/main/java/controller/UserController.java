package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import response.ResponseData;
import response.ResponseDataUtils;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    @ResponseBody
    public ResponseData user(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);
        ResponseData<User> responseData;
        if (user != null)
            responseData = new ResponseDataUtils<User>().dataBuilder(true, "", user);
        else
            responseData = new ResponseDataUtils<User>().dataBuilder(false, "用户不存在", user);
        return responseData;
    }

    @RequestMapping("/user/all")
    @ResponseBody
    public ResponseData allUser() {
        ResponseData<User> responseData;
        List<User> userList = userService.getAllUser();
        if (userList != null)
            responseData = new ResponseDataUtils<User>().dataBuilder(true, "", userList);
        else
            responseData = new ResponseDataUtils<User>().dataBuilder(false, "参数错误", userList);
        return responseData;
    }
}

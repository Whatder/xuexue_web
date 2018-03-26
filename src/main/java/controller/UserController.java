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
    private ResponseData responseData;

    //    通过id找用户
    @RequestMapping("/user")
    @ResponseBody
    public ResponseData user(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);
        if (user != null)
            responseData = new ResponseDataUtils<User>().dataBuilder(true, "", user);
        else
            responseData = new ResponseDataUtils<User>().dataBuilder(false, "用户不存在", user);
        return responseData;
    }

    //返回全部用户
    @RequestMapping("/user/all")
    @ResponseBody
    public ResponseData allUser() {
        List<User> userList = userService.getAllUser();
        if (userList != null)
            responseData = new ResponseDataUtils<User>().dataBuilder(true, "", userList);
        else
            responseData = new ResponseDataUtils<User>().dataBuilder(false, "参数错误", userList);
        return responseData;
    }

    //    登录
    @RequestMapping("/login")
    @ResponseBody
    public ResponseData login(HttpServletRequest request) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        User user = userService.getUserByAccount(account);
        if (user == null) {
            responseData = new ResponseDataUtils<User>().dataBuilder(false, "用户不存在", user);
        } else if (!user.getPassword().equals(password)) {
            user = null;
            responseData = new ResponseDataUtils<User>().dataBuilder(false, "密码错误", user);
        } else {
            responseData = new ResponseDataUtils<User>().dataBuilder(true, "", user);
        }
        return responseData;
    }

    //    注册
    @RequestMapping("/logup")
    @ResponseBody
    public ResponseData logup(HttpServletRequest request) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        User user = userService.getUserByAccount(account);
//        用户已存在
        if (user != null) {
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "用户已存在", "");
        } else {
            if (userService.logup(account, password, name)) {
                responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "注册成功");
            } else {
                responseData = new ResponseDataUtils<String>().dataBuilder(false, "注册失败", "");
            }
        }
        return responseData;
    }
}

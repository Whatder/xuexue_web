package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
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
    @RequestMapping(value = "/user/logup", method = RequestMethod.POST)
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

    //修改密码
    @RequestMapping(value = "user/password", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData changerPwd(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String oldPwd = request.getParameter("old_pwd");
        String newPwd = request.getParameter("new_pwd");
        if (id == 0 || null == oldPwd || null == newPwd)
            return new ResponseDataUtils<String>().dataBuilder(false, "参数错误", "");
        User user = userService.getUserById(id);
        if (!oldPwd.equals(user.getPassword()))
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "原密码错误", "");
        else {
            if (userService.changerPassword(newPwd, id))
                responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "修改成功");
            else
                responseData = new ResponseDataUtils<String>().dataBuilder(false, "修改失败，请重试", "");
        }
        return responseData;
    }

    //修改昵称
    @RequestMapping(value = "user/name", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData changerName(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        if (id == 0 || null == name)
            return new ResponseDataUtils<String>().dataBuilder(false, "参数错误", "");
        if (userService.changerName(name, id))
            responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "修改成功");
        else
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "修改失败，请重试", "");
        return responseData;
    }
}

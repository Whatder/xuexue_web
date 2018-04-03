package controller;

import model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import response.ResponseData;
import response.ResponseDataUtils;
import service.AdminService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;
    ResponseData responseData;

    //    登录
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData login(HttpServletRequest request) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        Admin admin = adminService.getAdminByAccount(account);
        if (admin == null) {
            responseData = new ResponseDataUtils<Admin>().dataBuilder(false, "用户不存在", admin);
        } else if (!admin.getPassword().equals(password)) {
            admin = null;
            responseData = new ResponseDataUtils<Admin>().dataBuilder(false, "密码错误", admin);
        } else {
            responseData = new ResponseDataUtils<Admin>().dataBuilder(true, "", admin);
        }
        return responseData;
    }


    //修改密码
    @RequestMapping(value = "admin/password", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData changerPwd(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String oldPwd = request.getParameter("old_pwd");
        String newPwd = request.getParameter("new_pwd");
        if (id == 0 || null == oldPwd || null == newPwd)
            return new ResponseDataUtils<String>().dataBuilder(false, "参数错误", "");
        Admin user = adminService.getAdminById(id);
        if (!oldPwd.equals(user.getPassword()))
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "原密码错误", "");
        else {
            if (adminService.changePwd(newPwd, id))
                responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "修改成功");
            else
                responseData = new ResponseDataUtils<String>().dataBuilder(false, "修改失败，请重试", "");
        }
        return responseData;
    }
}

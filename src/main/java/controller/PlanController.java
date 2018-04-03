package controller;

import model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import response.ResponseData;
import response.ResponseDataUtils;
import service.PlanService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PlanController {
    @Autowired
    private PlanService planService;
    private ResponseData responseData;

    @RequestMapping("/plan")
    @ResponseBody
    public ResponseData getPlanById(HttpServletRequest request) {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        List<Plan> planList = planService.getPlanByUserId(user_id);
        if (null == planList)
            responseData = new ResponseDataUtils<List<Plan>>().dataBuilder(false, "获取计划列表失败", planList);
        else {
            responseData = new ResponseDataUtils<List<Plan>>().dataBuilder(true, "", planList);
        }

        return responseData;
    }

    @RequestMapping(value = "/plan/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addPlan(HttpServletRequest request) {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int create_time = Integer.parseInt(request.getParameter("create_time"));
        String status = request.getParameter("status");

        if (planService.addPlan(user_id, title, content, create_time, status))
            responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "插入成功");
        else {
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "插入失败", "");
        }
        return responseData;
    }

    @RequestMapping(value = "/plan/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updatePlan(HttpServletRequest request) {
        String status = request.getParameter("status");
        int id = Integer.parseInt(request.getParameter("id"));

        if (planService.updateStatus(status, id))
            responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "更新成功");
        else {
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "更新失败", "");
        }
        return responseData;
    }

    @RequestMapping(value = "plan/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delPlan(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        if (planService.delPlanById(id))
            responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "删除成功");
        else
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "失败", "");
        return responseData;
    }
}

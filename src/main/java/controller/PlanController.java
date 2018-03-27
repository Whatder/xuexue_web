package controller;

import model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

}

package service;

import dao.PlanMapper;
import model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanMapper planMapper;

    public List<Plan> getPlanByUserId(int user_id) {
        return planMapper.getPlanByUserId(user_id);
    }

    public Boolean addPlan(int user_id, String title, String content, long create_time, String status) {
        return planMapper.addPlan(user_id, title, content, create_time, status);
    }

    public Boolean delPlanById(int id) {
        return planMapper.delPlanById(id);
    }

    public Boolean updateStatus(String status, int id) {
        return planMapper.updateStatus(status, id);
    }
}

package service;

import model.Plan;

import java.util.List;

public interface PlanService {
    List<Plan> getPlanByUserId(int user_id);

    Boolean addPlan(int user_id, String title, String content, long create_time, String status);

    Boolean delPlanById(int id);

    Boolean updateStatus(String status, int id);
}

package dao;

import model.Plan;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PlanMapper {
    @Select("select * from plan where user_id=#{user_id}")
    List<Plan> getPlanByUserId(int user_id);

    @Insert("insert into plan(user_id,title,content,create_time,status) values(#{user_id},#{title},#{content},#{create_time},#{status}) ")
    Boolean addPlan(@Param("user_id") int user_id, @Param("title") String title, @Param("content") String content,
                    @Param("create_time") long create_time, @Param("status") String status);

    @Delete("delete from plan where id=#{id}")
    Boolean delPlanById(int id);

    @Update("update plan set status=#{status} where id=#{id}")
    Boolean updateStatus(@Param("status") String status, @Param("id") int id);

}

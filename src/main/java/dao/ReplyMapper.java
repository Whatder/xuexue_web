package dao;

import model.Reply;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReplyMapper {
    @Select("select reply.id,user.name,user.profile_pic,reply.content,reply.create_time from reply left join user on reply.author_id = user.id where reply.topic_id = #{topic_id}")
    List<Reply> getReplyByTopicId(int topic_id);
}

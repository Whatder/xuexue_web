package dao;

import model.Reply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReplyMapper {
    @Select("select reply.id,user.name,user.profile_pic,reply.content,reply.create_time from reply left join user on reply.author_id = user.id order by reply.create_time desc")
    List<Reply> getAllReply();

    @Select("select reply.id,user.name,user.profile_pic,reply.content,reply.create_time from reply left join user on reply.author_id = user.id where reply.topic_id = #{topic_id} order by reply.create_time desc")
    List<Reply> getReplyByTopicId(int topic_id);

    @Insert("insert into reply(author_id,topic_id,content,create_time) values(#{author_id},#{topic_id},#{content},#{create_time})")
    Boolean insertReply(@Param("author_id") int author_id, @Param("topic_id") int topic_id, @Param("content") String content, @Param("create_time") long create_time);

    @Delete("delete from reply where id=#{id}")
    Boolean delReply(int id);
}

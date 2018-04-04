package dao;

import model.Topic;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TopicMapper {
    @Select("select user.name,user.profile_pic,topic.* from topic left join user on topic.author_id = user.id order by topic.create_time desc")
    List<Topic> getTopicList();

    @Insert("insert into topic(author_id,title,content,create_time,like_count) values(#{author_id},#{title},#{content},#{create_time},0)")
    Boolean insertTopic(@Param("author_id") int author_id, @Param("title") String title, @Param("content") String content, @Param("create_time") long create_time);

    @Update("update topic set like_count=like_count+1 where id=#{id}")
    Boolean likeTopicById(int id);

    @Select("select * from topic where id=#{id}")
    Topic getTopicByID(int id);

    @Delete("delete from topic where id=#{id}")
    Boolean delTopicByID(int id);

    //删除指定topic_id下的评论
    @Delete("delete from reply where topic_id=#{topic_id}")
    Boolean delReplyByTopicID(int topic_id);
}

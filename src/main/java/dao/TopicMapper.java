package dao;

import model.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TopicMapper {
    @Select("select user.name,user.profile_pic,topic.* from topic left join user on topic.author_id = user.id")
    List<Topic> getTopicList();

    @Insert("insert into topic(author_id,title,content,create_time,like_count) values(#{author_id},#{title},#{content},#{create_time},0)")
    Boolean insertTopic(@Param("author_id") int author_id, @Param("title") String title, @Param("content") String content, @Param("create_time") long create_time);
}

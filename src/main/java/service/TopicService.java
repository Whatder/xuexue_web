package service;

import model.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> getTopicList();

    Boolean insertTopic(int author_id, String title, String content, long create_time);

    Boolean likeTopicById(int id);
}

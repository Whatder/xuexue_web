package service;

import model.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> getTopicList();

    Boolean insertTopic(int author_id, String title, String content, long create_time);

    Boolean likeTopicById(int id);

    Topic getTopicByID(int id);

    Boolean delTopicByID(int id);

    Boolean delReplyByTopicID(int topic_id);
}

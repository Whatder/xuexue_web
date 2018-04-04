package service;

import dao.TopicMapper;
import model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicMapper topicMapper;

    public List<Topic> getTopicList() {
        return topicMapper.getTopicList();
    }

    public Boolean insertTopic(int author_id, String title, String content, long create_time) {
        return topicMapper.insertTopic(author_id, title, content, create_time);
    }

    public Boolean likeTopicById(int id) {
        return topicMapper.likeTopicById(id);
    }

    public Topic getTopicByID(int id) {
        return topicMapper.getTopicByID(id);
    }

    public Boolean delTopicByID(int id) {
        return topicMapper.delTopicByID(id);
    }

    public Boolean delReplyByTopicID(int topic_id) {
        return topicMapper.delReplyByTopicID(topic_id);
    }
}

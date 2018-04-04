package service;

import dao.ReplyMapper;
import model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyMapper replyMapper;

    public List<Reply> getAllReply() {
        return replyMapper.getAllReply();
    }

    public List<Reply> getReplyByTopicId(int topic_id) {
        return replyMapper.getReplyByTopicId(topic_id);
    }

    public Boolean insertReply(int author_id, int topic_id, String content, long create_time) {
        return replyMapper.insertReply(author_id, topic_id, content, create_time);
    }

    public Boolean delReply(int id) {
        return replyMapper.delReply(id);
    }
}

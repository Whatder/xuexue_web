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

    public List<Reply> getReplyByTopicId(int topic_id) {
        return replyMapper.getReplyByTopicId(topic_id);
    }
}

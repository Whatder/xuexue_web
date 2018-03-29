package service;

import model.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> getReplyByTopicId(int topic_id);
}

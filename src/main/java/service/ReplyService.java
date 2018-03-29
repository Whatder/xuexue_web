package service;

import model.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> getReplyByTopicId(int topic_id);

    Boolean insertReply(int author_id, int topic_id, String content, long create_time);
}

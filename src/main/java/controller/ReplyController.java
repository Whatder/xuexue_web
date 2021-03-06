package controller;

import model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import response.ResponseData;
import response.ResponseDataUtils;
import service.ReplyService;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

@Controller
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    private ResponseData responseData;

    @RequestMapping("/reply")
    @ResponseBody
    public ResponseData getReplyById(HttpServletRequest request) {
        int topic_id = Integer.parseInt(request.getParameter("topic_id"));
        List<Reply> replyList = replyService.getReplyByTopicId(topic_id);
        if (replyList != null)
            responseData = new ResponseDataUtils<List<Reply>>().dataBuilder(true, "", replyList);
        else
            responseData = new ResponseDataUtils<List<Reply>>().dataBuilder(false, "", replyList);
        return responseData;
    }

    @RequestMapping(value = "/reply/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addReply(HttpServletRequest request) {
        int author_id = Integer.parseInt(request.getParameter("author_id"));
        int topic_id = Integer.parseInt(request.getParameter("topic_id"));
        String content = request.getParameter("content");
        long create_time = Calendar.getInstance().getTimeInMillis() / 1000;
        if (replyService.insertReply(author_id, topic_id, content, create_time))
            responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "评论成功");
        else
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "评论失败", "");
        return responseData;
    }

    @RequestMapping("/reply/all")
    @ResponseBody
    public ResponseData getAllReply() {
        List<Reply> replyList = replyService.getAllReply();
        if (replyList != null)
            responseData = new ResponseDataUtils<List<Reply>>().dataBuilder(true, "", replyList);
        else
            responseData = new ResponseDataUtils<List<Reply>>().dataBuilder(false, "", replyList);
        return responseData;
    }

    @RequestMapping(value = "/reply/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delReply(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        if (replyService.delReply(id))
            responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "删除成功");
        else
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "删除失败", "");
        return responseData;
    }
}

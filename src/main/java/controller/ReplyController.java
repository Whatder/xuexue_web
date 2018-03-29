package controller;

import model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import response.ResponseData;
import response.ResponseDataUtils;
import service.ReplyService;

import javax.servlet.http.HttpServletRequest;
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
}
package controller;

import model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import response.ResponseData;
import response.ResponseDataUtils;
import service.TopicService;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

@Controller
public class TopicController {
    @Autowired
    private TopicService topicService;
    private ResponseData responseData;

    @RequestMapping("/topic")
    @ResponseBody
    public ResponseData getTopicList() {
        List<Topic> topicList = topicService.getTopicList();
        if (null != topicService)
            responseData = new ResponseDataUtils<List<Topic>>().dataBuilder(true, "", topicList);
        else
            responseData = new ResponseDataUtils<List<Topic>>().dataBuilder(false, "获取失败", topicList);
        return responseData;
    }

    @RequestMapping(value = "/topic/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insertTopic(HttpServletRequest request) {
        int author_id = Integer.parseInt(request.getParameter("author_id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        long create_time = Calendar.getInstance().getTimeInMillis() / 1000;
        if (topicService.insertTopic(author_id, title, content, create_time))
            responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "发布成功");
        else
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "发布失败", "");
        return responseData;
    }

    @RequestMapping(value = "/topic/like", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData likeTopicById(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        if (topicService.likeTopicById(id))
            responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "点赞成功");
        else
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "点赞失败", "");
        return responseData;
    }

    @RequestMapping(value = "/topic/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delTopicById(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
//        删除指定topic下的评论后 再删除本身
        if (topicService.delReplyByTopicID(topicService.getTopicByID(id).getId()) &&
                topicService.delTopicByID(id))
            responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "删除成功");
        else
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "删除失败", "");
        return responseData;
    }
}

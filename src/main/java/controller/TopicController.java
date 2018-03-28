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
        long create_time = Long.parseLong(request.getParameter("create_time"));
        if (topicService.insertTopic(author_id, title, content, create_time))
            responseData = new ResponseDataUtils<String>().dataBuilder(true, "", "发布成功");
        else
            responseData = new ResponseDataUtils<String>().dataBuilder(false, "发布失败", "");
        return responseData;
    }
}

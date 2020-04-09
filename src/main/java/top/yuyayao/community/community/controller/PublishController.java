package top.yuyayao.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import top.yuyayao.community.community.mapper.QuestionMapper;
import top.yuyayao.community.community.model.Question;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/doPublish")
    public String doPublish(Question question){
        questionMapper.create(question);
        return "publish";
    }
}

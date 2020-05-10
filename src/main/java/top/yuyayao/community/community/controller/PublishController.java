package top.yuyayao.community.community.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.yuyayao.community.community.mapper.QuestionMapper;
import top.yuyayao.community.community.mapper.UserMapper;
import top.yuyayao.community.community.model.Question;
import top.yuyayao.community.community.model.User;
import top.yuyayao.community.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false)String title
            , @RequestParam(value = "description", required = false)String description
            , @RequestParam(value = "tag", required = false)String tag
            , @RequestParam(value = "id",required = false) Integer id
            , HttpServletRequest request
            , Model model){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            model.addAttribute("error","用户未登陆");
            return "/publish";
        }
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title==null||title.equals("")){
            model.addAttribute("error","标题不能为空");
            return "/publish";
        }
        if(description==null||description.equals("")){
            model.addAttribute("error","内容不能为空");
            return "/publish";
        }
        if(tag==null||tag.equals("")){
            model.addAttribute("error","标签不能为空");
            return "/publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
//        questionMapper.create(question);
        question.setId(id);
        questionService.createOrupdate(question);
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id, Model model){
//        return "redirect:/question/"+id;
        Question question = questionMapper.getOneByI(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",id);
        return "/publish";
    }
}

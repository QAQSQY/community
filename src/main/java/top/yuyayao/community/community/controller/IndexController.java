package top.yuyayao.community.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.yuyayao.community.community.dto.QuestionDTO;
import top.yuyayao.community.community.mapper.QuestionMapperCustom;
import top.yuyayao.community.community.mapper.UserMapper;
import top.yuyayao.community.community.model.User;
import top.yuyayao.community.community.model.UserExample;
import top.yuyayao.community.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request, Model model, @RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        PageHelper.offsetPage(pageNum, pageSize);
        PageInfo<QuestionDTO> questionPageInfo = new PageInfo<QuestionDTO>(questionService.pageFind());
        if (questionPageInfo.getPageNum() == 0) {
            questionPageInfo.setPageNum(1);
        }
        model.addAttribute("questions", questionPageInfo);
        return "index";
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request, Model model, @RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize, HttpServletResponse response) {
//        User user = userMapper.findByToken("03e26d23-3141-4ddf-b347-eb222a22bcf2");
        UserExample userExample = new UserExample();
        userExample.createCriteria().andTokenEqualTo("03e26d23-3141-4ddf-b347-eb222a22bcf2");
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() != 0) {
            HttpSession session = request.getSession();
            session.setAttribute("user", users.get(0));
        }
        pageNum = (pageNum - 1) * pageSize;
        PageHelper.offsetPage(pageNum, pageSize);
        PageInfo<QuestionDTO> questionPageInfo = new PageInfo<QuestionDTO>(questionService.pageFind());
        if (questionPageInfo.getPageNum() == 0) {
            questionPageInfo.setPageNum(1);
        }
        model.addAttribute("questions", questionPageInfo);
        return "index";
    }
}

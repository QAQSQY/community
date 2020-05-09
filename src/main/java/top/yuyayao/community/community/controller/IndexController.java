package top.yuyayao.community.community.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.yuyayao.community.community.dto.QuestionDTO;
import top.yuyayao.community.community.mapper.UserMapper;
import top.yuyayao.community.community.model.Question;
import top.yuyayao.community.community.model.User;
import top.yuyayao.community.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        HttpSession session = request.getSession();
//                        session.setMaxInactiveInterval(60);
                        session.setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        pageNum =(pageNum-1)*pageSize;
        PageHelper.offsetPage(pageNum,pageSize);
        PageInfo<QuestionDTO> questionPageInfo = new PageInfo<QuestionDTO>(questionService.pageFind());
        model.addAttribute("questions",questionPageInfo);
        return "index";
    }
}

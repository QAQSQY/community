package top.yuyayao.community.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.yuyayao.community.community.dto.QuestionDTO;
import top.yuyayao.community.community.model.User;
import top.yuyayao.community.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProFileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request, @PathVariable(name = "action") String action, Model model, @RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("question".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            pageNum = (pageNum - 1) * pageSize;
            PageHelper.offsetPage(pageNum, pageSize);
            PageInfo<QuestionDTO> questionPageInfo = new PageInfo<QuestionDTO>(questionService.pageFindByUserId());
            if (questionPageInfo.getPageNum() == 0) {
                questionPageInfo.setPageNum(1);
            }
            model.addAttribute("questions", questionPageInfo);
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            pageNum = (pageNum - 1) * pageSize;
            PageHelper.offsetPage(pageNum, pageSize);
            PageInfo<QuestionDTO> questionPageInfo = new PageInfo<QuestionDTO>(questionService.pageFind());
            if (questionPageInfo.getPageNum() == 0) {
                questionPageInfo.setPageNum(1);
            }
            model.addAttribute("questions", questionPageInfo);
        }
        return "profile";
    }
}

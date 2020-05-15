package top.yuyayao.community.community.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import top.yuyayao.community.community.dto.ResultDTO;
import top.yuyayao.community.community.exception.CustomizeException;
import top.yuyayao.community.community.exception.CustomizedErrorCode;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(CustomizeException.class)
    @ResponseBody
    Object handle(HttpServletRequest request, Throwable throwable, Model model) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            //返回json
            if (throwable instanceof CustomizeException) {
                return ResultDTO.errorOf((CustomizeException) throwable);
            } else {
                return ResultDTO.errorOf(CustomizedErrorCode.SERVER_ERROR);
            }
        } else {
            //页面跳转
            if (throwable instanceof CustomizeException) {
                model.addAttribute("message", throwable.getMessage());
            } else {
                model.addAttribute("message", CustomizedErrorCode.QUESTION_NOT_FOUND.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}

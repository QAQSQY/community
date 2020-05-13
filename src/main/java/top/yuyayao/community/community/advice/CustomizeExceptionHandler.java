package top.yuyayao.community.community.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.yuyayao.community.community.exception.CustomizeException;
import top.yuyayao.community.community.exception.CustomizedErrorCode;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable throwable, Model model){
//        HttpStatus statuc = getStatus(request);
        if (throwable instanceof CustomizeException) {
            model.addAttribute("message",throwable.getMessage());
        } else {
            model.addAttribute("message", CustomizedErrorCode.REQUEST_ERROR.getMessage());
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
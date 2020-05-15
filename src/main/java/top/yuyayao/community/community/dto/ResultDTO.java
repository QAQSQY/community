package top.yuyayao.community.community.dto;

import lombok.Data;
import top.yuyayao.community.community.exception.CustomizeException;
import top.yuyayao.community.community.exception.CustomizedErrorCode;

@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code,String  message) {
        ResultDTO r = new ResultDTO();
        r.code = code;
        r.message = message;
        return r;
    }

    public static ResultDTO successOf(){
        ResultDTO r = new ResultDTO();
        r.code = 200;
        r.message = "请求成功";
        return r;
    }

    public static Object errorOf(CustomizedErrorCode noLogin) {
        return errorOf(noLogin.getCode(),noLogin.getMessage());
    }

    public static Object errorOf(CustomizeException throwable) {
        return errorOf(throwable.getCode(),throwable.getMessage());
    }
}

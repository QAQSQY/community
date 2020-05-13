package top.yuyayao.community.community.dto;

import lombok.Data;

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

    public static ResultDTO successOf(Integer code,String message){
        ResultDTO r = new ResultDTO();
        r.code = code;
        r.message = message;
        return r;
    }
}

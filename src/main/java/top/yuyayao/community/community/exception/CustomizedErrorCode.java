package top.yuyayao.community.community.exception;

public enum CustomizedErrorCode implements ICustomzedErrorCode {
    QUESTION_NOT_FOUND(2001, "你找的问题不存在"),
    REQUEST_ERROR(2002, "你的请求错了，要不换个姿势？"),
    TARGET_PARENT_NOT_FOUND(2003, "未选中任何评论和问题进行回复"),
    NO_LOGIN(2004,"当前操作需登录，请登录后在重试"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    SERVER_ERROR(5001, "服务器出问题啦"),
    COMMENT_NOT_FOUND(2006,"你操作的评论不存在" );


    private String message;
    private Integer code;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizedErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}

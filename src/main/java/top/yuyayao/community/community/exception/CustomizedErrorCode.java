package top.yuyayao.community.community.exception;

public enum CustomizedErrorCode implements ICustomzedErrorCode {
    QUESTION_NOT_FOUND("你找的问题不存在"),
    SERVER_ERROR("服务器出问题啦"),
    REQUEST_ERROR("你的请求错了，要不换个姿势？");
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizedErrorCode(String message) {
        this.message = message;
    }
}
